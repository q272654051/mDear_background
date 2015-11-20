package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.ColumnTable;




public interface IColumnTableService extends IBaseDAO<ColumnTable, Serializable>{
	//find
	
    public ColumnTable findColumnById(Serializable id);
    public List<ColumnTable> findBySQLQuery(ColumnTable entity);
    public List<ColumnTable> findChildNode(ColumnTable entity);
}
