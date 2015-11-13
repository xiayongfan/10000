package com.xiayongfan.controller.search;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net._01001111.text.LoremIpsum;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiayongfan.dao.search.BookRepository;
import com.xiayongfan.pojo.search.Book;
import com.xiayongfan.util.RandChineseName;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookSearchController {
	@Resource
	private BookRepository bookRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(BookSearchController.class);

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	private static final int MINCOUNT = 1;
	private static final int MAXCOUNT = 2000;

	@PostConstruct
	private void initData() {
		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		
		LoremIpsum lorem = new LoremIpsum();
		for (int i = MINCOUNT; i < MAXCOUNT; i++) {
			String documentId = UUID.randomUUID().toString();
			Book book = new Book();
			book.setId(documentId);
		//	book.setName(lorem.randomWord());
			
			book.setName(RandChineseName.getChineseName());
			
			if(i%10!=0)
			//book.setMessage(lorem.sentence()+" 夏永凡");
			book.setMessage(RandChineseName.getRoad());
			else
				book.setMessage(RandChineseName.getRoad()+" 夏永凡");
			book.setPrice(RandomUtils.nextDouble());
			IndexQuery indexQuery = new IndexQueryBuilder()
					.withId(book.getId()).withObject(book).build();
			indexQueries.add(indexQuery);
			
			
		}
		//elasticsearchTemplate.
		// bulk index
		elasticsearchTemplate.bulkIndex(indexQueries);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/searchIndex.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "search/home";
	}

	@RequestMapping(value = "/search.do",method =
			RequestMethod.GET)
	public String traditional(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		page -= 1;

		Pageable pageable = new PageRequest(page, size);

		Page<Book> pageObj;
		if (StringUtils.isBlank(query)) {
			pageObj = bookRepository.findAll(pageable);
		} else {
			pageObj = bookRepository.findByMessage(query, pageable);
		}
		
		model.addAttribute("total", pageObj.getTotalPages());
		model.addAttribute("books", pageObj.getContent());
		model.addAttribute("page", page + 1);
		
		return "search/traditional";
	}
	
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String traditional(Locale locale, Model model) {
		
		int page = Integer.parseInt((String) model.asMap().get("page"));
		int size = Integer.parseInt((String) model.asMap().get("size"));
		String query =(String) model.asMap().get("query");
		page -= 1;

		Pageable pageable = new PageRequest(page, size);

		Page<Book> pageObj;
		if (StringUtils.isBlank(query)) {
			pageObj = bookRepository.findAll(pageable);
		} else {
			pageObj = bookRepository.findByMessage(query, pageable);
		}
		
		model.addAttribute("total", pageObj.getTotalPages());
		model.addAttribute("books", pageObj.getContent());
		model.addAttribute("page", page + 1);
		
		return "search/traditional";
	}

	@RequestMapping(value = "/httpservletrequest.do", method = RequestMethod.GET)
	public String httpservletrequest(Locale locale, Model model) {
		model.addAttribute("url", "./rest/book2.do");
		return "search/book";
	}

	@RequestMapping(value = "/pageable.do", method = RequestMethod.GET)
	public String pageable(Locale locale, Model model) {
		model.addAttribute("url", "./rest/book1.do");
		return "search/book";
	}

	@RequestMapping(value = "/requestparam.do", method = RequestMethod.GET)
	public String requestparam(Locale locale, Model model) {
		model.addAttribute("url", "./rest/book3.do");
		return "search/book";
	}
}
