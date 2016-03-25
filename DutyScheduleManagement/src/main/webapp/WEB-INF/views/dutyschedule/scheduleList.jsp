<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<body>
<div class="container text-center"><br><br><br>
	<div class="table-responsive">
		<table class="table ">
			<thead>
				<tr>
					<th class="th-left" colspan="2"></th>
					<th class="th-right" colspan="1"><a href="/schedule?month=${month-1}&year=${year}">◀</a></th>
					<th class="th-center"colspan="1"><h3>DutySchedule ${year}. ${month+1 }</h3></th>
					<th class="th-left" colspan="1"><a href="/schedule?month=${month+1 }&year=${year}">▶</a></th>
					<th class="th-right" colspan="2">
						<a href="/schedule/write">
							<button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Scheduling Mode </button>
						</a>
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
									<td class="active">
										${currentCalendarDay[j]-35}
									</td>
								</c:when>
								<c:when test="${ ((j+1) % 7) eq 0 || ((j+1) % 7) eq 1}">
									<td class="danger">${currentCalendarDay[j]}</td>
								</c:when>
								<c:otherwise>
									<td class="info">${currentCalendarDay[j]}</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>					
					</tr>
					<c:choose>
						<c:when test="${fn:length(dutyScheduleList) > 0 }">
							<tr>
								<c:forEach begin="${i*7}" end="${i*7+6}" items="${dutyScheduleList}" var="row">
									<td>
										<c:choose>
											<c:when test="${row.date == date && row.month == month+1 }">
												<div style="width:140px; height:100px; color: pink">
											</c:when>
											<c:otherwise>
												<div style="width:140px; height:100px">
											</c:otherwise>
										</c:choose>			
											<div><p class="p-name" >${row.name}</p></div>
											<div><p class="p-email">${row.email}</p></div>		
										</div>
									</td>
								</c:forEach>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<c:forEach begin="${i*7}" end="${i*7+6}" var="j">
									<td>
										<div style="width:120px; height:80px"></div>
									</td>
								</c:forEach>
							</tr>
						</c:otherwise>
					</c:choose>		
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<br><br>
</body>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</content>