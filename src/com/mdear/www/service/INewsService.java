package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.vo.News;


public interface INewsService extends IBaseDAO<News, Serializable>{

	//save
	public boolean saveNews(News entity);
	
	//delete
	public boolean deleteNews(Serializable id);
	
	//update
	public boolean updateNews(News entity);
	
	//find
	public News findNewsById(int id);
	
	public Pager findByHQLQuery(News entity, Pager pager);
	
	public Pager findBySQLQuery(String typeid, String city,Pager pager);
	
	public Pager findlunbolist(Pager pager);
	public List getIncrement(String date, String condition);
	public List getChildIncrement(String date, String condition);
	public List getPublish(String date,String conditon);
	public List getPublishTable(String date,String conditon);
	public List getIncrementTable(String date,String condition);
	public List getChildIncrementTable(String date,String condition);
}
