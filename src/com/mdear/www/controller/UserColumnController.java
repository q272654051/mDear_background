package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.IUserColumnService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.User;
import com.mdear.www.vo.UserColumn;

/**
 * @author dengbojing
 * @date 2015-10-22
 * @description
 */
@Controller
@RequestMapping("userColumnController")
public class UserColumnController {
    
    @Resource
    private IUserService userService;
    @Resource
    private IUserColumnService userColService;
    
    /**
     * @description 获取不同资讯类型的审核人
     * @param request
     * @param response
     */
    @RequestMapping("/getChildNodes")
    public void getUserByColumnId(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter){
        String column_id = request.getParameter("id");
        List<User> user_list = new ArrayList<User>();
        List<UserColumn> userCol_list = userColService.findByHQLQuery("from UserColumn where column_id ="+column_id);
        for(UserColumn userCol : userCol_list){
            User user = userService.findById(userCol.getUser_id());
            user_list.add(user);
        }
        printwriter.print(JsonUtil.jsonArray(user_list, null, null));
        printwriter.flush();
        printwriter.close();
    }
    
    public void getColumnByUseriId(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter){
        String user_id = request.getParameter("id");
        List<UserColumn> userCol_list = userColService.findByHQLQuery("from UserColumn where user_id ="+user_id);
        
    }
}
