package com.xiayongfan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiayongfan.dao.mybatis.PublishDao;
import com.xiayongfan.pojo.Publish;
import com.xiayongfan.service.PublishService;
@Service("publsihService")
public class PublishServiceImpl implements PublishService {
    @Resource
    private PublishDao publishDao;
	@Override
	public List<Publish> findAll() {
		// TODO Auto-generated method stub
		return publishDao.findAll();
	}

}
