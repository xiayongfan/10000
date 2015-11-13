package com.xiayongfan.pojo;

//对应数据库的表
public class Student {
	private int stuid;
	private String stuname;
	private int stuage;
	private String stusex;
	private int stuphone;
//无参数构造
	public Student() {

	}
//两个参数的构造
	public Student(int stuid, int stuphone) {
		super();
		this.stuid = stuid;

		this.stuphone = stuphone;
	}
//四个参数的构造
	public Student(String stuname, int stuage, String stusex, int stuphone) {
		super();

		this.stuname = stuname;
		this.stuage = stuage;
		this.stusex = stusex;
		this.stuphone = stuphone;
	}
//所有参数的构造方法
	
	
	public Student(int stuid, String stuname, int stuage, String stusex,
			int stuphone) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.stuage = stuage;
		this.stusex = stusex;
		this.stuphone = stuphone;
	}

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", stuname=" + stuname + ", stuage="
				+ stuage + ", stusex=" + stusex + ", stuphone=" + stuphone
				+ "]";
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public int getStuage() {
		return stuage;
	}

	public void setStuage(int stuage) {
		this.stuage = stuage;
	}

	public String getStusex() {
		return stusex;
	}

	public void setStusex(String stusex) {
		this.stusex = stusex;
	}

	public int getStuphone() {
		return stuphone;
	}

	public void setStuphone(int stuphone) {
		this.stuphone = stuphone;
	}

}
