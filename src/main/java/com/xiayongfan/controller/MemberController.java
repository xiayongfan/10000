package com.xiayongfan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xiayongfan.pojo.Member;
import com.xiayongfan.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Resource
	private MemberService memberService;

	@RequestMapping(value = { "/add", "/add.html" }, method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView resView = new ModelAndView();
		resView.addAllObjects(map);
		resView.setViewName("member/add");
		return resView;
	}

	@RequestMapping(value = { "/add", "/add.html" }, method = { RequestMethod.POST })
	public ModelAndView addMember(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("member") Member member) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "成功添加数据到库," + member);
		this.memberService.add(member);

		ModelAndView resView = new ModelAndView();

		resView.addAllObjects(map);
		resView.setViewName("member/message");
		return resView;
	}

	@RequestMapping(value = { "/{id:\\d+}/query", "/{id:\\d+}/query.html" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView queryMember(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(id);
		Member member = this.memberService.get(id);
		if (null != member) {
			map.put("message", "查询Id=" + id + "的用户名为:" + member.getNickname());
		} else {
			map.put("message", "没有查询到与Id=" + id + "相关的数据");
		}

		ModelAndView resView = new ModelAndView();

		resView.addAllObjects(map);
		resView.setViewName("member/message");
		return resView;
	}

	@RequestMapping(value = { "/{id:\\d+}/delete", "/{id:\\d+}/delete.html" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deleteMember(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			this.memberService.delete(id);
			map.put("message", "删除Id为" + id + "的用户成功.");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "删除Id为" + id + "的用户失败, " + e.getMessage());
		}

		ModelAndView resView = new ModelAndView();

		resView.addAllObjects(map);
		resView.setViewName("member/message");
		return resView;
	}
}