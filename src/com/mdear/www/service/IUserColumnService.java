package com.mdear.www.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.UserColumn;


/**
 * @author dengbojing
 * @date 2015-10-21
 * @description
 */
@Service
public interface IUserColumnService extends IBaseDAO<UserColumn, Serializable>{
    public boolean deleteByUserId(int userId);
}
