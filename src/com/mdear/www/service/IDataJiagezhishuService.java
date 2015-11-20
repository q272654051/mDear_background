package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.DataJiagezhishu;

public interface IDataJiagezhishuService extends
		IBaseDAO<DataJiagezhishu, Serializable> {

	// save
	public boolean saveJiagezhishu(DataJiagezhishu entity);

	// delete
	public boolean deleteJiagezhishu(Serializable id);

	// update
	public boolean updateJiagezhishu(DataJiagezhishu entity);

	// find
	public DataJiagezhishu findJiagezhishuById(int id);

	public Pager findByHQLQuery(DataJiagezhishu entity, Pager pager);

	public Pager findBySQLQuery(String typeid, String city, Pager pager);

	public Pager findlunbolist(Pager pager);
}
