package com.mdear.www.service.impl;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IDataXinjianzhuzhaiService;
import com.mdear.www.vo.DataXinjianzhuzhai;

@Service
@Transactional
public class DataXinjianzhuzhaiServiceImpl extends BaseDao<DataXinjianzhuzhai> implements IDataXinjianzhuzhaiService {
	
	public DataXinjianzhuzhaiServiceImpl(){
		this.setClazz(DataXinjianzhuzhai.class);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveXinjianzhuzhai(DataXinjianzhuzhai entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteXinjianzhuzhai(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateXinjianzhuzhai(DataXinjianzhuzhai entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public DataXinjianzhuzhai findXinjianzhuzhaiById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(DataXinjianzhuzhai entity, Pager pager) {
		String sql="from DataXinjianzhuzhai x ";
		
		return this.findByHQLQuery(sql, null, pager);
	}

	@Transactional(readOnly = false)
	public Pager findBySQLQuery(String typeid, String city, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false)
	public Pager findlunbolist(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}
}
