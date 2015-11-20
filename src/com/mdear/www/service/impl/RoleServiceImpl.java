package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IRoleService;
import com.mdear.www.vo.Role;
import com.mdear.www.vo.Touzihuibao;

/**
 * @author dengbojing
 * @date 2015-9-9
 * @description
 */

@Service
@Transactional
public class RoleServiceImpl extends BaseDao<Role> implements IRoleService {

    /* (non-Javadoc)
     * @see com.yunfang.shiyanWeb.service.IRoleService#deleteRole(java.io.Serializable)
     */
    @Override
    public boolean deleteRole(Serializable id) {
        return this.deleteEntityById(id);
    }
    public RoleServiceImpl() {
        this.setClazz(Role.class);
    }
    
   
}
