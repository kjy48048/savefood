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
	<link href="../../static/css/food-style.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- header 영역 -->
	<jsp:include page="../common/header.jsp"/>

	<div id="wrapper">

		<!-- Sidebar 영역 -->
		<jsp:include page="../common/sidebar.jsp"/>

		<div id="content-wrapper">
			
			<div class="container-fluid">
	
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<i>식품등록</i>
					</li>
				</ol>
			
				<div class="utility-container">
					<div class="auto-reg-utility utility">
						<input class="utility-item" type="checkbox" id="check-auto-reg" checked="checked">
						<label class="utility-item" for="check-auto-reg"><a></a><span>자동등록</span></label>
						<select class="utility-item" id="fridge-list">
							<c:forEach items="${fridgeList}" var="fridge" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1}">
										<option value="${fridge.fridge_seq}" selected>${fridge.fridge_name}</option>									
									</c:when>
									<c:otherwise>
										<option value="${fridge.fridge_seq}">${fridge.fridge_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<select class="utility-item" id="saveplace-list">
							<c:forEach items="${saveplaceList}" var="saveplace" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1}">
										<option value="${saveplace.saveplace_seq}" selected>${saveplace.saveplace_name}</option>									
									</c:when>
									<c:otherwise>
										<option value="${saveplace.saveplace_seq}">${saveplace.saveplace_seq}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="search-utility utility">
						<input class="utility-item" type="text" id="search-text" placeholder="검색"/>
						<input type="button" class="search-btn utility-item"/>
					</div>
				</div>
			
				<div class="category-container">
					<div class="item-wrapper">
						<a class="category-item" href="#" data-category-seq="0">전체</a>
					</div>
					<c:forEach items="${categoryList}" varStatus="status" var="category">
						<div class="item-wrapper" onclick="foodReload()">
							<a class="category-item" href="#"  data-category-seq="${category.category_seq}">${category.category_name}</a>
						</div>
					</c:forEach>
				</div>
				
				<div class="food-container" >
					<c:forEach items="${foodListList}" var="foodList" varStatus="status">
						<div class="food-list">
							<h5>${foodList[0].category_name }</h5>
							<div class="food-wrapper">
								<c:forEach items="${foodList}" var="food">
									<div class="food">
										<img src="${pageContext.request.contextPath}${food.food_img}" onerror="this.src='${pageContext.request.contextPath}/resources/img/not-found.png'"/> 
										<span>${food.food_name }</span>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
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

	<!-- Saveplace Modal -->



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

	 	function showModal() {
			$("#saveplaceModal").modal();
		} 

	</script>
	
</body>



</html>
