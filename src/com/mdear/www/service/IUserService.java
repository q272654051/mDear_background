package com.mdear.www.service;

import java.io.Serializable;
import java.util.List;

import com.mdear.www.commons.dao.IBaseDAO;
import com.mdear.www.vo.Messagevo;
import com.mdear.www.vo.User;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
public interface IUserService extends IBaseDAO<User, Serializable>{

	//保存
	public boolean saveUser (User entity);
	//修改
	public boolean updateUser (User id);
	//删除
	public boolean deleteUser (User entity);
	//查询
	public List<User> findUser ();
	//根据用户名和密码登陆
	public List<User> findUserByNameAndPwd(String name,String pwd);
	
	public String login(User user);
}
