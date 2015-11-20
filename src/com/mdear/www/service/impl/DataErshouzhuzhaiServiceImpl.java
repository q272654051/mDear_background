package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IDataErshouzhuzhaiService;
import com.mdear.www.vo.DataErshouzhuzhai;

@Service
@Transactional
public class DataErshouzhuzhaiServiceImpl extends BaseDao<DataErshouzhuzhai>
		implements IDataErshouzhuzhaiService {

	public DataErshouzhuzhaiServiceImpl(){
		this.setClazz(DataErshouzhuzhai.class);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveErshouzhuzhai(DataErshouzhuzhai entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteErshouzhuzhai(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateErshouzhuzhai(DataErshouzhuzhai entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public DataErshouzhuzhai findErshouzhuzhaiById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(DataErshouzhuzhai entity, Pager pager) {
		String sql="from DataErshouzhuzhai e where 1=1";
		return this.findByHQLQuery(sql, null, pager);
	}

	@Override
	@Transactional(readOnly = false)
	public Pager findBySQLQuery(String typeid, String city, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Pager findlunbolist(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}
}
