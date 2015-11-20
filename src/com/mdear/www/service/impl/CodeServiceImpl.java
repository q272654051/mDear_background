package com.mdear.www.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.ICodeService;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.Messagevo;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
@Service
@Transactional
public class CodeServiceImpl extends BaseDao<Code> implements ICodeService{
    public CodeServiceImpl() {
        this.setClazz(Code.class);
    }
	@Override
	public Code findZixunzhuangtai(Serializable id) {
		// TODO Auto-generated method stub
		return this.findZixunzhuangtai(id);
	}

	@Override
	public Code findLunbozhuangtai(Serializable id) {
		// TODO Auto-generated method stub
		return this.findLunbozhuangtai(id);
	}

	@Override
	public Code findShujulaiyuan(Serializable id) {
		// TODO Auto-generated method stub
		return this.findShujulaiyuan(id);
	}

}
