package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.ICityService;
import com.mdear.www.vo.CityTable;


/**
 * @author dengbojing
 * @date 2015-8-13
 * @description
 */
@Service
@Transactional
public class CityTableServiceImpl extends BaseDao<CityTable> implements ICityService {

	public CityTableServiceImpl() {
		this.setClazz(CityTable.class);
	}
	@Transactional(readOnly = false)
	public CityTable findCityById(Serializable id) {
		return this.findById(id);
	}

	@Transactional(readOnly = false)
	public List<CityTable> findBySQLQuery(CityTable entity) {
		StringBuffer sb = new StringBuffer("select * from city where name='"+entity.getName()+"'");
	    return  this.findAlllist(sb.toString());
	}

	@Transactional(readOnly = false)
	public List<CityTable> findChildNode(CityTable entity) {
	    StringBuffer sb = new StringBuffer("from CityTable c where c.upid = "+entity.getId());
        return  this.findByHQLQuery(sb.toString());
	}
    
}
