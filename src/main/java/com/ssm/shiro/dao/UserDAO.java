package com.ssm.shiro.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.shiro.entity.User;

@Repository
public class UserDAO{
	@Autowired
	private UserMapper userMapper;
	
	public int deleteByPrimaryKey(Integer id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	public int insert(User record) {
		return this.userMapper.insert(record);
	}

	public int insertSelective(User record) {
		return this.userMapper.insertSelective(record);
	}

	public User selectByPrimaryKey(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(User record) {
		return this.userMapper.updateByPrimaryKey(record);
	}
	
	public List<User> selectByName(String username){
		List<User> list=new ArrayList<User>();
		try {
			list = this.userMapper.selectByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		return list;
	}

	
}
