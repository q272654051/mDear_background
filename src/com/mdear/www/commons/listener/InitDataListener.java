package com.mdear.www.commons.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.mdear.www.service.ICityService;
import com.mdear.www.service.ICodeService;
import com.mdear.www.service.IMenuService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.CityTable;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.Menu;
import com.mdear.www.vo.User;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
public class InitDataListener implements InitializingBean, ServletContextAware{
    @Autowired
    private IUserService userService;
//    @Autowired
//    private ICodeService codeService;
    @Autowired
    private IMenuService menuService;
//    @Autowired
//    private ICityService cityService;
//
//    @Override
    public void setServletContext(ServletContext context) {
        //获取所有用户
        Map<String,User> user_map = new HashMap<String,User>();
        Map<String,User> userId_map = new HashMap<String,User>();
        List<User> user_list = userService.findByHQLQuery("from User");
        for(User user : user_list){
            user_map.put(user.getUserName()+"", user);
            userId_map.put(user.getId()+"", user);
        }
        context.setAttribute("user_map", user_map);
        context.setAttribute("userId_map", userId_map);
        context.setAttribute("user_list", user_list);
        
//        Map<String,Code> code_map = new HashMap<String,Code>();
//        List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1004");
//        for(Code code : code_list){
//            code_map.put(code.getDmz()+"", code);
//        }
//        context.setAttribute("code_map", code_map);
//        context.setAttribute("code_list", code_list);
        
        //获取所有菜单
        Map<String,Menu> menu_map = new HashMap<String,Menu>();
        List<Menu> menu_list = menuService.findByHQLQuery("from Menu m");
        for(Menu menu : menu_list){
            menu_map.put(menu.getId()+"", menu);
        }
        context.setAttribute("menu_map", menu_map);
        context.setAttribute("menu_list", menu_list);
//        
//        //获取所有城市
//        Map<String,CityTable> city_map = new HashMap<String,CityTable>();
//        List<CityTable> city_list = cityService.findByHQLQuery("from CityTable c");
//        for(CityTable city : city_list){
//            city_map.put(city.getId()+"", city);
//        }
//        context.setAttribute("city_map", city_map);
//        context.setAttribute("city_list", city_list);
        
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}