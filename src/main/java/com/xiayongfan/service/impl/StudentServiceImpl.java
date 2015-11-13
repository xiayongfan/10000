package com.xiayongfan.service.impl;

import java.util.List;

import com.xiayongfan.dao.mybatis.StudentDao;
import com.xiayongfan.pojo.Student;
import com.xiayongfan.service.StudentService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//实现接口
@Service("studentService")
public  class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	
	@Override
	public int addStu(Student stu) {
		
		return studentDao.addStu(stu);
	}


	@Override
	public int deletestudent(Student stu) {          
		// TODO Auto-generated method stub
		
		return studentDao.deleteStu(stu);
		
	}







	@Override
	public List<Student> selectStudentByName(Student stu) {
		// TODO Auto-generated method stub
		return studentDao.selectByName(stu);
	}


	@Override
	public List<Student> selectAllStudent() {
		// TODO Auto-generated method stub
		return studentDao.selectAll();
	}


	@Override
	public Student selectStudentByID(Student stu) {
		// TODO Auto-generated method stub
		return studentDao.selectByID(stu);
	}


	@Override
	public Student updatestudent(Student stu) {
		// TODO Auto-generated method stub
		return studentDao.updateStu(stu);
	}



	
}
