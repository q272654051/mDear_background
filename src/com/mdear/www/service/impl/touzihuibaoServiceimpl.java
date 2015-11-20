package com.mdear.www.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.ItouzihuibaoService;
import com.mdear.www.vo.Touzihuibao;

@Service
@Transactional
public class touzihuibaoServiceimpl extends BaseDao<Touzihuibao> implements
		ItouzihuibaoService {

	public touzihuibaoServiceimpl() {
		this.setClazz(Touzihuibao.class);

	}

	@Transactional(readOnly = true)
	public List<Touzihuibao> findBySQLQuery(String city, String type) {
		StringBuffer sb = new StringBuffer(
				"select A.ID,A.city,A.type,A.zushoubi,A.changqitouzi,A.wunianzhuanshou,A.shijian,A.pnum,B.zuobiao as zuobiao from touzihuibao as A left join city as B on A.city= replace(B.name,'市','') where (B.level=1  or b.level=2) ");
		if (city != null && !city.equals("")) {
			sb.append(" and A.city='" + city + "'");
		}
		if (type != null && !type.equals("")) {
			sb.append(" and A.type='" + type + "'");
		}
		sb.append(" order by A.shijian desc");
		return this.findAlllist(sb.toString());
	}
}
