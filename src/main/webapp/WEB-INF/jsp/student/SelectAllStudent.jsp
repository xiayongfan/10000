<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统</title>
<style type="text/css">
table,td {
	font: 100% Arial, Helvetica, sans-serif;
	font-size: 14px;
}

.dd table {
	border-collapse: collapse;
	border: none;
}

.dd td {
	border: solid gray 1px;
	height: 25px;
	text-align: center;
}
</style>


</head>
<body>

	<form action="/xiayongfan1/student" method="post">
		<table>
			<tr>
				<td>学生姓名:</td>
				<td><input type="text" name="userName" /></td>
			</tr>

			<tr>
				<td>年龄:</td>
				<td><input type="text" name="age" /></td>
			</tr>

			<tr>
				<td>性别:</td>
				<td><input type="radio" name="sex" value="0" checked="checked" />男
					<input type="radio" name="sex" value="1" />女</td>
			</tr>

			<tr>
				<td>电话:</td>
				<td><input type="text" name="address" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="提交" /> <input type="reset"
					value="重置" /></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="dd">
		<table width="800px">
			<tr>
				<td width="25%">学号</td>
				<td width="10%">姓名</td>
				<td width="10%">年龄</td>
				<td width="10%">性别</td>
				<td width="20%">电话</td>
				
			</tr>

			<c:forEach items="${students}" var="student" varStatus="status">
				<tr>
					<!-- ${status.index+1}编号 -->
					<td><c:out value="${student.stuid}" /></td>
					<td><c:out value="${student.stuname}" /></td>
					<td><c:out value="${student.stuage}" /></td>
					<td><c:out value="${student.stusex}" /></td>
					<td><c:out value="${student.stuphone}" /></td>
				<td><a href="/xiayongfan1/student/selectStudentByID?studentId=${student.stuid}">selectStudentByID</a>
					<a href="/xiayongfan1/student/updateStudent?studentId=${student.stuid} ,stuphone=${student.stuphone}">updateStudent</a>  
					<a href="/xiayongfan1/student/SelectAllStudent/${student.stuid}">删除</a>
				</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<font size="2">共 ${page.totalPageCount} 页</font>
	<font size="2">第 ${page.pageNow} 页</font>
	<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=1">首页</a>
	<c:choose>
		<c:when test="${page.pageNow - 1 > 0}">
			<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=${page.pageNow - 1}">上一页</a>
		</c:when>
		<c:when test="${page.pageNow - 1 <= 0}">
			<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=1">上一页</a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${page.pageNow + 1 < page.totalPageCount}">
			<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=${page.pageNow + 1}">下一页</a>
		</c:when>
		<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
			<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=${page.totalPageCount}">下一页</a>
		</c:when>
	</c:choose>
	<a href="/xiayongfan1/student/SelectAllStudent/?pageNow=${page.totalPageCount}">尾页</a>

</body>
</html>