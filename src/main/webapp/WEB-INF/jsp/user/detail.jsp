<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<div class="dd">
		<table>

			<tr>
				<td width="25%">ID</td>
				<td width="10%">username</td>
				<td width="10%">age</td>
				<td width="10%">sex</td>
				<td width="10%">address</td>
				<td width="20%">operation</td>
			</tr>

			<tr>
				<!-- ${status.index+1}编号 -->
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.userName}" /></td>
				<td><c:out value="${user.age}" /></td>
				<td><c:if test="${user.sex == 0}">
						<c:out value="Male" />
					</c:if> <c:if test="${user.sex == 5}">
						<c:out value="Female" />
					</c:if></td>
				<td><c:out value="${user.address}" /></td>
				<td><a href="/xiayongfan1/user/detail/${user.id}">detail</a> <a
					href="/xiayongfan1/user/toupdate/${user.id}">update</a> <a
					href="/xiayongfan1/user/delete/${user.id}">delete</a></td>
			</tr>
		</table>
	</div>
	<a href="/xiayongfan1/user/list">首页</a>
</body>
</html>