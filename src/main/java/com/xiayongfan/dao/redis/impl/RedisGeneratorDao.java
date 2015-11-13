package com.xiayongfan.dao.redis.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class RedisGeneratorDao<K extends Serializable, V extends Serializable>  {
	  
	  @Resource
	  protected RedisTemplate<K,V> redisTemplate ;

 
	    
	  /** 
	   * 获取 RedisSerializer 
	   * <br>------------------------------<br> 
	   */  
	  protected RedisSerializer<String> getRedisSerializer() {  
	    return redisTemplate.getStringSerializer();  
	  }  
	  
	}