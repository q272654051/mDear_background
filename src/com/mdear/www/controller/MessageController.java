package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.commons.util.WebConstants;
import com.mdear.www.service.ICodeService;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.service.IMessageService;
import com.mdear.www.service.INewsService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.Messagevo;
import com.mdear.www.vo.News;
import com.mdear.www.vo.User;

/**
 * 
 * @author liuzhichen
 *
 */
@Controller
@RequestMapping("messageController")
public class MessageController {
	
	@Resource
	private INewsService newsService;
	@Resource
	private IMessageService messageService;
	@Resource
	private IColumnTableService colService;
	@Resource
	private ICodeService codeService;
	@Resource
	private IUserService usersService;
	/**
	 * 新增个人消息
	 * @param request
	 * @param response
	 * @param writer
	 */
	@RequestMapping("/saveMessage")
	@SystemControllerLog(description="新增消息")
	public void saveMessage(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,News news){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		boolean bool=newsService.saveOrUpdate(news);
		resultMap.put("success", bool);
		printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
		printWriter.flush();
        printWriter.close();
	}
	/**
	 * 删除个人消息
	 * @param request
	 * @param response
	 * @param printWriter
	 * @param message
	 */
	@RequestMapping("/deleteMessage")
	@SystemControllerLog(description="删除个人消息")
	public void deleteMessage(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Messagevo message){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(message.getId() != null){
			boolean bool=messageService.deleteMessage(message.getId());
			resultMap.put("success", bool);
		}else{
			resultMap.put("success", "false");
		}
		printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
		printWriter.flush();
        printWriter.close();
	}
	/**
	 * 批量删除个人消息
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param ids
	 */
	@RequestMapping("/deleteMessageByList")
	@SystemControllerLog(description="批量删除消息")
	public void deleteMessageByList(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,String [] ids){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		 int i = 0;
	        String idValue = "";
	        if(ids != null && ids.length> 0){
	            for(String id : ids){
	                boolean bool = messageService.deleteMessage(id);
	                if(bool){
	                    i++;
	                }else{
	                    idValue += id+",";
	                }
	            }
	            resultMap.put("success", true);
	        }else{
	        	resultMap.put("successa", false);
	        }
	        resultMap.put(i+"", idValue);
	        printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
	        printWriter.flush();
	        printWriter.close();
	}
	/**
	 * 修改个人消息
	 * @param request
	 * @param response
	 * @param printWriter
	 * @param ids
	 */
	@RequestMapping("/updateMessage")
	@SystemControllerLog(description="添加或者更新资讯")
	public void updateMessage(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,String ids[]){
		Map<String,Object> resultMap = new HashMap<String,Object>();
        String status = request.getParameter("status");
        int i = 0;
        String idValue = "";
        if(ids != null && ids.length> 0){
            for(String id : ids){
            	Messagevo message=new Messagevo();
            	message.setId(Integer.parseInt(id));
                message = messageService.findById(message.getId());
                if(message != null){
                	message.setStatus(Integer.parseInt(status));
                    boolean bool = messageService.saveOrUpdate(message);
                    if(bool){
                        i++;
                    }else{
                        idValue += id+",";
                    }
                }
            }
        }
        resultMap.put(i+"", idValue);
        printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
        printWriter.flush();
        printWriter.close();
	}
	/**
	 * 分页获取个人消息
	 * @param request
	 * @param response
	 * @param printwriter
	 * @return
	 */
	@SystemControllerLog(description="分页获取消息")
	@RequestMapping("/getMessage")
	public ModelAndView getMessage(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Messagevo message){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<Integer,String> code_map = new HashMap<Integer,String>();
		//获取当前登录用户
		String userid=request.getSession().getAttribute("userId").toString();
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1001");
		for(Code c : code_list){
            code_map.put(c.getDmz(), c.getDmsm());	
        }
		String sql="from Messagevo m where m.shenherenid="+userid;
		List<Messagevo> message_list = messageService.findByHQLQuery(sql);
		return new ModelAndView("message/my_info")
		.addObject("message_list", message_list)
		.addObject("code_list", code_list);
	}
	
	/**
	 * 获取消息未读条数
	 * @param request
	 * @param response
	 * @param printWriter
	 * @param message
	 */
	@SystemControllerLog(description="获取消息未读条数")
	@RequestMapping("/unMessage")
	public void unMessage(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Messagevo message){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//获取当前登录用户
		String userid=request.getSession().getAttribute("userId").toString();
		Map<Integer,String> code_map = new HashMap<Integer,String>();
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1004");
		for(Code c : code_list){
            code_map.put(c.getDmz(), c.getDmsm());
        }
		String sql="from Messagevo m where m.shenherenid="+userid+"and m.messageStatus="+0;
		List<Messagevo> message_list=messageService.findByHQLQuery(sql);
		User users = usersService.findById(Integer.parseInt(userid));
		int sex = users.getGender();
		resultMap.put("data", message_list);
		resultMap.put("sex", sex);
		resultMap.put("success", "true");
		printWriter.write(JsonUtil.jsonObject(resultMap, null, null));
		printWriter.flush();
        printWriter.close();
	}
	
	@SystemControllerLog(description="更改未读消息状态")
	@RequestMapping("/updateStatus")
	public void updateStatus(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		String newsid=request.getParameter("newsid");
		Messagevo messagevo=new Messagevo();
			String sql="from Messagevo where newsid="+newsid;
			List<Messagevo> list =messageService.findByHQLQuery(sql);
			if(list.size()!=0){
				messagevo = list.get(0);
				messagevo.setMessageStatus(1);
				boolean bool=messageService.updateMessage(messagevo);
				resultMap.put("success", bool);
			}else{
				resultMap.put("success", false);
			}
			String data=JsonUtil.jsonObject(resultMap, null, null);
			printWriter.write(data);
			printWriter.flush();
			printWriter.close();
		
	}
}
