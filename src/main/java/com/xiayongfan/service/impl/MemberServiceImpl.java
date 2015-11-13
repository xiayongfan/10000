package com.xiayongfan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiayongfan.dao.redis.MemberDao;
import com.xiayongfan.pojo.Member;
import com.xiayongfan.service.MemberService;
@Service("memberService")
public class MemberServiceImpl implements  MemberService{
    
	@Resource
	private  MemberDao memberDao;
    
	@Override
	public boolean add(Member member) {
		// TODO Auto-generated method stub
		return memberDao.add(member);
	}

	@Override
	public boolean add(List<Member> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		 memberDao.delete(key);
	}

	@Override
	public void delete(List<String> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member get(String keyId) {
		// TODO Auto-generated method stub
		return memberDao.get(keyId);
	}

}
