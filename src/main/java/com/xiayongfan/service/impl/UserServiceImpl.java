package com.xiayongfan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiayongfan.dao.mybatis.UserDao;
import com.xiayongfan.pojo.Page;
import com.xiayongfan.pojo.User;
import com.xiayongfan.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public long getAllUserCount() {
		
		return userDao.getAllUserCount();
	}

	@Override
	public List<User> getUsers(Page page) {
		return userDao.getUsers(page);
	}

}
