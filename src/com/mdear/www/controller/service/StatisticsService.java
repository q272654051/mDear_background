package com.mdear.www.controller.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Timeline;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.mdear.www.commons.util.FormatDateUtil;
import com.mdear.www.vo.ColumnTable;

/**
 * @author dengbojing
 * @date 2015-10-29
 * @description
 */
public class StatisticsService {

    /**
     * @description 生成带时间轴的饼状图
     * @param data_map
     * @param date_list
     * @param legend_list
     * @param name
     * @return
     */
    public static GsonOption setPieChart(Map<String,Map<String,String>> data_map,List<String> date_list,List<String> legend_list,String name){
        GsonOption option = new GsonOption();
        try {
            Timeline time_line = new Timeline();
            Label label = new Label();
            String formatter = "function(s){return s.slice(0, 7);}";
            //label.setFormatter(formatter);
            for(String str : date_list){
                time_line.data(str);
            }
            time_line.setLabel(label);
            
            
            List<Option> option_list = new ArrayList<Option>();
            GsonOption childOption = new GsonOption();
            Title title = new Title();
            title.text(name);
            Tooltip tooltip = new Tooltip();
            tooltip.trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
            Legend legend = new Legend();
            legend.orient(Orient.vertical);
            legend.x("left");
            legend.y("50");
            for(String str : legend_list){
                legend.data(str);
            }
            childOption.legend(legend);
            childOption.title(title);
            childOption.tooltip(tooltip);
            
            
            List<Series> pie_list = new ArrayList<Series>();
            for(String str : date_list){
                Pie pie = new Pie();
                Map<String,String> map = data_map.get(str);
                for(String type : map.keySet()){
                    Data data = new Data();
                    data.value(Integer.parseInt(map.get(type))).name(type);
                    pie.data(data);
                }
                pie.name(name);
                pie_list.add(pie);
            }
            if(pie_list.size() > 0){
                childOption.series(pie_list.get(0));
            }
            option_list.add(childOption);
            for(int i = 1; i < pie_list.size(); i++){
                GsonOption childOption1 = new GsonOption();
                childOption1.series(pie_list.get(i));
                option_list.add(childOption1);
                
            }
            option.options(option_list);
            option.setTimeline(time_line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return option;
    }
    
    /**
     * @description 生成柱状图
     * @param map
     * @param date_list
     * @param legend_list
     * @param name
     * @return
     */
    public static GsonOption setBarChart(Map<String,Map<String,String>> map,List<String> date_list,List<String> legend_list,String name){
        GsonOption option = new GsonOption();
        Title title = new Title();
        title.text(name);
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        Legend legend = new Legend();
        for(String str : legend_list){
            legend.data(str);
        }
        CategoryAxis xAxis = new CategoryAxis();
        ValueAxis yAxis = new ValueAxis();
        for(String str : date_list){
            xAxis.data(str);
        }
        List<Series> series_list = new ArrayList<Series>(); 
        for(String legend_str : legend_list){
            Bar bar = new Bar();
            bar.name(legend_str);
            for(String date : date_list){
                Map<String,String> temp_map = map.get(date);
                String data = temp_map.get(legend_str);
                bar.data(data);
            }
            series_list.add(bar);
        }
        option.series(series_list).title(title).tooltip(tooltip).legend(legend).xAxis(xAxis).yAxis(yAxis);
        return option;
    }
    
    
    /**
     * @description 获取新增资讯统计条件
     * @param cycle W week  M month S season Y year
     * @return
     */
    public static List<String> getIncrementCondition(String cycle){
        List<String> con_list = new ArrayList<String>();
        String date = "";
        String condition = "";
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        if("W".equals(cycle)){
            date = ",date_format(createdate,'%Y-%m-%d')";
            condition = " and date_format(createdate,'%Y-%m-%d') between date_add(date_format(now(),'%Y-%m-%d'),interval -"+week+" day) and date_add(date_format(now(),'%Y-%m-%d'),interval "+(7-week)+" day)";
        }else if("M".equals(cycle)){
            date = ",date_format(createdate,'%Y-%m')";
            condition = " and date_format(createdate,'%Y-%m') between date_format('"+year+"-01-01','%Y-%m') and date_format('"+year+"-12-31','%Y-%m')";
        }else if("S".equals(cycle)){
            date = ",date_format(createdate,'%Y-%m')";
            condition = " and date_format(createdate,'%Y-%m') = date_format(now(),'%Y-%m') ";
        }else if("Y".equals(cycle)){
            date = ",date_format(createdate,'%Y-%m')";
            condition = " and date_format(createdate,'%Y-%m') between date_format('"+year+"-01-01','%Y-%m') and date_format('"+year+"-12-31','%Y-%m')";
        }else{
            date = ",date_format(createdate,'%Y')";
        }
        con_list.add(0,date);
        con_list.add(1,condition);
        return con_list;
    }
    
    
    /**
     * @description 获取发布资讯统计条件
     * @param cycle W week  M month S season Y year
     * @return
     */
    public static List<String> getPublishCondition(String cycle){
        List<String> con_list = new ArrayList<String>();
        String date = "";
        String condition = "";
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        if("W".equals(cycle)){
            date = ",date_format(fabudate,'%Y-%m-%d')";
            condition = " and date_format(fabudate,'%Y-%m-%d') between date_add(date_format(now(),'%Y-%m-%d'),interval -"+week+" day) and date_add(date_format(now(),'%Y-%m-%d'),interval "+(7-week)+" day)";
        }else if("M".equals(cycle)){
            date = ",date_format(fabudate,'%Y-%m')";
            condition = " and date_format(fabudate,'%Y-%m') between date_format('"+year+"-01-01','%Y-%m') and date_format('"+year+"-12-31','%Y-%m')";
        }else if("S".equals(cycle)){
            date = ",date_format(fabudate,'%Y-%m')";
            condition = " and date_format(fabudate,'%Y-%m') = date_format(now(),'%Y-%m') ";
        }else if("Y".equals(cycle)){
            date = ",date_format(fabudate,'%Y-%m')";
            condition = " and date_format(fabudate,'%Y-%m') between date_format('"+year+"-01-01','%Y-%m') and date_format('"+year+"-12-31','%Y-%m')";
        }else{
            date = ",date_format(fabudate,'%Y')";
        }
        con_list.add(0,date);
        con_list.add(1,condition);
        return con_list;
    }
    
    /**
     * @description 根据周期获取时间列表
     * @param cycle
     * @return
     */
    public static List<String> getDateList(String cycle){
        List<String> date_list = new ArrayList<String>();
        if("W".equals(cycle)){
            List<Date> date_temp_list = FormatDateUtil.getWeek();
            for(int i = 0; i < date_temp_list.size(); i++){
                Date date = date_temp_list.get(i);
                date_list.add(FormatDateUtil.DateToShortStr(date));
            }
        }else if("M".equals(cycle)){
            date_list = FormatDateUtil.getMonth();
        }else if("S".equals(cycle)){
        }else if("Y".equals(cycle)){
            date_list = FormatDateUtil.getYear();
        }else{
        }
        return date_list;
    }
    
    
    public static Map<String,String> getTitle(String cycle){
        Map<String,String> title_map = new HashMap<String,String>();
        String pie_title = "";
        String bar_title = "";
        if("W".equals(cycle)){
            pie_title = "本周新增";
            bar_title = "本周发布";
        }else if("M".equals(cycle)){
            pie_title = "本月新增";
            bar_title = "本月发布";
        }else if("S".equals(cycle)){
            pie_title = "本季度新增";
            bar_title = "本季度发布";
        }else if("Y".equals(cycle)){
            pie_title = "全年新增";
            bar_title = "全年发布";
        }
        title_map.put("pie_title", pie_title);
        title_map.put("bar_title", bar_title);
        return title_map;
    }

    /**
     * @param col_list 类型列表
     * @param name  父类型名称
     * @return 子类型列表
     */
    public static List<ColumnTable> getColList(List<ColumnTable> col_list, String name) {
        int pid = 1;
        List<ColumnTable> return_list = new ArrayList<ColumnTable>();
        for(ColumnTable col : col_list){
            if(name.equals(col.getColumnName())){
                pid = col.getId();
                break;
            }
        }
        for(ColumnTable col : col_list){
            if(col.getPid() == pid){
                return_list.add(col);
            }
        }
        return return_list;
    }
}
