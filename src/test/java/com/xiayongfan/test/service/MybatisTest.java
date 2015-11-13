package com.xiayongfan.test.service;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.xiayongfan.test.pojo.Order;
import com.xiayongfan.test.pojo.Person;

/**
 * 测试一对多和多对一
 */
public class MybatisTest {

	private SqlSessionFactory ssf; 

	@Before
	public void initSF() throws Exception {
		String resource = "sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		ssf = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test//一对多关联查询
	public void selectPersonById()throws Exception{
		SqlSession session = ssf.openSession();
		Person person = session.selectOne("com.zdp.domain.Person.selectPersonById", "001");
		System.out.println(person.getOrders());
	}
	
	@Test//多对一关联查询
	public void selectOrderById()throws Exception{
		SqlSession session = ssf.openSession();
		Order order = session.selectOne("com.zdp.domain.Order.selectOrderById", "O_00001");
		System.out.println(order.getPerson().getName());
	}
}
