package com.mdear.www.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdear.www.commons.dao.BaseDao;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.commons.util.URLtoJSONandXML;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.Messagevo;
import com.mdear.www.vo.News;
import com.mdear.www.vo.User;

/**
 * @author dengbojing
 * @date 2015-8-17
 * @description
 */
@Service
@Transactional
public class UserServiceImpl  extends BaseDao<User> implements IUserService{
    private String url = "http://123.125.148.122:8888/logincheck.php";

    
    public UserServiceImpl() {
        this.setClazz(User.class);
    }
	@Override
	public boolean saveUser(User entity) {
		// TODO Auto-generated method stub
		return this.saveUser(entity);
		
	}

	@Override
	public boolean updateUser(User id) {
		// TODO Auto-generated method stub
		return this.updateUser(id);
	}

	@Override
	public boolean deleteUser(User entity) {
		// TODO Auto-generated method stub
		return this.deleteUser(entity);
	}

	@Override
	public List<User> findUser() {
		// TODO Auto-generated method stub
		return this.findUser();
	}

	@Override
	public List<User> findUserByNameAndPwd(String name, String pwd) {
		String sql = "from User u where u.userName = '"+name+"' and pwd='"+pwd+"'";
		List<User> list = findByHQLQuery(sql);
		return list;
	}

    /* (non-Javadoc)
     * @see com.yunfang.shiyanWeb.service.IUserService#login(com.yunfang.shiyanWeb.vo.User)
     */
    @Override
    public String login(User user) {
        JSONObject json = new JSONObject();
        json.put("UNAME", user.getUserName());
        json.put("PASSWORD", user.getPwd());
        try {
            url += "?UNAME="+URLEncoder.encode(user.getUserName(),"GBK")+"&PASSWORD="+user.getPwd();
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        String retStr = URLtoJSONandXML.getHttpUrl_new(url, "GB2312");
        return retStr;
    }
}
