package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.abel533.echarts.json.GsonOption;
import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.controller.service.StatisticsService;
import com.mdear.www.service.ICodeService;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.service.INewsService;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.ColumnTable;

/**
 * @author dengbojing
 * @date 2015-10-27
 * @description
 */
@Controller
@RequestMapping("statisticsController")
public class StatisticsController {
    @Resource
    private INewsService newsService;
    @Resource
    private IColumnTableService colService;
    @Resource
    private ICodeService codeService;
    
    /**
     * 统计中心
     * 
     * @param request
     * @param response
     * @param printWriter
     * @param news
     * @return
     */
    @SystemControllerLog(description = "统计中心")
    @RequestMapping("/countContre")
    public ModelAndView countContre(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
        return new ModelAndView("statistics/statistics_center");
    }
    
    
    
    /**
     * 
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("getPublishChart")
    public void getPublishChart(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
        String cycle = request.getParameter("cycle");
        List<String> con_list = StatisticsService.getPublishCondition(cycle);
        List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1005");
        List<String> date_list = StatisticsService.getDateList(cycle);
        List count_list = newsService.getPublish(con_list.get(0),con_list.get(1));
        Map<String,Map<String,String>> data_map = new TreeMap<String,Map<String,String>>();
        List<String> legend_list = new ArrayList<String>();
        
        Map<String,String> temp_map = new HashMap<String,String>();
        String date_temp = "";
        for(Iterator iterator = count_list.iterator();iterator.hasNext();){
            boolean flag = false;
            Object[] obj=(Object[])iterator.next();
            String date = obj[2].toString();
            if(date_temp == ""){
                date_temp = date;
            }else if(!date_temp.equals(date)){
                flag = true;
                temp_map = new HashMap<String,String>();
                date_temp = date;
            }
            
            for(Code code : code_list){
                if(code.getDmz().equals(Integer.parseInt(obj[0].toString()))){
                    temp_map.put(code.getDmsm(), obj[1].toString());
                }
                data_map.put(date_temp, temp_map);
            }
        }
        for(String key : date_list){
            Map<String,String> data_temp_map = data_map.get(key);
            if(data_temp_map == null || data_temp_map.size() == 0){
                data_temp_map = new TreeMap<String,String>();
                for(Code code : code_list){
                    data_temp_map.put(code.getDmsm(), 0+"");
                }
                data_map.put(key, data_temp_map);
            }else{
                for(Code code : code_list){
                    String str = data_temp_map.get(code.getDmsm());
                    if(StringUtils.isBlank(str)){
                        data_temp_map.put(code.getDmsm(), 0+"");
                    }
                }
            }
        }
        for(Code code : code_list){
            legend_list.add(code.getDmsm());
        }
        GsonOption option = StatisticsService.setBarChart(data_map, date_list, legend_list,StatisticsService.getTitle(cycle).get("bar_title"));
        printWriter.print(option);
    }
    
    
    /**
     * @description 本周/月/季/年新增数据统计
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("getIncrementChart")
    public void getIncrementChart(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
        String cycle = request.getParameter("cycle");
        String name = request.getParameter("name");
        List<String> con_list = StatisticsService.getIncrementCondition(cycle);
        List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable c where c.pid = 1 and status = 1");
        List count_list = new ArrayList();
        if(StringUtils.isNotBlank(name)){
            col_list = colService.findByHQLQuery("from ColumnTable c where status = 1");
            col_list = StatisticsService.getColList(col_list,name);
            count_list = newsService.getChildIncrement(con_list.get(0),con_list.get(1));
        }else{
            count_list = newsService.getIncrement(con_list.get(0),con_list.get(1));
        }
        
        Map<String,Map<String,String>> data_map = new TreeMap<String,Map<String,String>>();
        List<String> date_list = StatisticsService.getDateList(cycle);
        List<String> legend_list = new ArrayList<String>();
        String date_temp = "";
        Map<String,String> temp_map = new HashMap<String,String>();
        
        for(Iterator iterator = count_list.iterator();iterator.hasNext();){
            boolean flag = false;
            Object[] obj=(Object[])iterator.next();
            String date = obj[2].toString();
            if(date_temp == ""){
                date_temp = date;
            }else if(!date_temp.equals(date)){
                flag = true;
                temp_map = new HashMap<String,String>();
                date_temp = date;
            }
            
            for(ColumnTable col : col_list){
                if(col.getId().equals(Integer.parseInt(obj[0].toString()))){
                    temp_map.put(col.getColumnName(), obj[1].toString());
                }
                data_map.put(date_temp, temp_map);
            }
        }
        
        for(String key : date_list){
            Map<String,String> data_temp_map = data_map.get(key);
            if(data_temp_map == null || data_temp_map.size() == 0){
                data_temp_map = new TreeMap<String,String>();
                for(ColumnTable col : col_list){
                    data_temp_map.put(col.getColumnName(), 0+"");
                }
                data_map.put(key, data_temp_map);
            }else{
                for(ColumnTable col : col_list){
                    String str = data_temp_map.get(col.getColumnName());
                    if(StringUtils.isBlank(str)){
                        data_temp_map.put(col.getColumnName(), 0+"");
                    }
                }
            }
        }
        
        for(ColumnTable col : col_list){
            legend_list.add(col.getColumnName());
        }
        GsonOption option = StatisticsService.setPieChart(data_map, date_list, legend_list,StatisticsService.getTitle(cycle).get("pie_title"));
        printWriter.print(option);
    }
    
    @RequestMapping("getIncrementTable")
    public void getIncrementTable(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
        Map<String,Object> result_map = new HashMap<String,Object>();
        String cycle = request.getParameter("cycle");
        String name = request.getParameter("name");
        List<String> con_list = StatisticsService.getIncrementCondition(cycle);
        List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable c where c.pid = 1 and status = 1");
        List count_list = new ArrayList();
        List<Map<String,Map<String,String>>> data_map = new ArrayList<Map<String,Map<String,String>>>();
        if(StringUtils.isNotBlank(name)){
            col_list = colService.findByHQLQuery("from ColumnTable c where status = 1");
            col_list = StatisticsService.getColList(col_list,name);
            count_list = newsService.getChildIncrementTable(con_list.get(0),con_list.get(1));
        }else{
            count_list = newsService.getIncrementTable(con_list.get(0),con_list.get(1));
        }
        
        for(Iterator iterator = count_list.iterator();iterator.hasNext();){
            Object[] obj=(Object[])iterator.next();
            System.out.print(obj[0]+"-------------");
            System.out.print(obj[1]+"-------------");
            System.out.print(obj[2]+"-------------");
            System.out.println(obj[3]);
            for(ColumnTable col : col_list){
                if(col.getId().equals(obj[1])){
                    
                }
            }
        }
        
        result_map.put("col_list", col_list); 
        result_map.put("count_list", count_list); 
        result_map.put("success", true);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("getPublishtTable")
    public void getPublishtTable(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
            
        }
    }
