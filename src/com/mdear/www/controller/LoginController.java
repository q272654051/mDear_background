package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.commons.util.SessionOperateUtil;
import com.mdear.www.commons.util.WebConstants;
import com.mdear.www.service.IRoleMenuService;
import com.mdear.www.service.IUserRoleService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.RoleMenu;
import com.mdear.www.vo.User;
import com.mdear.www.vo.UserRole;

/**
 * @author djx
 * @date 2015-11-19
 * @description
 */

@Controller
@RequestMapping(value="loginController")
public class LoginController {
    @Resource
    IUserService userService;
    @Resource
    IUserRoleService userRoleService;
    @Resource
    IRoleMenuService roleMenuService;
    
    
    @RequestMapping("/tologin")
    public void login(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter,User user){
        Map<String,Object> result_map = new HashMap<String,Object>();
        try {
            List<RoleMenu> roleMenu_list = new ArrayList<RoleMenu>();
            //List<User> list = userService.findUserByNameAndPwd(user.getUserName(), "000000");
            Map<String,User> user_map = (Map<String,User>) SessionOperateUtil.getRequestAttribute(request, "user_map");
            String pwd_new = user.getPwd();
            user = user_map.get(user.getUserName());
            if(user != null && user.getId() != null){
                if(user.getStatus() == 1){
//                    user.setPwd(pwd);
//                    String content = userService.login(user);
                    if(!pwd_new.equals(user.getPwd())){
                        result_map.put("success", false);
                        result_map.put("msg", "用户名密码错误!");
                    }else{
                        List<UserRole> ur_list = userRoleService.findByHQLQuery("from UserRole ur where ur.userId = "+user.getId());
                        for(int i = 0; i < ur_list.size(); i++){
                            UserRole ur = ur_list.get(i);
                            List<RoleMenu> rm_list = roleMenuService.findByHQLQuery("from RoleMenu rm where rm.roleId = "+ur.getRoleId());
                            roleMenu_list.addAll(rm_list);
                        }
                        result_map.put("success", true);
                        result_map.put("msg", "登录成功");
                        request.getSession().setAttribute("userId",user.getId()+"");         //存储登陆人id
                        request.getSession().setAttribute("roleMenu_list", roleMenu_list);
                        request.getSession().setMaxInactiveInterval(3600);
                        WebConstants.setUser(request, user);
                    }
                }else{
                    result_map.put("success", false);
                    result_map.put("msg", "该账户已被禁用,请联系管理员");
                }
            }else{
                result_map.put("success", false);
                result_map.put("msg", "没有该用户");
            }
        } catch (Exception e) {
            result_map.put("success", false);
            result_map.put("msg", "系统错误,请与管理员联系");
            e.printStackTrace();
        }finally{
            printWriter.print(JsonUtil.jsonObject(result_map, null, null));
            printWriter.flush();
            printWriter.close();
        }
    }
    
    
    @RequestMapping("/loginSuccess")
    public ModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("index");
    }
    
    //首页
    @RequestMapping("/homepage")
    public ModelAndView homepage(HttpServletRequest request, HttpServletResponse response){
    	return new ModelAndView("homepage/homepage");
    }
    
}
