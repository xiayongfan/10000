package com.xiayongfan.test.pojo;

/**
 * 订单实体
 */
public class Order {

	private String id;
	private String number;
	private int price;
	
	private Person person; 
	
	@Override
	public String toString() {
		return "{id: " + id + ", number: " + number + ", price: " + price + "}";
	}

}