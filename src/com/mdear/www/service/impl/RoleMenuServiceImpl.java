package com.mdear.www.service.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IRoleMenuService;
import com.mdear.www.vo.RoleMenu;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */
@Service
@Transactional
public class RoleMenuServiceImpl  extends BaseDao<RoleMenu> implements IRoleMenuService {
    public RoleMenuServiceImpl() {
        this.setClazz(RoleMenu.class);
    }

    /* (non-Javadoc)
     * @see com.yunfang.shiyanWeb.service.IRoleMenuService#deleteByRoleId(int)
     */
    @Override
    public void deleteByRoleId(int roleId) {
        String sql = "delete from role_menu where roldId = "+roleId;
        Query query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }
}
