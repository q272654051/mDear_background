package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IDataJiagezhishuService;
import com.mdear.www.vo.DataJiagezhishu;

@Service
@Transactional
public class DataJiagezhishuServiceImpl extends BaseDao<DataJiagezhishu>
		implements IDataJiagezhishuService {
	
	public DataJiagezhishuServiceImpl(){
		this.setClazz(DataJiagezhishu.class);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveJiagezhishu(DataJiagezhishu entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteJiagezhishu(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateJiagezhishu(DataJiagezhishu entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public DataJiagezhishu findJiagezhishuById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(DataJiagezhishu entity, Pager pager) {
		String sql="from DataJiagezhishu j where 1=1";
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
