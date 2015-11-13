package com.xiayongfan.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiayongfan.pojo.Book;
import com.xiayongfan.pojo.Page;
import com.xiayongfan.pojo.Publish;
import com.xiayongfan.pojo.Student;
import com.xiayongfan.pojo.User;
import com.xiayongfan.service.StudentService;
import com.xiayongfan.service.UserService;

@Controller
//注解的url的下一级文件
@RequestMapping("/student")
public class StudentController {

@Resource
private StudentService studentService;
//继续注解映射/下一级
@RequestMapping("/showStudent")
public String toIndex(HttpServletRequest request,Model model){
	
	Student student=new Student();
	student.setStuage(5);
	student.setStuid(23);
	student.setStuname("abc");
	student.setStuphone(56);
	student.setStusex("0");
	
	studentService.addStu(student);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", student);
	//返回的是视图
	return "student/showStudent";

}


@RequestMapping("/addStudent")
public String toAdd(HttpServletRequest request,Model model){
	
	Student student=new Student();
	student.setStuage(5);
	student.setStuid(3);
	student.setStuname("abc");
	student.setStuphone(56);
	student.setStusex("0");
	
	studentService.addStu(student);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", student);
	//返回的是视图
	return "addStudent";

}

@RequestMapping("/addOne")
public String toAddOne(HttpServletRequest request,Model model){
	
	
	//返回的是视图
	return "addOne";

}



@RequestMapping("/deleteStudent")
public String todelete(HttpServletRequest request,Model model){
	
	Student student=new Student();
		student.setStuid(12);
	
	studentService.deletestudent(student);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", student);
	//返回的是视图
	return "deleteStudent";

}


@RequestMapping("/updateStudent")
public String toupdate(HttpServletRequest request,Model model){

	int studentId=Integer.parseInt(request.getParameter("studentId"));
	int studentphone=Integer.parseInt(request.getParameter("studentphone"));
	Student studentcom=new Student();
	
	studentcom.setStuid(studentId);
	studentcom.setStuphone(studentphone);
	
	
	
	
	Student sturesult=studentService.updatestudent(studentcom);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", sturesult);
	//返回的是视图
	return "updateStudent";

}


@RequestMapping("/SelectAllStudent")

public String findAll(HttpServletRequest request,Model modelt){
	List<Student> students = studentService.selectAllStudent();
	
	modelt.addAttribute("students",students);
	
	return "student/SelectAllStudent";
}




@RequestMapping("/selectStudentByID")
public String selectStudentByID(HttpServletRequest request,Model model){
	
	int studentId=Integer.parseInt(request.getParameter("studentId"));
	Student studentCon=new Student();
	studentCon.setStuid(studentId);
	Student studentRes=studentService.selectStudentByID(studentCon);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", studentRes);
	//返回的是视图
	return "student/selectStudentByID";

}


@RequestMapping("/SelectStudentByName")
public String selectStudentByName(HttpServletRequest request,Model model){
	
	Student student=new Student();
	student.setStuname("小");
	studentService.selectStudentByName(student);
	//第一个参数是模型。后面的是学生的对象
	model.addAttribute("student", student);
	//返回的是视图
	return "selectStudentByName";

}
}
