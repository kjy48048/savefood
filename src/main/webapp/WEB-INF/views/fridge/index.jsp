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

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="/view/fridge/fridge?num=1">냉장고1</a>
					<!-- <a href="/view/fridge/fridge?num=${fridge.fridge_seq}">${fridge.fridge_name}</a> -->	
					</li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>
				<div class="row">
					냉장, 냉동
				</div>
				
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="/view/fridge/fridge?num=2">냉장고2</a>
					</li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>
				<div class="row">
					냉장, 냉동
				</div>					
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

	<!-- Saveplace Modal -->
	<div class="modal fade" id="saveplaceModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
			<div class="form-group">
				<label for="inputSaveplace">보관장소이름</label>
				<input type="text" id="inputSaveplace" class="form-control" required>	
			</div>
			<div class="form-group">
				<label for="storage">보관장소분류</label>
				<select id="storage" class="form-control" required>
					<c:forEach items="${storage}" var="key" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0 }">
								<option value="${key.saveplace_storage_code }" selected>${key.saveplace_storage_name }</option>
							</c:when>
							<c:otherwise>
								<option value="${key.saveplace_storage_code }" >${key.saveplace_storage_name }</option>
							</c:otherwise>
						</c:choose>	
					</c:forEach>						
				</select>
			</div>	
	      </div>
	      <div class="modal-footer"> 
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<input type="submit" class="btn btn-success" id="submit">	      
	      </div>
	    </div>
	  </div>
	</div>



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
