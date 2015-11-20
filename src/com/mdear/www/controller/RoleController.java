package com.mdear.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.service.IMenuService;
import com.mdear.www.service.IRoleMenuService;
import com.mdear.www.service.IRoleService;
import com.mdear.www.vo.Menu;
import com.mdear.www.vo.Role;
import com.mdear.www.vo.RoleMenu;

/**
 * @author dengbojing
 * @date 2015-9-10
 * @description
 */
@Component
@RequestMapping("roleController")
public class RoleController {
    
    @Resource
    IRoleService roleService;
    @Resource
    IRoleMenuService roleMenuService;
    @Resource
    IMenuService menuService;
    
    /**
     * 获取角色
     * @param request
     * @param response
     * @param printWriter
     * @return
     */
    @RequestMapping("/getRoleList")
    public ModelAndView getRoleList(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter){
        List<Role> role_list = roleService.findByHQLQuery("from Role");
        return new ModelAndView("sys/role/role_list")
        .addObject("role_list", role_list);
    }
    
    @RequestMapping("/deleteRole")
    @SystemControllerLog(description="删除用户")
    public void deleteRole(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Role role){
        Map<String,Object> result_map = new HashMap<String,Object>();
        List<RoleMenu> roleMenu_list = roleMenuService.findByHQLQuery("from RoleMenu r where r.roleId = "+role.getId());
        boolean f = false;
        if(roleMenu_list.size() == 0 || roleMenu_list == null){
            f = roleService.deleteRole(role.getId());
            if(f){
                result_map.put("msg", "删除成功");
            }else{
                result_map.put("msg", "删除失败");
            }
        }else{
            
            result_map.put("msg", "删除失败!请先清除角色所拥有菜单");
        }
        result_map.put("success", f);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    @RequestMapping("/addRole")
    @SystemControllerLog(description="添加或者更新角色")
    public void addRole(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Role role){
        Map<String,Object> result_map = new HashMap<String,Object>();
        try {
            boolean f;
            if(role.getId() != null && role.getId() !=0 ){
                f = roleService.saveOrUpdate(role);
            }else{
                f = roleService.saveEntity(role);
            }
            result_map.put("success", f);
            if(f){
                saveRoleMenu(request, response, printWriter, role);
                result_map.put("msg", "保存成功");
            }else{
                result_map.put("msg", "保存失败");  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * @description 转向添加/修改角色界面
     * @param request
     * @param response
     * @param printWriter
     * @param role
     * @return
     */
    @RequestMapping("/getRole")
    public ModelAndView getRole(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Role role){
        String description = "新增";
        List<Menu> menu_list = new ArrayList<Menu>();
        menu_list = menuService.findByHQLQuery("from Menu");
        if(role.getId() != null && role.getId() != 0){
            role = roleService.findById(role.getClass(),role.getId());
            List<Menu> role_menu_list = menuService.findAlllist("select * from menu m left join role_menu rm on rm.menuId = m.id and rm.roleId = '"+role.getId()+"'");
            menu_list.removeAll(role_menu_list);
            request.setAttribute("role_menu_list", role_menu_list);
            description = "修改";
        }
        return new ModelAndView("sys/role/role_edit")
        .addObject("role", role)
        .addObject("menu_list",menu_list)
        .addObject("description", description);
    }
    
    private boolean saveRoleMenu(HttpServletRequest request,HttpServletResponse response,PrintWriter printWriter,Role role){
        String menu_id[] = request.getParameterValues("menu_id");
        roleMenuService.deleteByRoleId(role.getId());
        boolean f = false;
        if(menu_id != null){
            for(String str : menu_id){
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(Integer.parseInt(str));
                rm.setRoleId(role.getId());
                f = roleMenuService.saveEntity(rm);
            }
        }
        return f;
    }
}
