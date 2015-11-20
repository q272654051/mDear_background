package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.RoleMenu;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */
public interface  IRoleMenuService extends IBaseDAO<RoleMenu, Serializable>{
    public void deleteByRoleId(int roleId);
}
