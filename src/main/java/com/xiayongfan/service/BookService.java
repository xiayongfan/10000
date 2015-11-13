package com.xiayongfan.service;

import java.util.List;

import com.xiayongfan.pojo.Book;

public interface BookService {
	//public List<Book> findAll();
	
	public void add(Book book);
	
	public void update(Book book);
	
	public Book findById(int id);
	
	public void delete(int id);

	 public List<Book> findAll();

	
}
