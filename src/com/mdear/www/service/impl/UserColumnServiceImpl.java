package com.mdear.www.service.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IUserColumnService;
import com.mdear.www.vo.UserColumn;

/**
 * @author dengbojing
 * @date 2015-10-21
 * @description
 */
@Service
@Transactional
public class UserColumnServiceImpl extends BaseDao<UserColumn> implements IUserColumnService {
    public UserColumnServiceImpl() {
        this.setClazz(UserColumn.class);

    }

    /**
     * @description 删除用户所拥有的审核权限
     */
    @Override
    public boolean deleteByUserId(int userId) {
        String sql = "delete from user_column where user_id = "+userId;
        Query query = this.getSession().createSQLQuery(sql);
        query.executeUpdate(); 
        return true;
    }
}
