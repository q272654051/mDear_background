package com.mdear.www.service.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IUserRoleService;
import com.mdear.www.vo.User;
import com.mdear.www.vo.UserRole;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */
@Service
@Transactional
public class UserRoleServiceImpl extends BaseDao<UserRole> implements IUserRoleService  {
    public UserRoleServiceImpl() {
        this.setClazz(UserRole.class);
    }
    
    @Override
    public boolean deleteByUserId(int userId) {
        String sql = "delete from user_role where userId = "+userId;
        Query query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
        return true;
    }

}
