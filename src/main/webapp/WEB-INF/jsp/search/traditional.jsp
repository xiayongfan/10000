<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Traditional</title>
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
			<form id="myForm" action="./search.do" method="get" class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input id='search' name="query" type="text" class="form-control" placeholder="Search" value="${param.query}" >
				</div>
				<button type="submit" class="btn btn-default">查询</button>
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
				<tbody >
					<c:forEach items="${books}" var="book">
						<tr>
		    				<td>${book.id}</td>
		    				<td>${book.name}</td>
		    				<td>${book.message}</td>
		    				<td>${book.price}</td>
	    				</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="text-center">
			<div id="page-selection" class="pagination-sm"></div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/js/search/jquery-1.11.1.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="/js/search/bootstrap.min.js"></script>
	<script src="/js/search/jquery.bootpag.js"></script>
	<script>
		$(function() {			
			$('#page-selection').bootpag({
				total : 10,
				visiblePages : 10
			}).on("page", function(event, num) {
				var query = $('#search').val();
				window.location = './search.do?query=' + query + '&page=' + num;
			});
			
			$('#page-selection').bootpag({total: ${total}, page: ${page}, maxVisible: 10});
		});
	</script>
</body>

</html>
