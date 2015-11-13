var xmlHttpReq;
function checkUser(body){
 if(body=="") return;
 if(window.XMLHttpRequest){
    xmlHttpReq = new XMLHttpRequest();
 }else if(window.ActiveXObject){
   xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
 }
 var url = "checkUser.do";
 xmlHttpReq.open("POST",url,true);
 xmlHttpReq.onreadystatechange=callback;
 xmlHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 xmlHttpReq.send("username="+body);
}

function callback(){
if(xmlHttpReq.readyState == 4){
	if(xmlHttpReq.status == 200){
		var unameMsg = document.getElementById("unameMsg");
		unameMsg.innerHTML = xmlHttpReq.responseText;
	}
}
}

