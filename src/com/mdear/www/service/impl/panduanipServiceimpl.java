package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IpanduanipService;
import com.mdear.www.vo.Panduanip;

@Service
@Transactional
public class panduanipServiceimpl extends BaseDao<Panduanip> implements
		IpanduanipService {

	public panduanipServiceimpl() {
		this.setClazz(Panduanip.class);
	}

	@Transactional(readOnly = false)
	public boolean savepanduanip(Panduanip entity) {
		// TODO Auto-generated method stub
		return this.saveEntity(entity);
	}

	@Transactional(readOnly = false)
	public boolean deletepanduanip(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Transactional(readOnly = false)
	public boolean updatepanduanip(Panduanip entity) {
		// TODO Auto-generated method stub
		return this.updateEntity(entity);
	}

	@Transactional(readOnly = true)
	public List<Panduanip> findBySQLQuery(int newsid, String type, String ipaddress) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("select * from panduanip where newsid="+newsid+" and type='"+type+"' and ipaddress='"+ipaddress+"'");
		return  this.findAlllist(sb.toString());
	}

}
