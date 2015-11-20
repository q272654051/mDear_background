package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.CityTable;

/**
 * @author dengbojing
 * @date 2015-8-13
 * @description
 */
public interface ICityService extends IBaseDAO<CityTable, Serializable> {
	public CityTable findCityById(Serializable id);

	public List<CityTable> findBySQLQuery(CityTable entity);

	public List<CityTable> findChildNode(CityTable entity);
}
