package com.mdear.www.service;

import java.io.Serializable;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Code;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
public interface ICodeService extends IBaseDAO<Code, Serializable>{

	//find资讯状态
	public Code findZixunzhuangtai(Serializable id);
	//find轮播状态
	public Code findLunbozhuangtai(Serializable id);
	//find数据来源
	public Code findShujulaiyuan(Serializable id);
	
}
