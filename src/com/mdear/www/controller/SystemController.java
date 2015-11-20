package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.service.IRoleService;
import com.mdear.www.service.IUserRoleService;
import com.mdear.www.vo.Role;
import com.mdear.www.vo.UserRole;

/**
 * @author dengbojing
 * @date 2015-9-14
 * @description
 */
@Controller
@RequestMapping("systemController")
public class SystemController {
    
    @Resource
    IUserRoleService userRoleService;
    @Resource
    IRoleService roleService;
    
    @RequestMapping("/system")
    public ModelAndView system(HttpServletRequest requeste,HttpServletResponse respone,PrintWriter printWriter){
        List<Role> role_list = roleService.findByHQLQuery("from Role");
        List<UserRole> user_role_list = userRoleService.findByHQLQuery("from UserRole");
        /*Map<String,String> user_role_map = new HashMap<String,String>();
        for(UserRole user_role : user_role_list){
            
        }*/
        return new ModelAndView("sys/link/link")
        .addObject("role_list", role_list)
        .addObject("user_role_list", user_role_list);
    }
}
