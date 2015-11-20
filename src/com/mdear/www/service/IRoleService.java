package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Role;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */
public interface IRoleService extends IBaseDAO<Role, Serializable>{
    public boolean deleteRole(Serializable id);
}
