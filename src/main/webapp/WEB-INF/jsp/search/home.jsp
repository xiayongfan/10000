<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Home</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="/css/search/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="/css/search/bootstrap-theme.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>搜索引擎例子例子</h1>
			<ol>
				<li><a href="./pageable.do">Use <b>Pageable</b> to fetch HTTP GET parameter</a></li>
				<li><a href="./httpservletrequest.do">Use <b>HttpServletRequest</b> to fetch HTTP GET parameter</a></li>
				<li><a href="./requestparam.do">Use <b>@RequestParam</b> to fetch HTTP GET parameter</a></li>
				<li><a href="./search.do">Reload whole page (Traditional)</a></li>
			</ol>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/js/search/jquery-1.11.1.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="/js/search/bootstrap.min.js"></script>
	
</body>

</html>
