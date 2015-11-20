package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.ILableService;
import com.mdear.www.vo.ColumnTable;
import com.mdear.www.vo.Labletable;

@Service
@Transactional
public class LableServiceImpl  extends BaseDao<Labletable> implements ILableService {

	public LableServiceImpl() {
		this.setClazz(Labletable.class);
	}
	@Transactional(readOnly = false)
	public Labletable findLableById(Serializable id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Transactional(readOnly = false)
	public List<Labletable> findBySQLQuery(Labletable entity) {
		StringBuffer sb = new StringBuffer("select * from Labletable where lable='"+entity.getLable()+"'");
	    return  this.findAlllist(sb.toString());
	}

	@Transactional(readOnly = false)
	public List<Labletable> findChildNode(Labletable entity) {
		  StringBuffer sb = new StringBuffer("from Labletable c where c.pid = "+entity.getId());
	      return  this.findByHQLQuery(sb.toString());
	}



}
