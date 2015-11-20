package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.ILableService;
import com.mdear.www.vo.Labletable;


@Controller
@RequestMapping(value="lableController")
public class LableController {
	 @Autowired
	    private  ILableService lableser;
	    
	    @RequestMapping("/getChildNodes")
	    public void getChildNodes(HttpServletRequest request,HttpServletResponse response,PrintWriter writer,Labletable lal){
	        List<Labletable> lable_list = lableser.findChildNode(lal);
	        writer.write(JsonUtil.jsonArray(lable_list, null, null));
	    }
}
