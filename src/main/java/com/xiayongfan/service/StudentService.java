package com.xiayongfan.service;

import java.util.List;

import com.xiayongfan.pojo.Student;

public  interface StudentService {

	
	public int addStu(Student stu);
	public int deletestudent(Student stu);
	public Student updatestudent(Student stu);
	public List<Student> selectAllStudent();
	
	public Student selectStudentByID(Student stu);
	public List<Student> selectStudentByName(Student stu);
	
}
