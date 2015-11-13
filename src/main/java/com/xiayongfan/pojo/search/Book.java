package com.xiayongfan.pojo.search;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
//3334374487
@Document(indexName="book")
public class Book {
	private String id;
	private Double price;
	
	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true,indexAnalyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String name;
	
	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true,indexAnalyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String message;

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
