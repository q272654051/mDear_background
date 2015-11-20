package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.vo.ColumnTable;

@Service
@Transactional
public class ColumnTableServiceimpl extends BaseDao<ColumnTable> implements IColumnTableService {

	public ColumnTableServiceimpl() {
		this.setClazz(ColumnTable.class);
	}

	@Transactional(readOnly = false)
	public ColumnTable findColumnById(Serializable id) {
		return this.findById(id);
	}

	@Transactional(readOnly = true)
	public List<ColumnTable> findBySQLQuery(ColumnTable entity) {
	
		StringBuffer sb = new StringBuffer("select * from column_a where column_name='"+entity.getColumnName()+"'");
	    return  this.findAlllist(sb.toString());
	   
	}
	
	@Transactional(readOnly = true)
    public List<ColumnTable> findChildNode(ColumnTable entity) {
        StringBuffer sb = new StringBuffer("from ColumnTable c where status=1 and c.pid = "+entity.getId());
        return  this.findByHQLQuery(sb.toString());
       
    }
	

}
