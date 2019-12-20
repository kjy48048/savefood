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
					<i>카테고리</i>
				</li>
				<li class="breadcrumb-item active">Overview</li>
			</ol>
			
			<input type="button" class="btn btn-primary" onclick="regPop()" value="추가">
			<input type="button" id="deleteBtn" class="btn btn-primary" onclick="categoryDelete()" value="삭제">
			<p>
			
			<!-- DataTables Example -->

		    <div class="table-responsive">
				<table class="table table-bordered" id="dataTable">
					<thead>
						<tr style="text-align : center">
							<th><input type="checkbox"></th>
							<th>순서</th>
							<th>카테고리명</th>
							<th>등록된 식품 수</th>
							<th>수정</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categoryList}" var="category" varStatus="list"> 
						<tr>
							<td style="text-align:center; vertical-align:middle;">
								<input type="checkbox" value="${category.category_seq}" class="categorySeq">
							</td>
							<td style="text-align:center; vertical-align:middle;">${category.ord}</td>
							<td style="vertical-align:middle;">
								<input type="text" class="form-control" style="border:0px; background:#ffffff" value="${category.category_name}" disabled>
								<%-- <a href="/view/admin/category/food">${category.category_name}</a> --%>
							</td>
							<td style="text-align:center; vertical-align:middle;">
								<a href="/view/admin/category/food?category=${category.category_seq}">${category.food_count}</a>
							</td><%-- ?category=${category.category_seq} --%>
							<td style="text-align:center; vertical-align:middle;">
								<input type="button" class="btn btn-primary updateBtn" value="수정">
							</td>
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

	<!-- regPop -->
	<div class="modal fade" id="regPop" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">카테고리 추가</h5>
				</div>
				<div class="modal-body">카테고리 : 
					<input type="text" id="inputCategoryName" class="form-control" placeholder="카테고리명" required="required">
				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-success" onclick="checkDuplicate()">완료</button>
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

		/* 등록팝업 */
		function regPop() {
			$("#regPop").modal()
		}

		/* 등록중복체크 */
		function checkDuplicate() {
			var categoryName = $("#inputCategoryName").val();

			if(!categoryName) {
				alert("카테고리명을 입력해주세요.");
				$("#inputCategoryName").focus();
				return;
			}
			
			var dataParam = {
				"categoryName":$("#inputCategoryName").val()
			}
			
			$.ajax({
				url:'/api/admin/category/check',
				type:'post',
				dataType:'json',
				contentType:'application/json',
				data:JSON.stringify(dataParam),
				success:function(data){
					if(Object.keys(data).length > 0){
						var str = "";
						$.each(data, function(i){
							str += data[i].category_name+"\n";
							})
						if(confirm("다음과 같이 유사한 카테고리가 존재합니다. 계속 진행하시겠습니까?\n" + str)){
							cateReg();
						}else{
							return;
						}
					}else{
						cateReg();
					}
				},error:function(data){
					alert(data.responseText);
				}
			});
		}

		/* 등록 */
		function cateReg() {
			var categoryName = $("#inputCategoryName").val();
			
			var dataParam = {
				"categoryName":$("#inputCategoryName").val()
			}
			
			$.ajax({
				url:'/api/admin/category/reg',
				type:'post',
				dataType:'text',
				contentType:'application/json',
				data:JSON.stringify(dataParam),
				success:function(data){
					alert(categoryName + " 카테고리 등록이 완료되었습니다.");
					location.href = "/view/admin/category";
				},error:function(data){
					alert(data.responseText);
				}
			});
		}

		/* 수정 */
		$('.updateBtn').click(function(){
			var dataParam = {
					"categorySeq":$(this).parent().parent().children(":first-child").children().val(),
					"categoryName":$(this).parent().parent().children(":nth-child(3)").children().val()	
			}
			var btn = $(this);
			var inputBox = $(this).parent().parent().children(":nth-child(3)").children();
			
			if (btn.val() == "수정") {
				inputBox.removeAttr("disabled");
				inputBox.attr("style", "border:1px solid #000000; background:#ffffff");
				
				var len = inputBox.val().length;
				inputBox[0].focus();
				inputBox[0].setSelectionRange(len, len);
				
				btn.attr("value", "저장");

			}else {
 				 $.ajax({
					url:'/api/admin/category/update',
					type:'post',
					dataType:'text',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					success:function(data){
						alert("수정되었습니다.");
					},error:function(data){
						alert(data.responseText);
					}
				}); 
				
				inputBox.attr("disabled", "disabled");
				inputBox.attr("style", "border:0px; background:#ffffff");
				
				btn.attr("value", "수정");
			}
		});
			

		/* 삭제 */
		function categoryDelete() {
			/* 체크된 것들 가져와야함 */
			var dataParam = new Array();
			var checkedCategory = $(".categorySeq:checked");

			if(checkedCategory.length == 0){
				alert("삭제할 카테고리를 선택해주세요.");
				return;
				}
			
			var str = "";
			
			for(var i = 0; i < checkedCategory.length; i++){
				var categorySeq = {"categorySeq":checkedCategory.get(i).value};
				str += checkedCategory.get(i).parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling.firstElementChild.value + "\n";
				dataParam.push(categorySeq);				
				}
			if(confirm("삭제하시겠습니까? \n" + str)){
				 $.ajax({
					url:'/api/admin/category/delete',
					type:'post',
					dataType:'text',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					success:function(data){
						alert("삭제되었습니다.");
						location.href = "/view/admin/category";
					},error:function(data){
						alert(data.responseText);
					}
				}); 
			}else{
				return;
			}	
		}
		
		/* food로 이동 */
		function pageMove() {
			var dataParam = {
					"categorySeq":$("#categorySeq").val()
			}

			var inputBox = document.getElementById("categoryName");
			
				location.href = "/view/admin/category/food";
			
		}
	</script>
</body>

</html>
