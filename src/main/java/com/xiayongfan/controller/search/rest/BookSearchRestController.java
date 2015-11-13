package com.xiayongfan.controller.search.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiayongfan.dao.search.BookRepository;
import com.xiayongfan.pojo.search.Book;

@RestController
public class BookSearchRestController {
	@Resource
	private BookRepository bookRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(BookSearchRestController.class);

	@RequestMapping(value = "/rest/book1.do", method = RequestMethod.GET)
	public Page<Book> usePageable(
			@RequestParam(value = "query", required = false) String query,
			@PageableDefault(size = 10) Pageable pageable) {

		logger.info("/rest/book1.do -> pageSize = {}, pageNumber = {}",
				pageable.getPageNumber(), pageable.getPageSize());

		if (StringUtils.isBlank(query)) {
			return bookRepository.findAll(pageable);
		}
		return bookRepository.findByMessage(query, pageable);
	}

	@RequestMapping(value = "/rest/book2.do", method = RequestMethod.GET)
	public Page<Book> useHttpServletRequest(
			@RequestParam(value = "query", required = false) String query,
			HttpServletRequest request) {
		
		int size = 10;
		int page = Integer.parseInt(request.getParameter("page"));

		logger.info("/rest/book2 -> pageNumber = {}", page);
		
		Pageable pageable = new PageRequest(page, size);

		if (StringUtils.isBlank(query)) {
			return bookRepository.findAll(pageable);
		}
		return bookRepository.findByMessage(query, pageable);
	}
	
	@RequestMapping(value = "/rest/book3.do", method = RequestMethod.GET)
	public Page<Book> useRequestParam(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		logger.info("/rest/book3 -> pageNumber = {}", page);
		
		Pageable pageable = new PageRequest(page, size);

		if (StringUtils.isBlank(query)) {
			return bookRepository.findAll(pageable);
		}
		return bookRepository.findByMessage(query, pageable);
	}
}
