package com.xiayongfan.dao.mybatis;

import java.util.List;

import com.xiayongfan.pojo.Book;



public interface BookDao {
	
	public List<Book> findAll();
	
	public void add(Book book);
	
	public void update(Book book);
	
	public Book findById(int id);
	
	public void delete(int id);
}
