package com.xiayongfan.service;

import java.util.List;

import com.xiayongfan.pojo.Page;
import com.xiayongfan.pojo.User;

public interface UserService {

	public void addUser(User user);

	public List<User> getAllUser();

	public User getUserById(int id);

	public void deleteById(int id);

	public void update(User user);

	public long getAllUserCount();

	public List<User> getUsers(Page page);
}
