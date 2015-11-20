package com.mdear.www.service.impl;

import java.awt.print.Paper;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IMenuService;
import com.mdear.www.service.IMessageService;
import com.mdear.www.vo.DataErshouzhuzhai;
import com.mdear.www.vo.Messagevo;
import com.mdear.www.vo.RoleMenu;

@Service
@Transactional
public class MessageServiceImpl extends BaseDao<Messagevo> implements IMessageService {

    public MessageServiceImpl() {
        this.setClazz(Messagevo.class);
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public boolean saveMessage(Messagevo entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteMessage(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateMessage(Messagevo entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Messagevo findMessageById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(Messagevo entity, Pager pager) {
		String sql="from Messagevo m where 1=1";
		return this.findByHQLQuery(sql, null, pager);
	}
	
	@Override
	public Pager findBySQLQuery() {
		// TODO Auto-generated method stub
		return this.findBySQLQuery();
	}


}
