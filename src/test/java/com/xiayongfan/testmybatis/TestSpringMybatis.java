package com.xiayongfan.testmybatis;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.xiayongfan.pojo.Student;
import com.xiayongfan.service.StudentService;


@RunWith(JUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })  
public class TestSpringMybatis {
	private static Logger logger = Logger.getLogger(TestSpringMybatis.class);
	
	private StudentService stuservice=null;
	@Before
	public void before()
	{
		logger.info("before");
	}
	
	@After
	public void after()
	{
		logger.info("after");
	}
	@Test
	public void test1(){
		
		Student stu=new Student("小吃", 18, "男", 565656);
		
		//stuservice.addstu(stu);
		
		logger.info(stu);
		
	}
}
