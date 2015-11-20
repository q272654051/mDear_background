package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.DataTudizongti;

public interface IDataTudizongtiService extends
		IBaseDAO<DataTudizongti, Serializable> {

	// save
	public boolean saveTudizongti(DataTudizongti entity);

	// delete
	public boolean deleteTudizongti(Serializable id);

	// update
	public boolean updateTudizongti(DataTudizongti entity);

	// find
	public DataTudizongti findTudizongtiById(int id);

	public Pager findByHQLQuery(DataTudizongti entity, Pager pager);

	public Pager findBySQLQuery(String typeid, String city, Pager pager);

	public Pager findlunbolist(Pager pager);
}
