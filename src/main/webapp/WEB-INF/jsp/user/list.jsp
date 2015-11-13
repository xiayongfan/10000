<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<form action="/xiayongfan1/user/add" method="post">
		<table>
			<tr>
				<td>username:</td>
				<td><input type="text" name="userName" /></td>
			</tr>

			<tr>
				<td>age:</td>
				<td><input type="text" name="age" /></td>
			</tr>

			<tr>
				<td>sex:</td>
				<td><input type="radio" name="sex" value="0" checked="checked" />Male
					<input type="radio" name="sex" value="5" />Female</td>
			</tr>

			<tr>
				<td>address:</td>
				<td><input type="text" name="address" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="submit" /> <input type="reset"
					value="reset" /></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="dd">
		<table width="800px">
			<tr>
				<td width="25%">ID</td>
				<td width="10%">username</td>
				<td width="10%">age</td>
				<td width="10%">sex</td>
				<td width="10%">address</td>
				<td width="20%">operation</td>
			</tr>

			<c:forEach items="${users}" var="user" varStatus="status">
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
			</c:forEach>
		</table>
	</div>
	<font size="2">共 ${page.totalPageCount} 页</font>
	<font size="2">第 ${page.pageNow} 页</font>
	<a href="/xiayongfan1/user/list?pageNow=1">首页</a>
	<c:choose>
		<c:when test="${page.pageNow - 1 > 0}">
			<a href="/xiayongfan1/user/list?pageNow=${page.pageNow - 1}">上一页</a>
		</c:when>
		<c:when test="${page.pageNow - 1 <= 0}">
			<a href="/xiayongfan1/user/list?pageNow=1">上一页</a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${page.pageNow + 1 < page.totalPageCount}">
			<a href="/xiayongfan1/user/list?pageNow=${page.pageNow + 1}">下一页</a>
		</c:when>
		<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
			<a href="/xiayongfan1/user/list?pageNow=${page.totalPageCount}">下一页</a>
		</c:when>
	</c:choose>
	<a href="/xiayongfan1/user/list?pageNow=${page.totalPageCount}">尾页</a>

</body>
</html>