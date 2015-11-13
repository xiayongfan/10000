<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Use @RequestParam to fetch HTTP GET parameter</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="/css/search/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="/css/search/bootstrap-theme.min.css">
</head>
<body>
	<h2>搜索引擎例子--restful</h2>
	<div class="container">
		<div class="row">
	
			<form id="myForm" class="navbar-form navbar-left" data-keyboard="false" role="search">
				<div class="form-group">
					<input id='search' type="text" class="form-control" placeholder="Search">
				</div>
				<button id="btn-query" type="button" class="btn btn-default">搜索</button>
			</form>
		</div>

		<div class="row">
			<table id="table-data" class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Message</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody id='tbody'>
				</tbody>
			</table>
		</div>

		<div class="text-center">
			<div id="page-selection" class="pagination-sm"></div>
		</div>
	</div>

	<script src="/js/search/jquery-1.11.1.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="/js/search/bootstrap.min.js"></script>
	<script src="/js/search/jquery.bootpag.js"></script>
	<script>
		$(function() {
			$('#btn-query').on('click', function(){
				var query = $('#search').val();
				$('#page-selection').bootpag({'page': '1'});
				refreshTable(query, 1);
			});
			
			$('#page-selection').bootpag({
				total : 10,
				visiblePages : 10
			}).on("page", function(event, num) {
				var query = $('#search').val();
				refreshTable(query, num);
			});
			
			var refreshTable = function(query, page) {
				   $.ajax({
			        url: '${url}?query=' + query + '&page=' + (page - 1),
			        type: "GET",
			        dataType: 'json'
			      }).success(function(data) {
			    	var content = data.content;
			    	  
					var array = [];
					content.forEach(function(entry){
						array.push('<tr><td>' + entry.id + '</td><td>' + entry.name + '</td><td>' + entry.message + '</td><td>' + entry.price + '</td></tr>');
					});

					$('#tbody').html(array.join (''));
					$('#page-selection').bootpag({total: data.totalPages, visiblePages: data.totalPages > 10 ? 10 : data.totalPages});
			      }).error(function(error){
			    	  alert(error);
			      });
			}
			
			$('#myForm').bind('keydown', function(e) {
			    if (e.keyCode == 13) {
			        e.preventDefault();
			        $('#btn-query').click();
			    }
			});
			
			refreshTable('', 1);
		});
	</script>
</body>

</html>
