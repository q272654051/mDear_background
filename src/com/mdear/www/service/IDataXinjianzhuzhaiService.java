package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.DataXinjianzhuzhai;

public interface IDataXinjianzhuzhaiService extends IBaseDAO<DataXinjianzhuzhai, Serializable> {

	// save
	public boolean saveXinjianzhuzhai(DataXinjianzhuzhai entity);

	// delete
	public boolean deleteXinjianzhuzhai(Serializable id);

	// update
	public boolean updateXinjianzhuzhai(DataXinjianzhuzhai entity);

	// find
	public DataXinjianzhuzhai findXinjianzhuzhaiById(int id);

	public Pager findByHQLQuery(DataXinjianzhuzhai entity, Pager pager);

	public Pager findBySQLQuery(String typeid, String city, Pager pager);

	public Pager findlunbolist(Pager pager);
}
