package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Panduanip;

public interface IpanduanipService extends IBaseDAO<Panduanip, Serializable> {

	   //save
		public boolean savepanduanip(Panduanip entity);
		
		//delete
		public boolean deletepanduanip(Serializable id);
		
		//update
		public boolean updatepanduanip(Panduanip entity);
		
		//find
		public  List<Panduanip> findBySQLQuery(int newsid,String type,String ipaddress);
}
