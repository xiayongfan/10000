<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	background-color: #999999;
}
-->
</style>

<script type="text/javascript" src="/js/check.js"></script>

</head>
<body>
<form name="form1" method="post" action="doLogin.do" onsubmit="return checkLogin(this)">
  <table width="347" border="1" align="center">
    <caption>
      用户登录
    </caption>
    <tr>
      <td width="135">用户名</td>
      <td width="196"><input type="text" name="username"></td>
    </tr>
    <tr>
      <td>密码</td>
      <td><input type="text" name="password"></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" name="Submit" value="提交" onclick="return checkLogin(this)">
        &nbsp;
      <input type="reset" name="Submit2" value="重置">
      &nbsp;
      <input type="button" name="Submit3" value="注册" onClick="location='register.do'" ></td>
    </tr>
  </table>
</form>
</body>
</html>