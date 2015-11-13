package com.xiayongfan.pojo;

import java.io.Serializable;

public class Book implements Serializable{
	private int id;
	private String name;
	private String author;
//	private int publishId;
	private Publish publish;//同样是持有另一张表的外键
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Publish getPublish() {
		return publish;
	}
	public void setPublish(Publish publish) {
		this.publish = publish;
	}
	
//	public int getPublishId() {
//		return publishId;
//	}
//	public void setPublishId(int publishId) {
//		this.publishId = publishId;
//	}
	
}
