package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Touzihuibao;

public interface ItouzihuibaoService extends IBaseDAO<Touzihuibao, Serializable> {
	public List<Touzihuibao> findBySQLQuery(String city,String type);

}
