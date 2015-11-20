package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.DataZujinzhishu;

public interface IDataZujinzhishuService extends
		IBaseDAO<DataZujinzhishu, Serializable> {

	// save
	public boolean saveZujinzhishu(DataZujinzhishu entity);

	// delete
	public boolean deleteZujinzhishu(Serializable id);

	// update
	public boolean updateZujinzhishu(DataZujinzhishu entity);

	// find
	public DataZujinzhishu findZujinzhishuById(int id);

	public Pager findByHQLQuery(DataZujinzhishu entity, Pager pager);

	public Pager findBySQLQuery(String typeid, String city, Pager pager);

	public Pager findlunbolist(Pager pager);
}
