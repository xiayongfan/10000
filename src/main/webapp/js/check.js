function checkLogin(body){
	var uname = body.username.value;
	var pword = body.password.value;
	if(uname==""){
		alert("用户名不能为空");
		return false;
	}if(pword==""){
		alert("密码不能为空");
		return false;
	}
	return true;
}


function checkRegister(regis){
	var uname = regis.username.value;
	var pword = regis.password.value;
	var pword2 = regis.password2.value;
	var email = regis.email.value;
	reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
	if(uname==""){
		document.getElementById("unameMsg").innerHTML="用户名不能为空";
		return false;
	}if(pword==""){
		document.getElementById("pwordMsg").innerHTML="密码不能为空";
		return false;
	}if(pword2==""){
		document.getElementById("pwordMsg2").innerHTML="确认密码不能为空";
		return false;
	}if(email==""){
		document.getElementById("emailMsg").innerHTML="email不能为空";
		return false;
	}if(pword!=pword2){
		document.getElementById("pwordMsg2").innerHTML="两次密码输入不一致";
		return false;
	}if(!reg.test(email)){
		document.getElementById("emailMsg").innerHTML="邮箱格式不正确";
		return false;
	}
	return true;
}

function checkAdd(add){
	var bookName = add.name.value;
	var bookAuthor = add.author.value;
	var bookPub = add.pubname.value;
	if(bookName==""){
		alert("图书名不能为空");
		return false;
	}
	if(bookAuthor==""){
		alert("作者不能为空");
		return false;
	}
	if(bookPub=="0"){
		alert("请选择出版社");
		return false;
	}
	return true;
}