function quanxuan(){
  var ids = document.getElementsByName("ids");
  for(var i=0;i<ids.length;i++){
  	 if(ids[i].checked == true){
  	 	ids[i].checked = false;
  	 }else {
  	 	ids[i].checked = true;
  	 }
  }


}

