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

<style>
.none-style-a-tag {
	text-decoration: none;
	font-size: medium;
	font-weight: bold;
	color: #fff;
}

.none-style-a-tag:hover {
	text-decoration: none;
	font-size: medium;
	font-weight: bold;
	color: #fff;
}
</style>

<body id="page-top">

	<!-- header 영역 -->
	<jsp:include page="../common/header.jsp"/>

	<div id="wrapper">

		<!-- Sidebar 영역 -->
		<jsp:include page="../common/sidebar.jsp"/>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<c:choose>
				
					<c:when test="${fn:length(fridgeList) > 0}">
						<c:forEach items="${fridgeList}" var="fridge">
							<ol class="breadcrumb">
								<a class="breadcrumb-item" href="/view/fridge/fridge?fridge=${fridge.fridge_seq}">${fridge.fridge_name}</a>
								<li class="breadcrumb-item active">Overview</li>
							</ol>
								<!-- Icon Cards-->
								<div class="row">
									<c:forEach items="${savefoodList}" var="savefood">
										<c:if test="${savefood.fridge_seq == fridge.fridge_seq}">
					                    <div class="col-xl-3 col-sm-4 mb-3">
											<div class="card text-white bg-danger o-hidden h-100">
												<a class="none-style-a-tag" href="/view/fridge/fridge?fridge=${fridge.fridge_seq}">
												<div class="card-body">
													<div class="card-body-icon">
														<i class="fas fa-fw"></i>
													</div>
													<c:choose>
														<c:when test="${savefood.danger_cnt != 0}">
														<div class="mr-5">${savefood.danger_food_name} 포함 ${savefood.danger_cnt} 건</div>
														</c:when>
														<c:otherwise>
														<div class="mr-5">없음</div>
														</c:otherwise>
													</c:choose>
												</div>
												</a>
											</div>
										</div>
										<div class="col-xl-3 col-sm-4 mb-3">
											<div class="card text-white bg-warning o-hidden h-100">
											<a class="none-style-a-tag" href="/view/fridge/fridge?fridge=${fridge.fridge_seq}">
												<div class="card-body">
													<div class="card-body-icon">
														<i class="fas fa-fw"></i>
													</div>
													<c:choose>
														<c:when test="${savefood.normal_cnt != 0}">
														<div class="mr-5">${savefood.normal_food_name} 포함  ${savefood.normal_cnt} 건</div>
														</c:when>
														<c:otherwise>
														<div class="mr-5">없음</div>
														</c:otherwise>
													</c:choose>
												</div>
												</a>
											</div>
										</div>
										<div class="col-xl-3 col-sm-4 mb-3">
											<div class="card text-white bg-success o-hidden h-100">
											<a class="none-style-a-tag" href="/view/fridge/fridge?fridge=${fridge.fridge_seq}">
												<div class="card-body">
													<div class="card-body-icon">
														<i class="fas fa-fw"></i>
													</div>
													<c:choose>
														<c:when test="${savefood.safe_cnt != 0}">
														<div class="mr-5">${savefood.safe_food_name} 포함  ${savefood.safe_cnt} 건</div>
														</c:when>
														<c:otherwise>
														<div class="mr-5">없음</div>
														</c:otherwise>
													</c:choose>
												</div>
											</a>
											</div>
										</div>						
										</c:if>
									</c:forEach>

							</div>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<ol class="breadcrumb">
							<li class="breadcrumb-item" style="color: #dc3545;">
								냉장고가 없습니다.<br>
								냉장고를 생성해주세요!
							</li>
						</ol>
					</c:otherwise>
					
				</c:choose>	
				
				<input type="button" class="btn btn-primary" onclick="fridgeManage()" value="냉장고관리">
				
			</div>
			<!-- /.container-fluid -->
			

			<!-- Sticky Footer 영역 -->
			<jsp:include page="../common/footer.jsp"/>

		</div>
		<!-- /.content-wrapper -->
		
		
	</div>
	<!-- /#wrapper -->
	
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

	 	function fridgeManage() {
	 		location.href = "/view/fridge/management";
		} 

	</script>
	
</body>



</html>
