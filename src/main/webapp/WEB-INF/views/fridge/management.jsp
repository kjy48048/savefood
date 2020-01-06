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


	<style type="text/css">
	 #aaa {
		 float:right; 
	 }
	 .menuButton {
	 	font-size: 2.5em;
	 	border-style: solid;
	 	width: 1em;
	 	text-align: center;
	 	margin-right: 0.25em;
	 	cursor: Pointer;
	 }
	 
	 
	 
	 .each_place {
	 	cursor: Pointer;
	 }
	 .saveplace {
	 	margin-left: 1em;
	 }
	 .focus {
	 	background-color: #50bcdf;
	 }
	</style>

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
						<a href="#">냉장고관리</a>
					</li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>
			</div>
			
			<!-- 냉장고 관리 폼 -->
			<div class="container-fluid">
				<!-- 냉장고 관리 버튼부 -->
				<div>
				<ol class="breadcrumb">
					<li class="row">
						<i class="menuButton fas fa-plus"></i>
						<i class="menuButton fas fa-minus"></i>
						<i class="menuButton fas fa-angle-left"></i>
						<i class="menuButton fas fa-angle-right"></i>
						<i class="menuButton fas fa-angle-up"></i>
						<i class="menuButton fas fa-angle-down"></i>
						<i class="menuButton fas" style="width: 3em;">Save</i>
					</li>
				</ol>
				</div>
				<!--  -->
				<div class="row" style="border-style: solid;">
					<!-- 냉장고 관리 트리 -->
					<div class="modal-body-flex-row" role="document" style="border-style: solid;">
					<ol id="place_list">
						<li class="each_place">냉장고1</li>
						<li class="each_place saveplace">보관장소1</li>
						<li class="each_place">냉장고3</li>
						<li class="each_place">냉장고4</li>
					</ol>
					</div>
					<!--  냉장고 관리 입력부 -->
					<div class="modal-body-flex	" role="document" style="border-style: solid;">
						<div class="modal-body-flex-row"><div style="min-width: 80px; margin: auto;">이름 :</div>
						<input type="text" id="inputName" class="form-control" placeholder="이름" required="required">
						</div>
						<div class="modal-body-flex-row"><div style="min-width: 80px; margin: auto;">순서 :</div>
						<input type="text" id="inputOrder" class="form-control" placeholder="순서" required="required" disabled>
						</div>
						<div class="modal-body-flex-row"><div style="min-width: 80px; margin: auto;">분류 :</div>
						<input type="text" id="inputType" class="form-control" placeholder="분류" required="required" disabled>
						</div>
					</div>
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
		
		// 선택한 곳에 이벤트
		$('#place_list').on('click', 'li', function(event) {
			setFocus(this);
		});
		
		// fa-plus
		$('.fa-plus').on('click', function(event) {
			// focus가 존재하는 경우
			if($('#place_list').children().hasClass('focus')) {
				$('.focus').after("<li class='each_place'>새로운 보관장소</li>");
			} else {
				
				$('#place_list').append("<li class='each_place'>새로운 보관장소</li>");	
			}
		});
		
		// fa-minus
		$('.fa-minus').on('click', function(event) {
			// focus가 존재하는 경우
			if($('#place_list').children().hasClass('focus')) {
				$('.focus').remove();
			}
		});
		
		// fa-angle-left
		$('.fa-angle-left').on('click', function(event) {
			$('.focus').removeClass('saveplace');
		});
		
		// fa-angle-right
		$('.fa-angle-right').on('click', function(event) {
			$('.focus').addClass('saveplace');
		});
		
		// fa-angle-up
		$('.fa-angle-up').on('click', function(event) {
			var prev = $('.focus').prev()
			$('.focus').insertBefore(prev);
		});
		
		// fa-angle-down
		$('.fa-angle-down').on('click', function(event) {
			var next = $('.focus').next()
			$('.focus').insertAfter(next);
		});
		
		// 포커스 한 곳에 백그라운드 적용
		function setFocus(target) {
			$('#place_list').children().removeClass('focus');
			$(target).addClass('focus');
		}
	</script>
</body>



</html>
