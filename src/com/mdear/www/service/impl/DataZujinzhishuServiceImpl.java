package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.IDataZujinzhishuService;
import com.mdear.www.vo.DataXinjianzhuzhai;
import com.mdear.www.vo.DataZujinzhishu;

@Service
@Transactional
public class DataZujinzhishuServiceImpl extends BaseDao<DataZujinzhishu>implements IDataZujinzhishuService {
	
	public DataZujinzhishuServiceImpl(){
		this.setClazz(DataZujinzhishu.class);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveZujinzhishu(DataZujinzhishu entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteZujinzhishu(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateZujinzhishu(DataZujinzhishu entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public DataZujinzhishu findZujinzhishuById(int id) {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(DataZujinzhishu entity, Pager pager) {
		String sql="from DataZujinzhishu z where 1=1";
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
