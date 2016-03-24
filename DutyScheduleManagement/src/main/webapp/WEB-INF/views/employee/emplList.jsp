<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<body>
<div class="container"><hr>
	<h1 class="empl-title"> Add an Employee </h1><br>
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<form id="signup_frm" action="/empl/add" method="post" autocomplete="off">
				<div class="form-group">
					<label for="name"> Employee name </label>
					<input class="form-control" id="name" name="name" type="text" placeholder="Name">
				</div>
				<div class="form-group">
					<label for="email"> Email Address </label>
					<input class="form-control" id="email" name="email" type="email" placeholder="Email">
				</div><br>
				<div class="form-group text-center">
					<button class="btn btn-primary" type="submit">Add an employee</button>
				</div>		
			</form>
		</div>
	</div>
	<hr>
	<h1 class="empl-title"> Added Employees </h1><br>
	<div class="table-responsive">
		<table id="empl_list" class="table table-bordered text-center" style="cellspacing:0; width:100%">
			<colgroup>
				<col width="30%" />
				<col width="40%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th class="th-center" scope="col">Name</th>
					<th class="th-center" scope="col">Email Address</th>
					<th class="th-center" scope="col">Option1</th>
					<th class="th-center" scope="col">Option2</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test = "${fn:length(emplList) > 0}">
						<c:forEach items = "${emplList}" var = "row">
							<tr>
								<td id="emplName${row.emplId}">${row.name}</td>
								<td id="emplEmail${row.emplId}">${row.email}</td>
								<td><button class="btn btn-info btn-xs" id="modify${row.emplId}">Modify</button></td>
								<td><button class="btn btn-warning btn-xs" id="delete${row.emplId}">Delete</button></td>
							</tr>	
						</c:forEach>	
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">
								<h2> Empty an Employee List</h2>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<br><br>
	</div>	
	<!-- Modify form modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-sm">    
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modify Information</h4>
				</div>
				<div class="modal-body">
					<form action="/empl/modify" method="post" id="m_frm" name="m_frm">
						<div class="form group">
							<label for="m_name"> Employee Name </label>
							<input class="form-control" id="m_name" name="name" type="text" value="">
						</div><br>
						<div class="form group">
							<label for="m_email"> Email Address </label>
							<input  class="form-control" id="m_email" name="email" type="email" value="">
						</div>
						<div class="form group text-center">
							<input id="m_emplId" name="emplId" type="hidden" value=""><br><br>
							<Button class="btn btn-primary" type="submit"> Modify Information </Button>						
						</div>
					</form>
				</div>
			</div>    
		</div>
	</div>
	<!-- Delete form modal -->
	<div class="modal fade" id="del_modal" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Are you sure you want delete?</h4>
				</div>
				 <div class="modal-body text-center">
					<form id="form" name="form" method="post" action="/empl/delete">
						<input id="d_emplId" name="emplId" type="hidden" value="">
						<button class="btn btn-danger" type="submit">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</form>
				</div>
			</div>  
		</div>
	</div>
	<!-- Confirm modal( register or modify )-->
	<div class="modal fade" id="confirm_modal" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"> ${message}</h4>
				</div>
				 <div class="modal-body text-center">
					<button type="button" class="btn btn-info" data-dismiss="modal">Confirm</button>
				</div>
			</div>  
		</div>
	</div>		
</div>
</body>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function(){
	
	var message = "${message}";
	if( message.length != 0 ) {
		$("#confirm_modal").modal();
	}
	
	var emplId;
	var emplName;
	var emplEmail;
	$("[id^='modify']").click(function(){
		emplId = $(this).attr("id").slice(6);	
		emplName = $("#emplName" + emplId).text();
		emplEmail = $("#emplEmail" + emplId).text();
		$("#m_name").val(emplName);
		$("#m_email").val(emplEmail);
		$("#m_emplId").val(emplId);
		$("#myModal").modal();
	});

	$("[id^='delete']").click(function(){
		emplId = $(this).attr("id").slice(6);
		$("#d_emplId").val(emplId);	
		$("#del_modal").modal();	
	});
	
	$('#empl_list').DataTable();
});
</script>
<script src="/resources/js/signup.form.js"></script>
</content>