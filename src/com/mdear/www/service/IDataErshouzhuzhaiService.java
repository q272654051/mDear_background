package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.DataErshouzhuzhai;

public interface IDataErshouzhuzhaiService extends
		IBaseDAO<DataErshouzhuzhai, Serializable> {

	// save
		public boolean saveErshouzhuzhai(DataErshouzhuzhai entity);

		// delete
		public boolean deleteErshouzhuzhai(Serializable id);

		// update
		public boolean updateErshouzhuzhai(DataErshouzhuzhai entity);

		// find
		public DataErshouzhuzhai findErshouzhuzhaiById(int id);

		public Pager findByHQLQuery(DataErshouzhuzhai entity, Pager pager);

		public Pager findBySQLQuery(String typeid, String city, Pager pager);

		public Pager findlunbolist(Pager pager);
}
