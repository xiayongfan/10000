package com.xiayongfan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiayongfan.dao.mybatis.BookDao;
import com.xiayongfan.pojo.Book;
import com.xiayongfan.service.BookService;
import com.xiayongfan.util.RedisUtil;
@Service("bookService")
public class BookSerivceImpl implements BookService {

	@Resource
	private BookDao bookDao;  
	@Resource
	private RedisUtil redisUtil;
	@Override
	public List<Book> findAll() {
		
		return bookDao.findAll();
	}

	@Override
	public void add(Book book) {
		bookDao.add(book);
		
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
		
	}

	@Override
	public Book findById(int id) {
		
		return bookDao.findById(id);
	}

	@Override
	public void delete(int id) {
		
		 bookDao.delete(id);
	}


}
