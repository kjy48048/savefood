<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>SaveFood</title>

	<!-- Custom fonts for this template-->
	<link href="../../static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

	<!-- Page level plugin CSS-->
	<link href="../../static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="../../static/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- header 영역 -->
<jsp:include page="../common/header.jsp"/>

	<div id="wrapper">

<!-- Sidebar 영역 -->
<jsp:include page="../common/sidebar.jsp"/>

<!-- Main 영역 -->
	<div id="content-wrapper">	
		<div class="container-fluid">

			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<i>고객</i>
				</li>
				<li class="breadcrumb-item active">Overview</li>
			</ol>
			
			<input type="button" class="btn btn-primary" onclick="save()" value="저장">
			<p>
			
			<!-- DataTables Example -->
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable">
					<thead>
						<tr style="text-align : center">
							<th>번호</th>
							<th>아이디</th>
							<th>역할</th>
							<th>상태</th>
							<th>가입일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${memberList}" var="member" varStatus="memberList"> 
						<tr>
							<td style="text-align:center; vertical-align:middle;">
								<div>${memberList.count}</div>
							</td>
							<td style="vertical-align:middle;">${member.member_id}</td>
							<td style="text-align:center; vertical-align:middle;">
								<c:choose>
									<c:when test="${isEditable}">
										<select id="member-role-${memberList.count}" data-member-seq="${member.member_seq }" style="width:100%; height:100%;" onchange="roleChange(document.querySelector('#member-role-${memberList.count }'))">
											<c:forEach items="${role}" var="role" varStatus="roleList">
												<c:choose>
													<c:when test="${role.member_role_id == member.member_role_id}">
														<option value="${role.member_role_id}" selected>${role.member_role_name}</option>
													</c:when>
													<c:otherwise>
														<option value="${role.member_role_id}">${role.member_role_name}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<div> ${member.member_role_name} </div>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align:center; vertical-align:middle;">
								<select id="member-status-${memberList.count}" data-member-seq="${member.member_seq }" style="width:100%; height:100%;" onchange="statusChange(document.querySelector('#member-status-${memberList.count }'))">
									<c:forEach items="${status}" var="status" varStatus="statusList">
										<c:choose>
											<c:when test="${status.member_status_id == member.member_status }">
												<option value="${status.member_status_id}" selected>${status.member_status_name}</option>
											</c:when>
											<c:otherwise>
												<option value="${status.member_status_id}">${status.member_status_name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td style="text-align:center; vertical-align:middle;">${member.insert_date}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>		


<!-- /.container-fluid -->
		</div>
		
<!-- /.content-wrapper -->		
	</div>
	
<!-- /#wrapper -->
	</div>
		
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
				</div>
				<div class="modal-body">로그아웃 시 저장되어있던 세션정보가 사라집니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="javascript:logout();">확인</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../../static/vendor/jquery/jquery.min.js"></script>
	<script src="../../static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../../static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="../../static/vendor/chart.js/Chart.min.js"></script>
	<script src="../../static/vendor/datatables/jquery.dataTables.js"></script>
	<script src="../../static/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../../static/js/sb-admin.min.js"></script>

	<script>

		$(document).ready(function(){
			result = [<c:forEach var="member" items="${memberList}" varStatus="status">
				{memberSeq:${member.member_seq}, 
				oldMemberRole:${member.member_role_id}, 
				oldMemberStatus:${member.member_status},
				memberRole:0,
				memberStatus:0}
				<c:if test="${!status.last}">,</c:if>
			</c:forEach>];
			
			
			});

			function roleChange(element){
				result.forEach(function(item){
						if(item.memberSeq == element.dataset.memberSeq){
								if(item.oldMemberRole == element.value){
										item.memberRole = 0;
									}
								else{
										item.memberRole = parseInt(element.value);
									};
							};
					});
				
			};

			function statusChange(element){
				result.forEach(function(item){
					if(item.memberSeq == element.dataset.memberSeq){
							if(item.oldMemberStatus == element.value){
									item.memberStatus = 0;
								}
							else{
									item.memberStatus = parseInt(element.value);
								};
						};
				});
			};
			
			function save(){			
				var updateCnt = 0;

				result.forEach(function(item){
						if(item.memberRole != 0 || item.memberStatus != 0){
								updateCnt++;
							}
					});
				
				if(updateCnt == 0){
						alert("수정사항이 없습니다.");
						return;
					};
				
				 $.ajax({
						url:'/api/admin/customer/update',
						type:'post',
						dataType:'text',
						contentType:'application/json',
						data:JSON.stringify(result),
						success:function(data){
							alert("저장되었습니다.");
							location.reload();
						},error:function(data){
							alert(data.responseText);
						}
					}); 
			};
		
		function logout() {
			$.ajax({
		        url:'/api/member/logout',
		        type:'post',
		        dataType:'text',
		        contentType: 'application/json',
		        success:function(data){
		        	location.href = "/view/member/login";
		        },error:function(data){
		        	alert(data.responseText);
		        }
		    });
		}
	</script>
</body>

</html>
