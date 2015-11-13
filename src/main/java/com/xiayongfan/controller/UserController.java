package com.xiayongfan.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




import com.xiayongfan.pojo.Page;
import com.xiayongfan.pojo.User;
import com.xiayongfan.service.UserService;



@Controller
@RequestMapping(value = "/user")
public class UserController {
    
	@Resource
	private UserService userService = null;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showForm(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String pageNow = request.getParameter("pageNow");
		Page page = null;
		List<User> users = null;
		int totalCount = (int) userService.getAllUserCount();
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			users = userService.getUsers(page);
		} else {
			page = new Page(totalCount, 1);
			users = userService.getUsers(page);
			// users = userMapper.getAllUser();
		}
		model.addAttribute("page", page);
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(value = "/add")
	public String add(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		
		String userName = request.getParameter("userName");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		User user = new User();
		user.setUserName(userName);
		user.setAge(Integer.parseInt(age));
		user.setSex(Integer.parseInt(sex));
		user.setAddress(address);
		userService.addUser(user);

		List<User> list = null;
		Page page = null;
		int totalCount = (int) userService.getAllUserCount();
		page = new Page(totalCount, 1);
		list = userService.getUsers(page);
		model.addAttribute("page", page);
		model.addAttribute("users", list);
		return "user/list";
	}

	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable int id, ModelMap model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/detail";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id, ModelMap model) {
		userService.deleteById(id);
		List<User> list = userService.getAllUser();
		model.addAttribute("users", list);
		return "user/list";
	}

	@RequestMapping(value = "/toupdate/{id}")
	public String toUpdate(@PathVariable int id, ModelMap model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/update";
	}

	@RequestMapping(value = "/update")
	public String update(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("sid");
		String userName = request.getParameter("userName");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUserName(userName);
		user.setAge(Integer.parseInt(age));
		user.setSex(Integer.parseInt(sex));
		user.setAddress(address);
		userService.update(user);
		List<User> list = userService.getAllUser();
		model.addAttribute("users", list);
		return "user/list";
	}
}
