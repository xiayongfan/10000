<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/usercheck.js"></script>


<style type="text/css">
<!--
body {
	background-color: #999999;
}
-->
</style></head>
<body>
<form name="form1" method="post" action="doRegister.do" onSubmit="return checkRegister(this)">
  <p>&nbsp;</p>
  <table width="525" border="1" align="center">
    <caption>
      用户注册
    </caption>
    <tr>
      <td width="162">用&nbsp;&nbsp;户&nbsp;&nbsp;名</td>
      <td width="347"><input type="text" name="username" onblur="checkUser(this.value)">
        <span id=unameMsg style="color: red;"></span> </td>
    </tr>
    <tr>
      <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
      <td><input type="password" name="password">
        <span id=pwordMsg style="color: red;"></span> </td>
    </tr>
    <tr>
      <td>确认密码</td>
      <td><input type="password" name="password2">
      <span id=pwordMsg2 style="color: red;"></span> </td>
    </tr>
    <tr>
      <td>邮 箱</td>
      <td><input type="text" name="email">
      <span id=emailMsg style="color: red;"></span> </td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" name="Submit" value="提交" >
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        
      <input type="reset" name="Submit2" value="重置"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>