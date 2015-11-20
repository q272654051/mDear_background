/**
 * 
 */
package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.ColumnTable;
import com.mdear.www.vo.Log;

/**
 * @author dengbojing
 * @date 2015-8-12
 * @description
 */
public interface ILogService extends IBaseDAO<Log, Serializable>{
	public void add(Log log);
	public Pager findByHQLQuery(Log entity, Pager pager);
}
