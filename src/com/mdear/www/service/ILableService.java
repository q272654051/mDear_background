package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;



import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Labletable;


public interface ILableService   extends IBaseDAO<Labletable, Serializable>{
	    public Labletable findLableById(Serializable id);
	    public List<Labletable> findBySQLQuery(Labletable entity);
	    public List<Labletable> findChildNode(Labletable entity);
}
