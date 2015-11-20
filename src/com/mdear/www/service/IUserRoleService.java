package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.UserRole;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */
public interface IUserRoleService extends IBaseDAO<UserRole, Serializable>{
    public boolean deleteByUserId(int userId);
}
