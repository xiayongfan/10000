package com.xiayongfan.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xiayongfan.dao.mybatis.BookDao;
import com.xiayongfan.dao.mybatis.PublishDao;
import com.xiayongfan.pojo.Book;
import com.xiayongfan.pojo.Publish;
import com.xiayongfan.service.BookService;
import com.xiayongfan.service.PublishService;

/**
 * @author fcs
 * spring mvc+mybatis+mysql  demo
 * 2014-9-21
 */
@Controller
@RequestMapping(value="/book")
public class BookController {
	@Resource
	private PublishService publishService = null;
	@Resource
	private BookService bookService = null;
	
	@RequestMapping(value="/list.do")
	public ModelAndView findAll(HttpServletRequest request,Model modelt){
		List<Book> books = bookService.findAll();
		List<Publish> publishes=	publishService.findAll();
		modelt.addAttribute("books",books);
		modelt.addAttribute("pubs",publishes);
		return new ModelAndView("book/list");
	}
	
	@RequestMapping(value="/preAdd.do")
	public String preAdd(ModelMap model){
		List<Publish> pubs= publishService.findAll();
		model.addAttribute("pubs",pubs);
		return "book/add";
	}
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response){
		//重定向的一种
		ModelAndView mav = new ModelAndView("redirect:/book");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String pubId = request.getParameter("pubId");
		int pub_Id = Integer.parseInt(pubId);
		Book book = new Book();
		Publish pub = new Publish();
		pub.setPubId(pub_Id);
		book.setName(name);
		book.setAuthor(author);
		book.setPublish(pub);
		bookService.add(book);
		/**
		 * TODO:有没有更好的方法    
		 * 1.页面到Conreoller的数据获取略显臃肿
		 */
		return mav;
	}
	@RequestMapping(value="/delete.do")
	public String delete(HttpServletRequest request,HttpServletResponse response){
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);
			bookService.delete(id);
		}
		return "redirect:/book";
	}
	
	@RequestMapping(value="/preEdit.do")
	public String preEdit(ModelMap model,HttpServletRequest request){
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		Book book = bookService.findById(id);
		List<Publish> pubs = publishService.findAll();
		model.addAttribute("book", book);
		model.addAttribute("pubs", pubs);
		return "book/edit";
	}
	
	@RequestMapping(value="/edit.do")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response){
		//重定向的一种
		ModelAndView mav = new ModelAndView("redirect:/book");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String sid = request.getParameter("id");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String pubId = request.getParameter("pubId");
		int pub_Id = Integer.parseInt(pubId);
		Book book = new Book();
		Publish pub = new Publish();
		pub.setPubId(pub_Id);
		book.setId(Integer.parseInt(sid));
		book.setName(name);
		book.setAuthor(author);
		book.setPublish(pub);
		bookService.update(book);
		/**
		 * TODO:有没有更好的方法    
		 * 1.页面到Conreoller的数据获取略显臃肿
		 */
		return mav;
	}
	
}
