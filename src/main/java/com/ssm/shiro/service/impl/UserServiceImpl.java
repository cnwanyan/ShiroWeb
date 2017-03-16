package com.ssm.shiro.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.shiro.dao.UserDAO;
import com.ssm.shiro.entity.User;
import com.ssm.shiro.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.userDAO.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return this.userDAO.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return this.userDAO.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return this.userDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return this.userDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return this.userDAO.updateByPrimaryKey(record);
	}

	@Override
	public User login(User user) {
		List<User> list=this.userDAO.selectByName(user.getName());
		if(list.size()!=0&&!list.isEmpty()){
			for (User us : list) {
				//if(us.getPassword().equals(user.getPassword())){
					return us;
				//}
			}
		}
		return null;
	}

}
