package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Menu;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
public interface IMenuService extends IBaseDAO<Menu, Serializable>{

	//查询菜单列表
	public List<Menu> findMenuById(Serializable id);
	
}
