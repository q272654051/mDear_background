package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.Messagevo;


public interface IMessageService extends IBaseDAO<Messagevo, Serializable> {

	//新增save
	public boolean saveMessage(Messagevo entity);
	//删除delete
	public boolean deleteMessage(Serializable id);
	//修改update
	public boolean updateMessage(Messagevo entity);
	//查询find
	public Messagevo findMessageById(int id);
	
	public Pager findByHQLQuery(Messagevo entity,Pager pager);
	
	public Pager findBySQLQuery();
}
