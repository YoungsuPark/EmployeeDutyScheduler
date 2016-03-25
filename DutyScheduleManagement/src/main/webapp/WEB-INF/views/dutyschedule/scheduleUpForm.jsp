<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<body>
<div class="container text-center"><br><br><br>
	<form id="schedule_frm" name="schedule_frm" action="/schedule/write_ok" method="post" >
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th class="th-left" colspan="2">
							<a href="/schedule"><button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> return </button></a>
						</th>
						<th class="th-right" colspan="1"><a href="/schedule/write?month=${month-1}&year=${year}">◀</a></th>
						<th class="th-center" colspan="1"><h3>Scheduling<br>Mode<br>${year}. ${month+1 }</h3></th>
						<th class="th-left" colspan="1"><a href="/schedule/write?month=${month+1 }&year=${year}">▶</a></th>
						<th class="th-right"colspan="2">
							<button type="submit" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> Save Schedule </button>
						</th>
					</tr>
					<colgroup>
						<col width = "14.286%" />
						<col width = "14.286%" />
						<col width = "14.286%" />
						<col width = "14.286%" />
						<col width = "14.286%" />
						<col width = "14.286%" />
						<col width = "14.286%" />		
					</colgroup>
					<tr class="success">
						<th class="th-center" scope="col">Sun</th>
						<th class="th-center" scope="col">Mon</th>
						<th class="th-center" scope="col">Tue</th>
						<th class="th-center" scope="col">Wed</th>
						<th class="th-center" scope="col">Thur</th>
						<th class="th-center" scope="col">Fri</th>
						<th class="th-center" scope="col">Sat</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="${weekCount-1}" var="i">
						<tr>
							<c:forEach begin="${i*7}" end="${i*7+6}" var="j">
								<c:choose>
									<c:when test="${currentCalendarDay[j] > 35}">
										<td class="active"> ${currentCalendarDay[j]-35} </td>
									</c:when>
									<c:when test="${ ((j+1) % 7) eq 0 || ((j+1) % 7) eq 1}">
										<td class="danger">${currentCalendarDay[j]} </td>
									</c:when>
									<c:otherwise>
										<td class="info">${currentCalendarDay[j]} </td>
									</c:otherwise>
								</c:choose>
							</c:forEach>					
						</tr>
						<c:choose>
							<c:when test="${fn:length(dutyScheduleList) > 0 }">
								<tr>
									<c:forEach begin="${i*7}" end="${i*7+6}" items="${dutyScheduleList}" var="row">
										<c:choose>
											<c:when test="${row.date eq 0 }">
												<td></td>
											</c:when>
											<c:otherwise>
												<td>
													<div style="width:140px; height:115px">
														<div><p class="p-name" id="name${row.date}">${row.name}</p></div>
														<div><p class="p-email" id="email${row.date}">${row.email}</p></div>		
														<input class="form-control" id="empl${row.date}" name="dutySchedules[${row.date-1}].emplId" type="hidden" value="${row.emplId }">
														<input class="form-control" id="empl_duty${row.date}" name="dutySchedules[${row.date-1}].emplDutyScheduleId" type="hidden" value="${row.emplDutyScheduleId }">
														<input class="form-control" name="dutySchedules[${row.date-1}].year" type="hidden" value="${row.year}">
														<input class="form-control" name="dutySchedules[${row.date-1}].month" type="hidden" value="${row.month}">
														<input class="form-control" id="date${row.date}" name="dutySchedules[${row.date-1}].date" type="hidden" value="${row.date}">
														<button class="btn btn-info btn-xs" id="disp_empls${row.date}" type="button" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>	
													</div>
												</td>
											</c:otherwise>				
										</c:choose>
									</c:forEach>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<c:forEach begin="${i*7}" end="${i*7+6}" var="j">
										<c:choose>
											<c:when test="${currentCalendarDay[j] > 35}">
												<td><div style="width:140px; height:115px"></div></td>
											</c:when>
											<c:otherwise>
												<td>
													<div style="width:140px; height:115px">
														<h4><span class="label label-success" id="name${currentCalendarDay[j]}">Empty</span></h4>
														<h5><span class="label label-success" id="email${currentCalendarDay[j]}"></span></h5>
														<input class="form-control" id="empl${currentCalendarDay[j]}" name="dutySchedules[${currentCalendarDay[j]-1}].emplId" type="hidden" value="39">
														<input class="form-control" name="dutySchedules[${currentCalendarDay[j]-1}].year" type="hidden" value="${year}">
														<input class="form-control" name="dutySchedules[${currentCalendarDay[j]-1}].month" type="hidden" value="${month+1}">
														<input class="form-control" id="date${currentCalendarDay[j]}" name="dutySchedules[${currentCalendarDay[j]-1}].date" type="hidden" value="${currentCalendarDay[j]}">
														<button class="btn btn-info btn-xs" id="disp_empls${currentCalendarDay[j]}" type="button" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>	
													</div>	
												</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>				
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>	
				</tbody>
			</table>
		</div><br>
	</form>
	<!-- Empl_list Modal -->
	<div class="modal fade" id="empl_list_modal" role="dialog">
		<div class="modal-dialog modal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Employee List</h4>
				</div>
				 <div class="modal-body">
				 	<div class="table-responsive">
				 		<table id="empl_list" class="table table-bordered text-center" style="cellspacing:0; width:100%">
				 			<colgroup>
				 				<col width="30%" />
				 				<col width="60%" />
				 				<col width="10%" />
				 			</colgroup>
				 			<thead>
				 				<tr>
									<th class="th-center" scope="col" >Name</th>
									<th class="th-center" scope="col">Email Address</th>
									<th class="th-center" scope="col">Option</th>
				 				</tr>
				 			</thead>
				 			<tbody>
				 				<c:forEach items = "${emplList}" var = "row">
									<tr>
										<td id="emplName${row.emplId}">${row.name}</td>
										<td id="emplEmail${row.emplId}">${row.email}</td>
										<td><button class="btn btn-info btn-xs" id="select${row.emplId}" type="submit">Select</button></td>
									</tr>	
								</c:forEach>		
				 			</tbody>
				 		</table>	 	
				 	</div>			 
				</div>
			</div>  
		</div>
	</div>		
</div><br><br>
</body>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function(){
	var emplId;
	var emplName;
	var emplEmail;
	var dateId;
	
	$("[id^='disp_empls'").click(function(){
		dateId = $(this).attr("id").slice(10);
		$("#empl_list_modal").modal();
	});
	
	$("[id^='select']").click(function(){
		emplId =$(this).attr("id").slice(6);
		emplName = $("#emplName" + emplId).text();
		emplEmail = $("#emplEmail" +emplId).text()
		$("#empl"+dateId).val(emplId);
		$("#name"+dateId).text(emplName);
		$("#email"+dateId).text(emplEmail)
		$("#empl_list_modal").modal("hide");
	});
	
	$('#empl_list').DataTable();
});
</script>
</content>