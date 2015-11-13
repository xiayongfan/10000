 $(document).ready(function() {
     $('#query').keyup(function() {
    	 reg=/^[a-z|A-Z|0-9]/;//以字母或者数字开头的不予响应
    	if(reg.test($('#query').val()))return;
    	 
         $.ajax({  
             url: 'auto.do?body='+$('#query')[0].value,  
             type: 'GET',  
             dataType: 'json',  
             timeout: 1000,  
             cache: false,  
             error: erryFunction,  //错误执行方法    
             success: succFunction //成功执行方法    
         })  
         function erryFunction() {  
             alert("error");  
         }  
         function succFunction(data) {  
        	 $.each(data, function (index, value) {  //遍历
        		 $("#auto").append("<br>"+value);  
        	 });
         }  
     });
  });  