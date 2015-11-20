package com.mdear.www.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.ModelAndView;

import com.jspsmart.upload.SmartUpload;
import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.*;
import com.mdear.www.service.ICodeService;
import com.mdear.www.service.IColumnDataService;
import com.mdear.www.service.IDataErshouzhuzhaiService;
import com.mdear.www.service.IDataJiagezhishuService;
import com.mdear.www.service.IDataTudizongtiService;
import com.mdear.www.service.IDataXinjianzhuzhaiService;
import com.mdear.www.service.IDataZujinzhishuService;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.DataErshouzhuzhai;
import com.mdear.www.vo.DataJiagezhishu;
import com.mdear.www.vo.DataTudizongti;
import com.mdear.www.vo.DataXinjianzhuzhai;
import com.mdear.www.vo.DataZujinzhishu;
import com.mdear.www.vo.column_data;

/**
 * 
 * @author liuzc
 * 
 */
@Controller
@RequestMapping(value = "dataXinjianzhuzhaiController")
public class DataController implements ServletConfigAware{

	@Resource
	private IDataXinjianzhuzhaiService dataXinjianzhuzhaiService;
	@Resource
	private IDataZujinzhishuService dataZujinzhishuService;
	@Resource
	private IDataErshouzhuzhaiService dataErshouzhuzhaiService;
	@Resource
	private IDataJiagezhishuService dataJiagezhishuService;
	@Resource
	private IDataTudizongtiService dataTudizongtiService;
	@Resource
	private ICodeService codeService;
	@Resource
	private IColumnDataService columnDataService;
	
	private ServletConfig servletConfig;

	@Override
	public void setServletConfig(ServletConfig config) {
		this.servletConfig = config;
		
	}
	
	@RequestMapping("findData")
	@SystemControllerLog(description = "选择数据来源")
	public List<Code> findData(HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter, Code code) {
		String dmsm = request.getParameter("s1");
		String HQL = "select dmsm from Code where dmid='1003' ";
		List<Code> list = codeService.findByHQLQuery(HQL);
		return null;
	}

	/**
	 * 点击数据维护，跳出相应界面，显示下拉列表框
	 * 
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping("/findDataXinjiazhuzhai")
	@SystemControllerLog(description = "下拉列表框")
	public ModelAndView findDataXinjiazhuzhai(HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1003");
		return new ModelAndView("datawh/data_list").addObject("code_list",
				code_list);
	}
	//数据中心 只有查 和导出
	@RequestMapping("/findDataCentre")
	public ModelAndView findDataCentre(HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1003");
		return new ModelAndView("datawh/data_centre").addObject("code_list",code_list);
	}

	@RequestMapping("/selectColumnName")
	@SystemControllerLog(description = "动态获取列表的列名")
	public List<column_data> selectColumnName(String column_pinyin) {
		List<column_data> nameList =  new ArrayList<column_data>();
		nameList = columnDataService.findBySQLQuery(column_pinyin);
		return nameList;
	}
	
	/**
	 * 根据下拉列表框分页查询相应的5张数据
	 * 
	 * @param request
	 * @param response
	 * @param printWriter
	 */
	@RequestMapping("/findDataList")
	@SystemControllerLog(description = "分页查询")
	public void findDataList(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter) {
		Map<String, Object> colMap = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage")!=null?request.getParameter("curPage"):"1";
		String dmsm = request.getParameter("dmsm");// 下拉框的值

		Pager pager = PagerParam.getPagerParam(request, null);
		Pager result = null;

		List<column_data> columnData = selectColumnName(dmsm);
		if ("data_ershouzhuzhai".equals(dmsm)) {
			DataErshouzhuzhai dataErshouzhuzhai = new DataErshouzhuzhai();
			result = dataErshouzhuzhaiService.findByHQLQuery(dataErshouzhuzhai,pager);
		} else if ("data_jiagezhishu".equals(dmsm)) {
			DataJiagezhishu dataJiagezhishu = new DataJiagezhishu();
			result = dataJiagezhishuService.findByHQLQuery(dataJiagezhishu,
					pager);
		} else if ("data_zujinzhishu".equals(dmsm)) {
			DataZujinzhishu dataZujinzhishu = new DataZujinzhishu();
			result = dataZujinzhishuService.findByHQLQuery(dataZujinzhishu,
					pager);
		} else if ("data_tudizongti".equals(dmsm)) {
			DataTudizongti dataTudizongti = new DataTudizongti();
			result = dataTudizongtiService
					.findByHQLQuery(dataTudizongti, pager);
		} else if ("data_xinjianzhuzhai".equals(dmsm)) {
			DataXinjianzhuzhai dataXinjianzhuzhai = new DataXinjianzhuzhai();
			result = dataXinjianzhuzhaiService.findByHQLQuery(dataXinjianzhuzhai, pager);
		}
		colMap.put("result", result);
		colMap.put("columnData", columnData);
		String param = JsonUtil.jsonObject(colMap, new String[]{}, ContentUtil.YMD);
		
		printWriter.print(param);
	    printWriter.flush();
	    printWriter.close();
	}
	

	/**
	 * 保存or修改数据
	 * @param request
	 * @param response
	 * @param printWriter
	 * @return
	 */
	@RequestMapping("/saveorupdateData")
	@SystemControllerLog(description = "保存or修改")
	public void saveDataXinjianzhuzhai(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter) {
		Map<String,Object> result_Map=new HashMap<String,Object>();
		String dmsm = request.getParameter("tName");// 下拉框
		String hid = request.getParameter("id");
		boolean bool = false;
		Pager pager = new Pager();
		if ("data_ershouzhuzhai".equals(dmsm)) {
			if (hid == "") {
				DataErshouzhuzhai dataErshouzhuzhai = new DataErshouzhuzhai();
				dataErshouzhuzhai.setShijian(request.getParameter("shijian"));
				dataErshouzhuzhai.setCity(request.getParameter("city"));
				dataErshouzhuzhai.setChengjiaotaoshu(Integer.parseInt(request
						.getParameter("chengjiaotaoshu")));
				dataErshouzhuzhai.setShichangjunjia(Integer.parseInt(request
						.getParameter("shichangjunjia")));
				dataErshouzhuzhai.setUserid(Integer.parseInt(request
						.getSession().getAttribute("userId").toString()));
				bool=dataErshouzhuzhaiService.saveErshouzhuzhai(dataErshouzhuzhai);
				result_Map.put("success", bool);
			} else {
				DataErshouzhuzhai dataErshouzhuzhai = new DataErshouzhuzhai();
				dataErshouzhuzhai.setId(Integer.parseInt(request
						.getParameter("id")));
				dataErshouzhuzhai.setUserid(Integer.parseInt(request
						.getSession().getAttribute("userId").toString()));
				dataErshouzhuzhai.setShijian(request.getParameter("shijian"));
				dataErshouzhuzhai.setCity(request.getParameter("city"));
				dataErshouzhuzhai.setChengjiaotaoshu(Integer.parseInt(request
						.getParameter("chengjiaotaoshu")));
				dataErshouzhuzhai.setShichangjunjia(Integer.parseInt(request
						.getParameter("shichangjunjia")));
				bool=dataErshouzhuzhaiService.updateErshouzhuzhai(dataErshouzhuzhai);
				result_Map.put("success", bool);
			}
		} else if ("data_jiagezhishu".equals(dmsm)) {
			if (hid == "") {
				DataJiagezhishu dataJiagezhishu = new DataJiagezhishu();
				dataJiagezhishu.setShijian(request.getParameter("shijian"));
				dataJiagezhishu.setCity(request.getParameter("city"));
				dataJiagezhishu.setJiagezhishu(Integer.parseInt(request
						.getParameter("jiagezhishu")));
				dataJiagezhishu.setWuyeleixing(request
						.getParameter("wuyeleixing"));
				dataJiagezhishu.setHuanbi(Integer.parseInt(request
						.getParameter("huanbi")));
				dataJiagezhishu.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				bool=dataJiagezhishuService.saveJiagezhishu(dataJiagezhishu);
				result_Map.put("success", bool);
			} else {
				DataJiagezhishu dataJiagezhishu = new DataJiagezhishu();
				dataJiagezhishu.setId(Integer.parseInt(request
						.getParameter("id")));
				dataJiagezhishu.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				dataJiagezhishu.setShijian(request.getParameter("shijian"));
				dataJiagezhishu.setCity(request.getParameter("city"));
				dataJiagezhishu.setJiagezhishu(Integer.parseInt(request
						.getParameter("jiagezhishu")));
				dataJiagezhishu.setWuyeleixing(request
						.getParameter("wuyeleixing"));
				dataJiagezhishu.setHuanbi(Integer.parseInt(request
						.getParameter("huanbi")));
				bool=dataJiagezhishuService.updateJiagezhishu(dataJiagezhishu);
				result_Map.put("success", bool);
			}
		} else if ("data_zujinzhishu".equals(dmsm)) {
			if (hid == "") {
				DataZujinzhishu dataZujinzhishu = new DataZujinzhishu();
				dataZujinzhishu.setShijian(request.getParameter("shijian"));
				dataZujinzhishu.setCity(request.getParameter("city"));
				dataZujinzhishu.setWuyeleixing(request
						.getParameter("wuyeleixing"));
				dataZujinzhishu.setZujinzhishu(Integer.parseInt(request
						.getParameter("zujinzhishu")));
				dataZujinzhishu.setHuanbi(Integer.parseInt(request
						.getParameter("huanbi")));
				dataZujinzhishu.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				bool=dataZujinzhishuService.saveZujinzhishu(dataZujinzhishu);
				result_Map.put("success", bool);
			} else {
				DataZujinzhishu dataZujinzhishu = new DataZujinzhishu();
				dataZujinzhishu.setId(Integer.parseInt(request
						.getParameter("id")));
				dataZujinzhishu.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				dataZujinzhishu.setShijian(request.getParameter("shijian"));
				dataZujinzhishu.setCity(request.getParameter("city"));
				dataZujinzhishu.setWuyeleixing(request
						.getParameter("wuyeleixing"));
				dataZujinzhishu.setZujinzhishu(Integer.parseInt(request
						.getParameter("zujinzhishu")));
				dataZujinzhishu.setHuanbi(Integer.parseInt(request
						.getParameter("huanbi")));
				bool=dataZujinzhishuService.updateZujinzhishu(dataZujinzhishu);
				result_Map.put("success", bool);
			}
		} else if ("data_tudizongti".equals(dmsm)) {
			if (hid == "") {
				DataTudizongti dataTudizongti = new DataTudizongti();
				dataTudizongti.setShijian(request.getParameter("shijian"));
				dataTudizongti.setCity(request.getParameter("city"));
				dataTudizongti.setGuihuajianzhumianji(Integer.parseInt(request
						.getParameter("guihuajianzhumianji")));
				dataTudizongti.setJiansheyongdimianji(Integer.parseInt(request
						.getParameter("jiansheyongdimianji")));
				dataTudizongti.setPingjunyijialv(Integer.parseInt(request
						.getParameter("pingjunyijialv")));
				dataTudizongti.setTudichurangjin(Integer.parseInt(request
						.getParameter("tudichurangjin")));
				dataTudizongti.setYongtu(request.getParameter("yongtu"));
				dataTudizongti.setZongshu(Integer.parseInt(request
						.getParameter("zongshu")));
				dataTudizongti.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				bool=dataTudizongtiService.saveTudizongti(dataTudizongti);
				result_Map.put("success", bool);
			} else {
				DataTudizongti dataTudizongti = new DataTudizongti();
				dataTudizongti.setId(Integer.parseInt(request
						.getParameter("id")));
				dataTudizongti.setUserid(Integer.parseInt(request.getSession()
						.getAttribute("userId").toString()));
				dataTudizongti.setShijian(request.getParameter("shijian"));
				dataTudizongti.setCity(request.getParameter("city"));
				dataTudizongti.setGuihuajianzhumianji(Integer.parseInt(request
						.getParameter("guihuajianzhumianji")));
				dataTudizongti.setJiansheyongdimianji(Integer.parseInt(request
						.getParameter("jiansheyongdimianji")));
				dataTudizongti.setPingjunyijialv(Integer.parseInt(request
						.getParameter("pingjunyijialv")));
				dataTudizongti.setTudichurangjin(Integer.parseInt(request
						.getParameter("tudichurangjin")));
				dataTudizongti.setYongtu(request.getParameter("yongtu"));
				dataTudizongti.setZongshu(Integer.parseInt(request
						.getParameter("zongshu")));
				bool=dataTudizongtiService.updateTudizongti(dataTudizongti);
				result_Map.put("success", bool);
			}
		} else if ("data_xinjianzhuzhai".equals(dmsm)) {
			if (hid == "") {
				DataXinjianzhuzhai dataXinjianzhuzhai = new DataXinjianzhuzhai();
				dataXinjianzhuzhai.setShijian(request.getParameter("shijian"));
				dataXinjianzhuzhai.setCity(request.getParameter("city"));
				dataXinjianzhuzhai.setXiaoshoujiage(Integer.parseInt(request
						.getParameter("xiaoshoujiage")));
				dataXinjianzhuzhai.setXiaoshoumianji(Integer.parseInt(request
						.getParameter("xiaoshoumianji")));
				dataXinjianzhuzhai.setXiaoshoutaoshu(Integer.parseInt(request
						.getParameter("xiaoshoutaoshu")));
				dataXinjianzhuzhai.setUserid(Integer.parseInt(request
						.getSession().getAttribute("userId").toString()));
				bool=dataXinjianzhuzhaiService.saveXinjianzhuzhai(dataXinjianzhuzhai);
				result_Map.put("success", bool);
			} else {
				DataXinjianzhuzhai dataXinjianzhuzhai = new DataXinjianzhuzhai();
				dataXinjianzhuzhai.setId(Integer.parseInt(request
						.getParameter("id")));
				dataXinjianzhuzhai.setUserid(Integer.parseInt(request
						.getSession().getAttribute("userId").toString()));
				dataXinjianzhuzhai.setShijian(request.getParameter("shijian"));
				dataXinjianzhuzhai.setCity(request.getParameter("city"));
				dataXinjianzhuzhai.setXiaoshoujiage(Integer.parseInt(request
						.getParameter("xiaoshoujiage")));
				dataXinjianzhuzhai.setXiaoshoumianji(Integer.parseInt(request
						.getParameter("xiaoshoumianji")));
				dataXinjianzhuzhai.setXiaoshoutaoshu(Integer.parseInt(request
						.getParameter("xiaoshoutaoshu")));
				bool=dataXinjianzhuzhaiService.updateXinjianzhuzhai(dataXinjianzhuzhai);
				result_Map.put("success", bool);
			}
		}
		printWriter.write(JsonUtil.jsonObject(result_Map, null, null));
	}

	/**
	 * 删除数据
	 * @param request
	 * @param response
	 * @param printWriter
	 */
	@SystemControllerLog(description = "删除")
	@RequestMapping("/delData")
	public void delDataXinjianzhuzhai(HttpServletRequest request,
			HttpServletResponse response, PrintWriter printWriter) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		String dmsm = request.getParameter("dmsm");
		if ("data_ershouzhuzhai".equals(dmsm)) {
			if (id != 0) {
				boolean bool = dataErshouzhuzhaiService.deleteErshouzhuzhai(id);
				resultMap.put("success", bool);
			} else {
				resultMap.put("success", "false");
			}
		} else if ("data_jiagezhishu".equals(dmsm)) {
			if (id != 0) {
				boolean bool = dataJiagezhishuService.deleteJiagezhishu(id);
				resultMap.put("success", bool);
			} else {
				resultMap.put("success", "false");
			}
		} else if ("data_zujinzhishu".equals(dmsm)) {
			if (id != 0) {
				System.out.println(id);
				boolean bool = dataZujinzhishuService.deleteZujinzhishu(id);
				resultMap.put("success", bool);
			} else {
				resultMap.put("success", "false");
			}
		} else if ("data_tudizongti".equals(dmsm)) {
			if (id != 0) {
				boolean bool = dataTudizongtiService.deleteTudizongti(id);
				resultMap.put("success", bool);
			} else {
				resultMap.put("success", "false");
			}
		} else if ("data_xinjianzhuzhai".equals(dmsm)) {
			if (id != 0) {
				boolean bool = dataXinjianzhuzhaiService
						.deleteXinjianzhuzhai(id);
				resultMap.put("success", bool);
			} else {
				resultMap.put("success", "false");
			}
		}
		printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * 数据导入到excel表
	 * 
	 * @param request
	 * @param response
	 * @param printWriter
	 */
	@RequestMapping("/dcExcel")
	@SystemControllerLog(description = "数据导出到excel表")
	public void dcExcel(HttpServletRequest request, HttpServletResponse response) {
		String tableName=request.getParameter("tName");
		System.out.println(tableName);
		try {
			response.setContentType("application/vnd.ms-excel");
			OutputStream os = response.getOutputStream();
			String path = request.getSession().getServletContext()
					.getRealPath("");
			String fileName = path + "\\1.xls";
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			if ("data_ershouzhuzhai".equals(tableName)) {
				String[] tableHead = new String[4];
				String sheetName = "二手住宅量价走势";
				tableHead[0] = "时间";
				tableHead[1] = "城市";
				tableHead[2] = "成交套数";
				tableHead[3] = "市场均价";
				List<DataErshouzhuzhai> list = dataErshouzhuzhaiService
						.findByHQLQuery("from DataErshouzhuzhai");
				List data_list = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> data_map = new HashMap<String, Object>();
					for (int j = 0; j < tableHead.length; j++) {
						String key = "call" + i + "_" + j;
						String value = "";
						if (j == 0) {
							value = list.get(i).getShijian() + "";
						} else if (j == 1) {
							value = list.get(i).getCity() + "";
						} else if (j == 2) {
							value = list.get(i).getChengjiaotaoshu() + "";
						} else if (j == 3) {
							value = list.get(i).getShichangjunjia() + "";
						}

						data_map.put(key, value);
					}
					data_list.add(data_map);
				}
				ExcelUtil.saveExcel("", data_list, tableHead, fos, sheetName);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", "true");
				resultMap.put("fileName", fileName);
				os.write(JsonUtil.jsonObject(resultMap, null, null).getBytes());
				os.flush();
				os.close();
			} else if ("data_jiagezhishu".equals(tableName)) {
				String[] tableHead = new String[5];
				String sheetName = "房地产市场指数价格指数";
				tableHead[0] = "时间";
				tableHead[1] = "城市";
				tableHead[2] = "物业类型";
				tableHead[3] = "价格指数";
				tableHead[4] = "环比";
				List<DataJiagezhishu> list = dataJiagezhishuService
						.findByHQLQuery("from DataJiagezhishu");
				List data_list = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> data_map = new HashMap<String, Object>();
					for (int j = 0; j < tableHead.length; j++) {
						String key = "call" + i + "_" + j;
						String value = "";
						if (j == 0) {
							value = list.get(i).getShijian() + "";
						} else if (j == 1) {
							value = list.get(i).getCity() + "";
						} else if (j == 2) {
							value = list.get(i).getWuyeleixing() + "";
						} else if (j == 3) {
							value = list.get(i).getJiagezhishu() + "";
						} else if (j == 4) {
							value = list.get(i).getHuanbi() + "";
						}

						data_map.put(key, value);
					}
					data_list.add(data_map);
				}
				ExcelUtil.saveExcel("", data_list, tableHead, fos, sheetName);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", "true");
				resultMap.put("fileName", fileName);
				os.write(JsonUtil.jsonObject(resultMap, null, null).getBytes());
				os.flush();
				os.close();
			} else if ("data_zujinzhishu".equals(tableName)) {
				String[] tableHead = new String[5];
				String sheetName = "房地产市场指数租金指数";
				tableHead[0] = "时间";
				tableHead[1] = "城市";
				tableHead[2] = "物业类型";
				tableHead[3] = "租金指数";
				tableHead[4] = "环比";
				List<DataZujinzhishu> list = dataZujinzhishuService
						.findByHQLQuery("from DataZujinzhishu");
				List data_list = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> data_map = new HashMap<String, Object>();
					for (int j = 0; j < tableHead.length; j++) {
						String key = "call" + i + "_" + j;
						String value = "";
						if (j == 0) {
							value = list.get(i).getShijian() + "";
						} else if (j == 1) {
							value = list.get(i).getCity() + "";
						} else if (j == 2) {
							value = list.get(i).getWuyeleixing() + "";
						} else if (j == 3) {
							value = list.get(i).getZujinzhishu() + "";
						} else if (j == 4) {
							value = list.get(i).getHuanbi() + "";
						}

						data_map.put(key, value);
					}
					data_list.add(data_map);
				}
				ExcelUtil.saveExcel("", data_list, tableHead, fos, sheetName);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", "true");
				resultMap.put("fileName", fileName);
				os.write(JsonUtil.jsonObject(resultMap, null, null).getBytes());
				os.flush();
				os.close();
			} else if ("data_tudizongti".equals(tableName)) {
				String[] tableHead = new String[8];
				String sheetName = "土地总体成交情况";
				tableHead[0] = "时间";
				tableHead[1] = "城市";
				tableHead[2] = "用途";
				tableHead[3] = "总数";
				tableHead[4] = "土地出让金";
				tableHead[5] = "建设用地面积";
				tableHead[6] = "规划建筑面积";
				tableHead[7] = "平均溢价率";
				List<DataTudizongti> list = dataTudizongtiService
						.findByHQLQuery("from DataTudizongti");
				List data_list = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> data_map = new HashMap<String, Object>();
					for (int j = 0; j < tableHead.length; j++) {
						String key = "call" + i + "_" + j;
						String value = "";
						if (j == 0) {
							value = list.get(i).getShijian() + "";
						} else if (j == 1) {
							value = list.get(i).getCity() + "";
						} else if (j == 2) {
							value = list.get(i).getYongtu() + "";
						} else if (j == 3) {
							value = list.get(i).getZongshu() + "";
						} else if (j == 4) {
							value = list.get(i).getTudichurangjin() + "";
						} else if (j == 5) {
							value = list.get(i).getJiansheyongdimianji() + "";
						} else if (j == 6) {
							value = list.get(i).getGuihuajianzhumianji() + "";
						} else if (j == 7) {
							value = list.get(i).getPingjunyijialv() + "";
						}

						data_map.put(key, value);
					}
					data_list.add(data_map);
				}
				ExcelUtil.saveExcel("", data_list, tableHead, fos, sheetName);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", "true");
				resultMap.put("fileName", fileName);
				os.write(JsonUtil.jsonObject(resultMap, null, null).getBytes());
				os.flush();
				os.close();
			} else if ("data_xinjianzhuzhai".equals(tableName)) {
				String[] tableHead = new String[5];
				String sheetName = "二手住宅量价走势";
				tableHead[0] = "时间";
				tableHead[1] = "城市";
				tableHead[2] = "销售套数";
				tableHead[3] = "销售面积";
				tableHead[4] = "销售价格";
				List<DataXinjianzhuzhai> list = dataXinjianzhuzhaiService
						.findByHQLQuery("from DataXinjianzhuzhai");
				List data_list = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> data_map = new HashMap<String, Object>();
					for (int j = 0; j < tableHead.length; j++) {
						String key = "call" + i + "_" + j;
						String value = "";
						if (j == 0) {
							value = list.get(i).getShijian() + "";
						} else if (j == 1) {
							value = list.get(i).getCity() + "";
						} else if (j == 2) {
							value = list.get(i).getXiaoshoutaoshu() + "";
						} else if (j == 3) {
							value = list.get(i).getXiaoshoumianji() + "";
						} else if (j == 4) {
							value = list.get(i).getXiaoshoujiage() + "";
						}

						data_map.put(key, value);
					}
					data_list.add(data_map);
				}
				ExcelUtil.saveExcel("", data_list, tableHead, fos, sheetName);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", "true");
				resultMap.put("fileName", fileName);
				os.write(JsonUtil.jsonObject(resultMap, null, null).getBytes());
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 下载模板
	@RequestMapping("/xiazai")
	public void downloadexcel(HttpServletRequest request,HttpServletResponse response) {
		String tableName=request.getParameter("fileName");
		String fileName = "/DataTemplate/"+tableName+".xlsx";
		OutputStream out = null;
		String path = request.getSession().getServletContext().getRealPath("/"); 
		try {
			InputStream inStream = new FileInputStream(path+fileName);
			tableName = new String(tableName.getBytes("ISO-8859-1"),"UTF-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+tableName); 
			out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0){
            	out.write(b, 0, len);
            }
            out.flush();
            out.close();
        	inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向数据库中导入数据
	 * 
	 * @param request
	 * @param response
	 * @param printWriter
	 */

	
	@RequestMapping("/drExcel")
	@SystemControllerLog(description = "向数据库中导入数据")
	public void drExcel(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter) {
		Map<String,Object> result_map=new HashMap<String,Object>();
		String errorInfo = "";     //错误信息提示
		try {
			List<String> lists = upload(request,response);    //获取文件列表
			
			String url = request.getSession().getServletContext().getRealPath("/")+"upload";   //路径
			String tableName = lists.get(0);   //表名
			String fileName = lists.get(1);   //文件名
			Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());     //操作人
			
			Integer errorRow = saveObject(tableName,fileName,url,userId);
			File file = new File(url+"/"+fileName);        //清空服务器中上传的文件
			file.delete();
			if(0 != errorRow){
				if(-1 != errorRow){
					errorInfo = "第"+errorRow+"行数据出现异常，此行及此行后的数据未能成功插入！";
				}else{
					errorInfo = "导入出错！";
				}
			}else{
				errorInfo = "导入成功！";
			}
		} catch (Exception e) {
			errorInfo = "导入出错！";
		}
		printWriter.write("<script>alert('"+errorInfo+"');</script>");    //写回提示
		printWriter.flush();
        printWriter.close();
	}

	@SystemControllerLog(description = "解析表格数据存入对象")
	public Integer saveObject(String tableName,String fileName,String url,Integer userId) {
		ExcelUtil eu = new ExcelUtil();      //解析数据工具
		Integer errorRow = 0;    //报错行数
		
		try {
			List<Map<String, String>> list = eu.excelPutIn(url + "/" + fileName);
			if("data_ershouzhuzhai".equals(tableName)){      //二手住宅
				for (int i = 1; i < list.size(); i++) {
					try {
						Map<String, String> map = list.get(i);
						DataErshouzhuzhai ershouzhuzhai = new DataErshouzhuzhai();
						ershouzhuzhai.setUserid(userId);
						ershouzhuzhai.setShijian(map.get("call" + i + "_" + 0));
						ershouzhuzhai.setCity(map.get("call" + i + "_" + 1));
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 2))) {
							ershouzhuzhai.setChengjiaotaoshu(Integer.parseInt(map.get("call" + i + "_" + 2)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
							ershouzhuzhai.setShichangjunjia(Integer.parseInt(map.get("call" + i + "_" + 3)));
						}
						dataErshouzhuzhaiService.saveErshouzhuzhai(ershouzhuzhai);
					} catch (Exception e) {
						errorRow = i;
						break;
					}
				}
			}else if("data_jiagezhishu".equals(tableName)){    //价格指数
				for (int i = 1; i < list.size(); i++) {
					try {
						Map<String, String> map = list.get(i);
						DataJiagezhishu jiagezhishu = new DataJiagezhishu();
						jiagezhishu.setUserid(userId);
						jiagezhishu.setShijian(map.get("call" + i + "_" + 0));
						jiagezhishu.setCity(map.get("call" + i + "_" + 1));
						jiagezhishu.setWuyeleixing(map.get("call" + i + "_" + 2));
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
							jiagezhishu.setJiagezhishu(Integer.parseInt(map.get("call" + i + "_" + 3)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 4))) {
							jiagezhishu.setHuanbi(Integer.parseInt(map.get("call" + i + "_" + 4)));
						}
						dataJiagezhishuService.saveJiagezhishu(jiagezhishu);
					} catch (Exception e) {
						errorRow = i;
						break;
					}
				}
			}else if("data_zujinzhishu".equals(tableName)){     //租金指数
				for (int i = 1; i < list.size(); i++) {
					try {
						Map<String, String> map = list.get(i);
						DataZujinzhishu zujinzhishu = new DataZujinzhishu();
						zujinzhishu.setUserid(userId);
						zujinzhishu.setShijian(map.get("call" + i + "_" + 0));
						zujinzhishu.setCity(map.get("call" + i + "_" + 1));
						zujinzhishu.setWuyeleixing(map.get("call" + i + "_" + 2));
						
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
							zujinzhishu.setZujinzhishu(Integer.parseInt(map.get("call" + i + "_" + 3)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 4))) {
							zujinzhishu.setHuanbi(Integer.parseInt(map.get("call" + i + "_" + 4)));
						}
						dataZujinzhishuService.saveZujinzhishu(zujinzhishu);
					} catch (Exception e) {
						errorRow = i;
						break;
					}
				}
			}else if("data_tudizongti".equals(tableName)){     //土地总体
				for (int i = 1; i < list.size(); i++) {
					try {
						Map<String, String> map = list.get(i);
						DataTudizongti tudizongti = new DataTudizongti();
						tudizongti.setUserid(userId);
						tudizongti.setShijian(map.get("call" + i + "_" + 0));
						tudizongti.setCity(map.get("call" + i + "_" + 1));
						tudizongti.setYongtu(map.get("call" + i + "_" + 2));
						
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
							tudizongti.setZongshu(Integer.parseInt(map.get("call" + i + "_" + 3)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 4))) {
							tudizongti.setTudichurangjin(Integer.parseInt(map.get("call" + i + "_" + 4)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 5))) {
							tudizongti.setJiansheyongdimianji(Integer.parseInt(map.get("call" + i + "_" + 5)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 6))) {
							tudizongti.setGuihuajianzhumianji(Integer.parseInt(map.get("call" + i + "_" + 6)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 7))) {
							tudizongti.setPingjunyijialv(Integer.parseInt(map.get("call" + i + "_" + 7)));
						}
						dataTudizongtiService.saveTudizongti(tudizongti);
					} catch (Exception e) {
						errorRow = i;
						break;
					}
				}
			}else if("data_xinjianzhuzhai".equals(tableName)){     //新建住宅
				for (int i = 1; i < list.size(); i++) {
					try {
						Map<String, String> map = list.get(i);
						DataXinjianzhuzhai xinjianzhuzhai = new DataXinjianzhuzhai();
						xinjianzhuzhai.setUserid(userId);     //操作人
						xinjianzhuzhai.setShijian(map.get("call" + i + "_" + 0));     //时间
						xinjianzhuzhai.setCity(map.get("call" + i + "_" + 1));        //城市
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 2))) {
							xinjianzhuzhai.setXiaoshoutaoshu(Integer.parseInt(map.get("call" + i + "_" + 2)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
							xinjianzhuzhai.setXiaoshoumianji(Integer.parseInt(map.get("call" + i + "_" + 3)));
						}
						if (StringUtils.isNotBlank(map.get("call" + i + "_" + 4))) {
							xinjianzhuzhai.setXiaoshoujiage(Integer.parseInt(map.get("call" + i + "_" + 4)));
						}
						dataXinjianzhuzhaiService.saveXinjianzhuzhai(xinjianzhuzhai);    //存入数据库
					} catch (Exception e) {
						errorRow = i;
						break;
					}
				}
			}else{
				//不做处理
			}
			System.gc();
			return errorRow;
		} catch (Exception e) {
			if(0 == errorRow){
				errorRow = -1;
				return errorRow;
			}else{
				return errorRow;
			}
		}
	}
	
//	@RequestMapping("/drExcel")
//	@SystemControllerLog(description = "向数据库中导入数据")
//	public void drExcel(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter) {
//		Map<String,Object> result_map=new HashMap<String,Object>();
//		List<String> lists = upload(request,response);
//		ExcelUtil eu = new ExcelUtil();
//		StringBuffer sb = new StringBuffer();
//		sb.append(lists.get(0));
//		sb.append(".xlsx");
//		String url = request.getSession().getServletContext().getRealPath("/")+"upload"; 
//		List<String> fileName_list = FileUploadUtil.getFilesName(url);
//		if ("data_ershouzhuzhai".equals(lists.get(0))) {
//			for (String str : fileName_list) {
//				try {
//					List<Map<String, String>> list = eu.excelPutIn(url + "\\" + str);
//					for (int i = 1; i < list.size(); i++) {
//						Map<String, String> map = list.get(i);
//						DataErshouzhuzhai ershouzhuzhai = new DataErshouzhuzhai();
//						ershouzhuzhai.setShijian(map.get("call" + i + "_" + 0));
//						ershouzhuzhai.setCity(map.get("call" + i + "_" + 1));
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 2))) {
//
//							ershouzhuzhai.setChengjiaotaoshu(Integer
//									.parseInt(map.get("call" + i + "_" + 2)));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 3))) {
//							ershouzhuzhai.setShichangjunjia(Integer
//									.parseInt(map.get("call" + i + "_" + 3)));
//						}
//						boolean bool=dataErshouzhuzhaiService.saveErshouzhuzhai(ershouzhuzhai);
//						result_map.put("success", bool);
//					}
//				} catch (Exception e) {
//					// logger.error(e.getMessage());
//					continue;
//				}
//			}
//			System.gc();
//		} else if ("data_jiagezhishu".equals(lists.get(0))) {
//			for (String str : fileName_list) {
//				try {
//					List<Map<String, String>> list = eu.excelPutIn(url + "\\" + str);
//					for (int i = 1; i < list.size(); i++) {
//						Map<String, String> map = list.get(i);
//						DataJiagezhishu jiagezhishu = new DataJiagezhishu();
//						jiagezhishu.setShijian(map.get("call" + i + "_" + 0));
//						jiagezhishu.setCity(map.get("call" + i + "_" + 1));
//						jiagezhishu.setWuyeleixing(map
//								.get("call" + i + "_" + 2));
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 3))) {
//							jiagezhishu.setJiagezhishu(Integer.parseInt(map
//									.get("call" + i + "_" + 3)));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 4))) {
//							jiagezhishu.setHuanbi(Integer.parseInt(map
//									.get("call" + i + "_" + 4)));
//						}
//						dataJiagezhishuService.saveJiagezhishu(jiagezhishu);
//					}
//				} catch (Exception e) {
//					// logger.error(e.getMessage());
//					continue;
//				}
//			}
//			System.gc();
//		} else if ("data_zujinzhishu".equals(lists.get(0))) {
//			for (String str : fileName_list) {
//				try {
//					List<Map<String, String>> list = eu.excelPutIn(url + "\\" + str);
//					for (int i = 1; i < list.size(); i++) {
//						Map<String, String> map = list.get(i);
//						DataZujinzhishu zujinzhishu = new DataZujinzhishu();
//						zujinzhishu = (DataZujinzhishu) list.get(0);
//						zujinzhishu.setShijian(map.get("call" + i + "_" + 0));
//						zujinzhishu.setCity(map.get("call" + i + "_" + 1));
//						zujinzhishu.setWuyeleixing(map
//								.get("call" + i + "_" + 2));
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 3))) {
//							zujinzhishu.setZujinzhishu(Integer.parseInt(map
//									.get("call" + i + "_" + 3)));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 4))) {
//							zujinzhishu.setHuanbi(Integer.parseInt(map
//									.get("call" + i + "_" + 4)));
//						}
//						dataZujinzhishuService.saveZujinzhishu(zujinzhishu);
//					}
//				} catch (Exception e) {
//					// logger.error(e.getMessage());
//					continue;
//				}
//			}
//			System.gc();
//		} else if ("data_tudizongti".equals(lists.get(0))) {
//			for (String str : fileName_list) {
//				try {
//					List<Map<String, String>> list = eu.excelPutIn(url + "\\"
//							+ str);
//					for (int i = 1; i < list.size(); i++) {
//						Map<String, String> map = list.get(i);
//						DataTudizongti tudizongti = new DataTudizongti();
//						tudizongti.setShijian(map.get("call" + i + "_" + 0));
//						tudizongti.setCity(map.get("call" + i + "_" + 1));
//						tudizongti.setYongtu(map.get("call" + i + "_" + 2));
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 3))) {
//							tudizongti.setZongshu(Integer.parseInt(map
//									.get("call" + i + "_" + 3)));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 4))) {
//							tudizongti.setTudichurangjin(Integer
//									.parseInt("call" + i + "_" + 4));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 5))) {
//							tudizongti.setJiansheyongdimianji(Integer
//									.parseInt("call" + i + "_" + 5));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 6))) {
//							tudizongti.setGuihuajianzhumianji(Integer
//									.parseInt("call" + i + "_" + 6));
//						}
//						if (StringUtils.isNotBlank(map
//								.get("call" + i + "_" + 7))) {
//							tudizongti.setPingjunyijialv(Integer
//									.parseInt("call" + i + "_" + 7));
//						}
//						dataTudizongtiService.saveTudizongti(tudizongti);
//					}
//				} catch (Exception e) {
//					// logger.error(e.getMessage());
//					continue;
//				}
//			}
//			System.gc();
//		} else if ("data_xinjianzhuzhai".equals(lists.get(0))) {
//			try {
//				List<Map<String, String>> list = eu.excelPutIn(url + "/" + fileName_list.get(1).toString());
//				for (int i = 0; i <= list.size(); i++) {
//					Map<String, String> map = list.get(i);
//					DataXinjianzhuzhai xinjianzhuzhai = new DataXinjianzhuzhai();
//					xinjianzhuzhai = (DataXinjianzhuzhai) list.get(0);
//					xinjianzhuzhai.setUserid(Integer.parseInt(request.getSession().getAttribute("userId").toString()));   //userID
//					xinjianzhuzhai.setShijian(map.get("call" + i + "_" + 0));     //时间
//					xinjianzhuzhai.setCity(map.get("call" + i + "_" + 1));        //城市
//					if (StringUtils.isNotBlank(map.get("call" + i + "_" + 2))) {
//						xinjianzhuzhai.setXiaoshoutaoshu(Integer.parseInt(map.get("call" + i + "_" + 2)));
//					}
//					if (StringUtils.isNotBlank(map.get("call" + i + "_" + 3))) {
//						xinjianzhuzhai.setXiaoshoumianji(Integer.parseInt(map.get("call" + i + "_" + 3)));
//					}
//					if (StringUtils.isNotBlank(map.get("call" + i + "_" + 4))) {
//						xinjianzhuzhai.setXiaoshoujiage(Integer.parseInt(map.get("call" + i + "_" + 4)));
//					}
//					dataXinjianzhuzhaiService.saveXinjianzhuzhai(xinjianzhuzhai);    //存入数据库
//				}
//			} catch (Exception e) {
//				result_map.put("msg", "导入失败");
//			}
//			System.gc();
//		}
//		printWriter.write("<script>a</script>");
//		printWriter.flush();
//        printWriter.close();
//	}

	public List<String> upload(HttpServletRequest request,HttpServletResponse response) {
		SmartUpload mySmartUpload = new SmartUpload();  
		List<String> list=new ArrayList<String>();
		String fileName = "";
		try {
			mySmartUpload.initialize(servletConfig, request, response);
			mySmartUpload.upload(); 
			String tableName = new String(mySmartUpload.getRequest().getParameter("tableName").toCharArray());
			request.setAttribute("tableName", tableName);
	        for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {  
		        com.jspsmart.upload.File myfile = mySmartUpload.getFiles().getFile(i);  
		        fileName = myfile.getFileName();  
		        //保存  	
		        mySmartUpload.save("/upload");  
	        }  
	        list.add(tableName);
	        list.add(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return list;
	}

}

