package com.mdear.www.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.service.IMenuService;
import com.mdear.www.vo.Menu;
import com.mdear.www.vo.Messagevo;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
@Service
@Transactional
public class MenuServiceImpl extends BaseDao<Menu> implements IMenuService{

    public MenuServiceImpl() {
        this.setClazz(Menu.class);
    }
	@Override
	public List<Menu> findMenuById(Serializable id) {
		// TODO Auto-generated method stub
		return this.findMenuById(id);
	}
}
