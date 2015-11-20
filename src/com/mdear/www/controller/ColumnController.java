package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.Var;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.vo.ColumnTable;
import com.mdear.www.vo.Role;
import com.mdear.www.vo.User;

/**
 * @author dengbojing
 * @date 2015-8-19
 * @description
 */
@Controller
@RequestMapping(value="columnController")
public class ColumnController {
    
    @Autowired
    private IColumnTableService colService;
    
    @RequestMapping("/getChildNodes")
    public void getChildNodes(HttpServletRequest request,HttpServletResponse response,PrintWriter writer,ColumnTable col){
        List<ColumnTable> col_list = colService.findChildNode(col);
        writer.write(JsonUtil.jsonArray(col_list, null, null));
        writer.flush();
        writer.close();
    }
    
    @RequestMapping("/getList")
    public ModelAndView getList(HttpServletRequest request,HttpServletResponse response,PrintWriter writer,ColumnTable col){
        ModelAndView mav = new ModelAndView("sys/type/type_list");
        List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable c where c.pid = 1");
        return mav.addObject("col_list", col_list);
    }
    
	/**
	 * 标记删除新闻类型
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param ColumnTable
	 */
	@RequestMapping("/deleteType")
	@SystemControllerLog(description="标记删除一级新闻类型")
	public void deleteType(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,ColumnTable col){
	    Map<String,Object> result_map = new HashMap<String,Object>();
	    int status = col.getStatus();
	    col = colService.findById(col.getClass(), col.getId());
	    col.setStatus(status);
	    boolean flag = colService.updateEntity(col);
	    result_map.put("success", flag);
	    if(flag){
	        result_map.put("msg", "删除成功");
	    }else{
	        result_map.put("msg", "删除失败");
	    }
	    printwriter.print(JsonUtil.jsonObject(result_map, null, null));
	    printwriter.flush();
	    printwriter.close();
	}
	
	/**
	 * @description 转向添加修改新闻类型界面
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param ColumnTable
	 * @return
	 */
	@RequestMapping("/getType")
	public ModelAndView getType(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,ColumnTable col){
		ColumnTable colu = new ColumnTable();
	    try {
	    	String operation = "新增";
            if(col.getId() != null && col.getId() !=0 ){
            	//查找一级类型信息
            	colu = colService.findById(col.getClass(), col.getId());
            	//查找对应的二级类型信息
                List<ColumnTable> col_child_list = colService.findAlllist("select * from column_a c where c.pid = '"+col.getId()+"'");
                operation = "修改";
                request.setAttribute("colu", colu);
                request.setAttribute("col_child_list", col_child_list);
            }
            return new ModelAndView("sys/type/type_edit").addObject("operation", operation);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return null;
	}
	
	@RequestMapping("/addType")
	@SystemControllerLog(description="添加/保存新闻类型")
	public void addType(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,ColumnTable col){
	    Map<String,Object> result_map = new HashMap<String,Object>();
	    try {
            boolean f;
            boolean f_second;
            
        	String columnNameStr = col.getColumnName();
            String[] columnNameStrArray = columnNameStr.split(",");
            String columnName_first = "";
            
            if(col.getId() != null && col.getId() !=0 ){
            	ColumnTable col_old =  new ColumnTable();
            	col_old = colService.findById(col.getId());
            	col_old.setId(col.getId());
            	
                String columnName_second = "";
                if(columnNameStrArray.length>0){
                	columnName_first = columnNameStrArray[0];
                }
            	col_old.setColumnName(columnName_first);
                f = colService.saveOrUpdate(col_old);
                //--------------------------------
                //如果有二级类型--->保存
                if (columnNameStrArray.length>1) {
                	columnName_second = columnNameStrArray[1];
                	if (!"".equals(columnName_second)){
                     	ColumnTable colNew = new ColumnTable();
                         colNew.setPid(col.getId());
                         colNew.setStatus(1);
                         colNew.setColumnName(columnName_second);
                         f_second = colService.saveEntity(colNew);
     				}
				}
               
            }else{
            	col.setPid(1);    //新增一级名称类型时，将pid,status都设为1(一级菜单，可用状态)
            	col.setStatus(1);
            	if(columnNameStrArray.length>0){
                 	columnName_first = columnNameStrArray[0];
                }
            	col.setColumnName(columnName_first);
                f = colService.saveEntity(col);
            }
            result_map.put("success", f);
            if(f){
                //saveUserColumn(request, response, col);
                result_map.put("msg", "保存成功");
            }else{
                result_map.put("msg", "保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	    printwriter.print(JsonUtil.jsonObject(result_map, null, null));
	    printwriter.flush();
	    printwriter.close();
	}
	
	/**
	 * @description 修改二级类型菜单
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param ColumnTable
	 * @return
	 */
	@RequestMapping("/updateType")
	public void updateType(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter){
	    Map<String,Object> result_map = new HashMap<String,Object>();
	    String idStr = request.getParameter("id");
	    String columnNameStr = request.getParameter("columnName");
	    String pidStr = request.getParameter("pid");
	    String statusStr = request.getParameter("status");
	    Integer id = 0;
	    Integer pid = 0;
	    Integer status = 0;
	    
 	    if(StringUtils.isNotBlank(idStr)){
 	    	id = Integer.parseInt(idStr);
	    }
 	    if(StringUtils.isNotBlank(pidStr)){
 	    	pid = Integer.parseInt(pidStr);
	    }
 	    if (StringUtils.isNoneBlank(statusStr)) {
 	    	status = Integer.parseInt(statusStr);
		}
	    ColumnTable col = new ColumnTable();
	    col.setId(id);
	    col.setPid(pid);
	    col.setColumnName(columnNameStr);
	    col.setStatus(status);
	    boolean flag = colService.saveOrUpdate(col);
	    result_map.put("success", flag);
	    if(flag){
	        result_map.put("msg", "删除成功");
	    }else{
	        result_map.put("msg", "删除失败");
	    }
	    printwriter.print(JsonUtil.jsonObject(result_map, null, null));
	    printwriter.flush();
	    printwriter.close();
	}
}
