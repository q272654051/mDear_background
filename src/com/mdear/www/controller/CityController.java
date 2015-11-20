package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.ICityService;
import com.mdear.www.vo.CityTable;



@Controller
@RequestMapping(value="cityController")
public class CityController {
	 @Autowired
	    private  ICityService cityservive;
	    
	    @RequestMapping("/getChildNodes")
	    public void getChildNodes(HttpServletRequest request,HttpServletResponse response,PrintWriter writer,CityTable lal){
	        List<CityTable> city_list = cityservive.findChildNode(lal);
	        writer.write(JsonUtil.jsonArray(city_list, null, null));
	        writer.flush();
	        writer.close();
	    }
}
