package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.service.INewsService;
import com.mdear.www.vo.News;

@Service
@Transactional
public class NewsServiceimpl extends BaseDao<News> implements INewsService {

	public NewsServiceimpl() {
		this.setClazz(News.class);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveNews(News entity) {
		return this.saveEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteNews(Serializable id) {
		return this.deleteEntityById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateNews(News entity) {
		return this.updateEntity(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public News findNewsById(int id) {
		return this.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(News entity, Pager pager) {
	    String sql = " from News n where 1=1 ";
	    //撰写人
	    if(StringUtils.isNotBlank((entity.getUserid()+"")) && entity.getUserid() != null){
	        sql += " and n.userid LIKE '%"+entity.getUserid()+"%'";
	    }
	    //标题
	    if(StringUtils.isNotBlank(entity.getTitle())){
            //sql += " and instr(n.title,'"+entity.getCity()+"')";
	    	sql += "and n.title LIKE '%"+entity.getTitle()+"%'";
        }
	    //状态
	    if(StringUtils.isNotBlank((entity.getStatus()+"")) && entity.getStatus() != null){
	        sql += " and n.status = "+entity.getStatus();
	    }
	    //类别
	    if(StringUtils.isNotBlank(entity.getType())){
	        sql += " and n.type = "+entity.getType();
	    }
	    //轮播
	    if(entity.getIflunbo()!=null){
	        sql += " and n.iflunbo = "+entity.getIflunbo();
	    }
	    //编辑时间
	    if(StringUtils.isNotBlank(entity.getStartTime()) && StringUtils.isNotBlank(entity.getEndTime())){
	        if(StringUtils.isNotBlank(entity.getEditdate())){
	            sql += " and n.editdate between "+entity.getStartTime()+" and "+entity.getEndTime()+"";
	        }else if(StringUtils.isNotBlank(entity.getFabudate())){
	            sql += " and n.fdate between '"+entity.getStartTime()+"' and '"+entity.getEndTime()+"'";
	        }
	    }
		return this.findByHQLQuery(sql, null, pager);
		
	}

	@Transactional(readOnly = false)
	public Pager findBySQLQuery(String typeid,String city, Pager pager) {
		StringBuffer sb = new StringBuffer(
				" select A.ID,A.lyName,a.Title,C.name AS city,C.zuobiao AS zuobiao,A.imageurl,B.column_name as type,A.zhaiyao,a.fabudate,A.djl,A.cont from News as A left join column_a as B on A.type=B.ID left join city as C on A.city=C.id where 1=1 ");
	
		ArrayList values = new ArrayList();
		if (typeid != null&&!typeid.equals("")) {
				sb.append(" and A.Status=3 and  A.type=?  ");
				values.add(typeid);
			
		}
		if(city!=null&&!city.equals(""))
		{
			sb.append(" and C.name like ?  ");
			values.add("%"+city+"%");
		}
		sb.append(" and iflunbo=0  order by Convert(fabudate,datetime) desc");
		return this.findBySQLQuery(sb.toString(), values.toArray(), pager);

	}

	@Transactional(readOnly = false)
	public Pager findlunbolist(Pager pager) {
		StringBuffer sb = new StringBuffer(
				" select A.ID,A.lyName,a.Title,C.name AS city,C.zuobiao AS zuobiao,A.imageurl,B.column_name as type,A.zhaiyao,a.fabudate,A.djl,A.cont from News as A left join column_a as B on A.type=B.ID left join city as C on A.city=C.id where A.iflunbo=1 order by Convert(fabudate,datetime) desc ");
		return this.findBySQLQuery(sb.toString(), pager);
	}
	
	/**
	 * 新增资讯图表  按照类型分组
	 */
	public List getIncrement(String date,String condition){
	    String sql = "select type,count(id)"+date+" from news where status > 1 "+condition+" group by type"+date+" order by "+date.substring(1);
	    List list = this.getSession().createSQLQuery(sql).list();
	    return list;
	}
	/**
	 * 新增资讯图表 按照子类型分组
	 */
	public List getChildIncrement(String date,String condition){
	    String sql = "select smalltype,count(id)"+date+" from news where status > 1 "+condition+" group by smalltype"+date+" order by "+date.substring(1);
	    List list = this.getSession().createSQLQuery(sql).list();
	    return list;
	}
	
	/**
	 * 发布资讯图表 按照发布位置分组
	 */
	public List getPublish(String date,String condition){
	    String sql = "select plateformId,count(id)"+date+"from news where status = 3 "+condition+" group by plateformId"+date+" order by "+date.substring(1);
	    List list = this.getSession().createSQLQuery(sql).list();
        return list;
	}
	
	/**
	 * 发布资讯表格 按照发布位置,用户分组
	 */
	public List getPublishTable(String date,String condition){
        String sql = "select u.username,n.plateformid,n.num from users u left join ( select userid,count(id) num,plateformid from news where status = 3 "+condition+" group by plateformid,userid"+date.substring(1)+" ) n on n.userid = u.id where u.status = 1";
        List list = this.getSession().createSQLQuery(sql).list();
        return list;
    }
	
	/**
	 * 新增资讯表格 按照类型,用户分组
	 */
    public List getIncrementTable(String date, String condition) {
        String sql = "select u.username,n.type,n.num,n.date from users u left join (select userid,type,count(id) as num"+date+" as date from news where status > 1 "+condition+" group by userid,type"+date+" order by "+date.substring(1)+") n on n.userid = u.id where u.status =1;";
        List list = this.getSession().createSQLQuery(sql).list();
        return list;
    }
    
    /**
     * 新增资讯表格 按照子类型,用户分组
     */
    public List getChildIncrementTable(String date, String condition) {
        String sql = "select u.username,n.smalltype,n.num,n.date from users u left join (select userid,smalltype,count(id) as num"+date+" as date from news where status > 1 "+condition+" group by userid,type"+date+" order by "+date.substring(1)+") n on n.userid = u.id where u.status =1;";
        List list = this.getSession().createSQLQuery(sql).list();
        return list;
    }

}
