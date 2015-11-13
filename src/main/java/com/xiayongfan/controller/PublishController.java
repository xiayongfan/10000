package com.xiayongfan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiayongfan.service.PublishService;

@Controller

@RequestMapping("/publish")
public class PublishController {
	@Resource
	private PublishService publsihService;
	
	@RequestMapping("/findtAll")
	public String index(HttpServletRequest request,Model model){
		
		
		
		return "findtAll";
	}

}
