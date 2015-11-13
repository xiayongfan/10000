<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	background-color: #CCCCCC;
}
-->
</style></head>
<body>

<form name="form1" method="post" action="/book_springmvc_mybatis/book/edit" >
<input type="hidden" name="id" value="${book.id}"/>
  <table width="305" border="1" align="center">
    <caption>
      修改图书
    </caption>
    <tr>
      <td width="295">图书名称
      <input type="text" name="name" value="${book.name}"> </td>
    </tr>
    <tr>
      <td>图书作者
      <input type="text" name="author" value="${book.author}"></td>
    </tr>
    <tr>
     <td>图书出版社
      <select name="pubId">
     	<option value="${book.publish.pubId}" selected="selected">${book.publish.pubName }</option> 
      	<c:forEach items="${pubs}" var="pub">
      		<option value="${pub.pubId}" >${pub.pubName }</option>
      	</c:forEach>
      </select>
      </td>
    </tr>
      <tr>
      <td align="center"><input type="submit" name="Submit" value="提交"> 
        &nbsp; 
        <input type="submit" name="Submit2" value="重置"></td>
    </tr>
  </table>
</form>
</body>
</html>