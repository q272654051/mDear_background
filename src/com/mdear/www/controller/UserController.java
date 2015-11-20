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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.service.IRoleService;
import com.mdear.www.service.IUserColumnService;
import com.mdear.www.service.IUserRoleService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.ColumnTable;
import com.mdear.www.vo.Role;
import com.mdear.www.vo.User;
import com.mdear.www.vo.UserColumn;
import com.mdear.www.vo.UserRole;

/**
 * 
 * @author liuzhichen
 *
 */
@Controller
@RequestMapping("userController")
public class UserController {
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	@Resource
    private IColumnTableService colService;
	@Resource
	private IUserColumnService userColService;
	@Resource
	private IUserRoleService userRoleService;
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @param printwriter
	 */
	@RequestMapping("/getUserList")
	public ModelAndView getUserList(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,User user){
	    Map<String,Object> result_map=new HashMap<String,Object>();
		String reload = request.getParameter("reload");
	    if(StringUtils.isNotBlank(reload)){
	        Map<String,User> user_map = new HashMap<String,User>();
	        List<User> user_list = userService.findByHQLQuery("from User u");
	        for(User user_temp : user_list){
	            user_map.put(user_temp.getUserName()+"", user_temp);
	        }
	        result_map.put("gender", user_list.get(0).getGender());
	        request.getSession().getServletContext().setAttribute("userid", user.getId());
	        request.getSession().getServletContext().setAttribute("user_map", user_map);
	        request.getSession().getServletContext().setAttribute("user_list", user_list);
	    }
	    return new ModelAndView("sys/user/user_list");
	}
	/**
	 * 修改登录密码
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param user
	 */
	@RequestMapping("/updateUser")
	@SystemControllerLog(description="修改用户")
	public void updateUser(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,User user){
		Map<String,Object> result_map=new HashMap<String,Object>();
		boolean bool=false;
		try {
		    bool=userService.updateEntity(user);
		    result_map.put("success",bool);
			if(bool !=false){
				result_map.put("success", "修改成功！");
			}else{
				result_map.put("msg", "密码修改失败");			
			}
		} catch (Exception e) {
		    result_map.put("success","false");
			result_map.put("msg", "系统异常");
		}
		printwriter.write(JsonUtil.jsonObject(result_map, null, null));
	}
	
	
	/**
	 * 退出当前登录状态
	 * @param request
	 * @param response
	 * @param printwriter
	 * @return
	 */
	@RequestMapping("/exitUser_noLogin")
	@SystemControllerLog(description="退出登录")
	public ModelAndView exitUser(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("successLogin");
		ModelAndView mdv=new ModelAndView("index.jsp");
		return mdv;
	}
	
	/**
	 * 标记删除用户
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param user
	 */
	@RequestMapping("/deleteUser")
	@SystemControllerLog(description="标记删除用户")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,User user){
	    Map<String,Object> result_map = new HashMap<String,Object>();
	    int status = user.getStatus();
	    user = userService.findById(user.getClass(), user.getId());
	    user.setStatus(status);
	    boolean flag = userService.updateEntity(user);
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
	 * @description 转向添加修改用户界面
	 * @param request
	 * @param response
	 * @param printwriter
	 * @param user
	 * @return
	 */
	@RequestMapping("/getUser")
	public ModelAndView getUser(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,User user){
	    try {
	        String operation = "新增";
	        List<Role> role_list = roleService.findByHQLQuery("from Role");
	        List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable");
            if(user.getId() != null && user.getId() !=0 ){
                user = userService.findById(user.getClass(), user.getId());
                List<Role> user_role_list = roleService.findAlllist("select * from Role r inner join user_role ur on r.id = ur.roleId and ur.userId = '"+user.getId()+"'");
                List<ColumnTable> user_col_list = colService.findAlllist("select * from column_a c inner join user_column uc on c.id = uc.column_id and uc.user_id = '"+user.getId()+"'");
                operation = "修改";
                request.setAttribute("user_role_list", user_role_list);
                request.setAttribute("user_col_list", user_col_list);
                role_list.removeAll(user_role_list);
                col_list.removeAll(user_col_list);
            }
            
            return new ModelAndView("sys/user/user_edit")
            .addObject("user", user)
            .addObject("operation", operation)
            .addObject("role_list", role_list)
            .addObject("col_list", col_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return null;
	}
	
	
	@RequestMapping("/addUser")
	@SystemControllerLog(description="添加用户")
	public void addUser(HttpServletRequest request,HttpServletResponse response,PrintWriter printwriter,User user){
	    Map<String,Object> result_map = new HashMap<String,Object>();
	    try {
            boolean f;
            if(user.getId() != null && user.getId() !=0 ){
                f = userService.saveOrUpdate(user);
            }else{
                f = userService.saveEntity(user);
            }
            result_map.put("success", f);
            if(f){
                saveUserColumn(request, response, user);
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
	 * @description 添加用户审核权限以及用户角色
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean saveUserColumn(HttpServletRequest request,HttpServletResponse response,User user){
	    String column[] = request.getParameterValues("columnId");
	    String role[] = request.getParameterValues("roleId");
	    boolean f = false;
	    int i = 0;
	    userColService.deleteByUserId(user.getId());
	    userRoleService.deleteByUserId(user.getId());
	    if(column != null){
	        for(String column_id : column){
	            UserColumn uc = new UserColumn();
	            uc.setColumn_id(Integer.parseInt(column_id));
	            uc.setUser_id(user.getId());
	            f = userColService.saveEntity(uc);
	            if(f){
	                i++;
	            }
	            
	        }
	    }
	    if(role != null && i == column.length){
	        for(String role_id : role){
	            UserRole ur = new UserRole();
	            ur.setRoleId(Integer.parseInt(role_id));
	            ur.setUserId(user.getId());
	            f = userRoleService.saveEntity(ur);
	        }
	    }
	    return f;
	}
}
