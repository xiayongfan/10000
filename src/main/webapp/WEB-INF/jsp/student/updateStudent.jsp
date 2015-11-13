<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统</title>
<style type="text/css">table,td {
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
<div class="dd">
		<table width="800px">
			<tr>
				<td width="25%">学号</td>
				<td width="10%">姓名</td>
				<td width="10%">年龄</td>
				<td width="10%">性别</td>
				<td width="20%">电话</td>
				
			</tr>

			
					
					<td><c:out value="${student.stuid}" /></td>
					<td><c:out value="${student.stuname}" /></td>
					<td><c:out value="${student.stuage}" /></td>
					<td><c:out value="${student.stusex}" /></td>
					<td><c:out value="${student.stuphone}" /></td>
					
			
		</table>
	</div>
	
	
	</head>
	</html>