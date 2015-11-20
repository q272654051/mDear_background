package com.mdear.www.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IColumnDataService;
import com.mdear.www.vo.column_data;

@Service
@Transactional
public class ColumnDataServiceImpl extends BaseDao<column_data> implements IColumnDataService {
	@Transactional(readOnly = true)
	public List<column_data> findBySQLQuery(String column_pinyin) {
		String sql = "select * from column_data where column_pinyin='"+column_pinyin+"'";
	    return  this.getSession().createSQLQuery(sql).addEntity(column_data.class).list();
	}
}
