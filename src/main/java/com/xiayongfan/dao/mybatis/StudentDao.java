package com.xiayongfan.dao.mybatis;

import java.util.List;

import com.xiayongfan.pojo.Student;

//dao层
public interface StudentDao {

	int addStu(Student stu);

	Student updateStu(Student stu);

    int deleteStu(Student stu);

	List<Student> selectAll();

	Student selectByID(Student stu);

	List<Student> selectByName(Student stu);
}


