<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<!-- below 3 lines for bootstrap -->
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap core CSS -->
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- DataTables core CSS -->
	<link href="/resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<!-- Customize CSS -->
	<link href="/resources/css/customize.ui.css" rel="stylesheet">
	<title><decorator:title default="Ahope"/></title>
<decorator:head />
</head>
<body>
<div id="ys-container-header" class="container-fluid">
	<h1>Schedule Management</h1>
</div>
<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation </span>
				<span class="icon-bar"> </span>
				<span class="icon-bar"> </span>
				<span class="icon-bar"> </span>
			</button>
			<a class="navbar-brand" href="/"> AHOPE </a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/empl">EmployeeManagement</a></li>
				<li><a href="/schedule">DutyScheduleManagement</a></li>
				<li><a href="#">Login</a></li>
			</ul>
		</div>
	</div>
</nav>
<decorator:body />
<footer class="container-fluid text-center">
	<p class="pull-center"><a href=#> back to top </a></p>
	<p>&copy; 2016 AHOPE, Inc.</p>
</footer>
<!-- JavaScript -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.validate.js"></script>
<script src="/resources/js/jquery.dataTables.min.js"></script>
<script src="/resources/js/dataTables.bootstrap.min.js"></script>
<decorator:getProperty property="page.local_script" />
</body>
</html>