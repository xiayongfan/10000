//1.声明变量  
//http://blog.163.com/sean_zwx/blog/static/16903275520111222590736/
    	var xmlHttpReq;
    	function auto(body){
    	if(body=="")return;
    	//2.判断
    	  if(window.XMLHttpRequest){//不是IE
    		xmlHttpReq = new XMLHttpRequest();
    	  }else if(window.ActiveXObject){//IE5.0以上
    	  	xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    	  }
    	 //3.开启发送数据
    	 var url = "AutoServlet?body="+body;//提交的路径
    	 url = encodeURI(url);
    	 //第一个参数 提交的方式
    	 //第二个参数 提交的路径
    	 //第三个参数 是否是异步的(默认是true异步)
    	 xmlHttpReq.open("GET",url,true);
    	 //4.注册回调函数
    	 xmlHttpReq.onreadystatechange = callback;
    	 //6.发送
    	 xmlHttpReq.send(null);//send()里面可以不带参数或者带上null
    	}
    	 //5.实现callback
    	 function callback(){
    	 	if(xmlHttpReq.readyState == 4){//交互完成
    	 		if(xmlHttpReq.status == 200){//服务器正确响应Http请求
    	 			var arrays = xmlHttpReq.responseText;
    	 			if(arrays != ""){
    	 			var auto = document.getElementById("auto");
    	 			//接受Hello AJAX
   					var query = document.getElementById("query");
    	 			arrays = arrays.split(",");
  					auto.style.visibility = "visible";
    	 			for(var i=0;i<arrays.length;i++){
    	 				//创建div
    	 				var div = document.createElement("div");
    	 				div.innerHTML = arrays[i];
    	 				auto.appendChild(div);
    	 			}
    	 		}else{
    	 			auto.style.visibility = "hidden";
    	 		}
    	 		
    	 		
    	 		}
    	 	}
    	 }
    	 
    	 function selectBN(){
    		 
    		 
    	 
    	 }
