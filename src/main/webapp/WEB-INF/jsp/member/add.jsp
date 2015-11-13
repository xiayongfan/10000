<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/jQuery/jquery.js"></script>
-->
<script type="text/javascript" src="/xiayongfan1/jQuery/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index 2</title>
</head>
<body>
    <div><font color="red" size="10px"></font></div>
    <form action="${pageContext.request.contextPath }/member/add" method="post" name="loginForm" id="loginForm">
        <div>
            姓名:<input class="username" type="text" id="id" name="id"  value=""/>
        </div>
        <div >
            密码:<input class="password" type="text" id="nickname" name="nickname" value=""/>
        </div>
        <div><input type="button" value="submit" onclick="login()" /></div>
    </form> 
<script type="text/javascript">
    
    function login(){
    	alert("login start")
        var username = $("#username").val();
        var password = $("#password").val();
        
        $("#loginForm").submit();
       
    }

    document.onkeydown=function(event){ 
        e = event ? event :(window.event ? window.event : null); 
        if(e.keyCode==13){ 
            login();
        } 
    } 
    
</script>
</body>
</html>
