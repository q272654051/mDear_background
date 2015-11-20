package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IDataTudizongtiService;
import com.mdear.www.vo.DataTudizongti;

@Service
@Transactional
public class DataTudizongtiServiceImpl extends BaseDao<DataTudizongti>
		implements IDataTudizongtiService {
	
	public DataTudizongtiServiceImpl(){
		this.setClazz(DataTudizongti.class);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveTudizongti(DataTudizongti entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteTudizongti(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateTudizongti(DataTudizongti entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public DataTudizongti findTudizongtiById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(DataTudizongti entity, Pager pager) {
		String sql="from DataTudizongti t where 1=1";
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
