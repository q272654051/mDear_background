package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.column_data;

public interface IColumnDataService extends IBaseDAO<column_data, Serializable>{
	//find 
	public List<column_data> findBySQLQuery(String column_pinyin);
}
