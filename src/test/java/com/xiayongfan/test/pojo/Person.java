package com.xiayongfan.test.pojo;

import java.util.List;

/**
 * 客户实体 
 */
public class Person {

	private String id;
	private String name;
	private String address;
	private String tel;
	
	private List<Order> orders;
	
	@Override
	public String toString() {
		return "{id: " + id + ", name: " + name + ", address: " + address + ", tel: " + tel + "}";
	}
}