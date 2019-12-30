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
	 #aaa{
		 float:right; 
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
						<a href="#">냉장</a>
					</li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>

				<!-- Icon Cards-->
				<div class="row">
                    <div class="col-xl-2 col-sm-4 mb-3 dropdown">
						<div class="card text-white bg-danger o-hidden h-70" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">서울우유</div>
							</div>
						</div>
						<div class="dropdown-menu" aria-labelledby="pagesDropdown">
							<h6 class="dropdown-header">카테고리 : 유제품>우유</h6>
							<h6 class="dropdown-header">유통기한 : 2019/11/20</h6>
							<button>수정</button>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-danger o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">바이오요거트</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-warning o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">계란</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">삼다수</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">테라</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">고춧가루</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">배추김치</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">두부</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="#">냉동</a>
					</li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>

				<!-- Icon Cards-->
				<div class="row">
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">투게더</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">크루아상생지</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">소고기(국거리)</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">삼겹살</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">모짜렐라치즈</div>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-sm-4 mb-3">
						<div class="card text-white bg-success o-hidden h-70">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw"></i>
								</div>
								<div class="mr-auto">만두</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Breadcrumbs-->
				<c:forEach items="${saveplaceList}" var="saveplace" varStatus="list"> 
				<ol class="breadcrumb">
					<li class="breadcrumb-item" style="width: auto;">
						<!-- <a href="#" id="saveplaceName${saveplace.saveplace_seq}">${saveplace.saveplace_name}</a> -->
						<a href="#">
							<input id="saveplaceName${saveplace.saveplace_seq}" type="text"
								style="border:0px; background-color: transparent;" 
								value="${saveplace.saveplace_name}" disabled>
						</a>	
					</li>
					<li class="breadcrumb-item active">
						<i class="far fa-edit" onclick="updateSaveplace(${saveplace.saveplace_seq})"></i>
						<i class="far fa-trash-alt" onclick="deleteSaveplace(${saveplace.saveplace_seq})"></i>
					</li>
					
				</ol>
				</c:forEach>					
				
				
				<!-- Area Chart Example-->
<!-- 
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-chart-area"></i>
						Area Chart Example</div>
					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
					<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
				</div>
			
 -->
			</div>
					
			
			
			<div class="row">
				<div class="col" >
					<button type="button" class="btn btn-primary btn-toggle" onclick="showModal()" style="margin:10px 10px  10px  10px;" >+</button>
					<!-- <button type="button" class="btn btn-primary btn-toggle" data-toggle="modal" data-target="#saveplaceModal" style="margin:10px 10px  10px  10px;" >+</button>  -->
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
				<select id="comboStorage" class="form-control" required>
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
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			<button type="button" class="btn btn-success" onclick="regSaveplace()">등록</button>     
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
		
		// 보관장소 등록 팝업
	 	function showModal() {
			$("#saveplaceModal").modal();
		} 

	 // 보관장소 등록 처리
		function regSaveplace() {
			var saveplace = $("#inputSaveplace").val();
			var storage = $("#comboStorage").val();	
			//var fridgeSeq = "${fridgeSeq}";
			var fridgeSeq = 1;	

			alert(saveplace);
			alert(storage);
			alert(fridgeSeq);

			if(!saveplace) {
				alert("보관장소 이름을 입력해주세요.");
				$("#inputSaveplace").focus();
			}
			if(!storage) {
				alert("보관장소 분류를 선택해주세요.");
				$("#comboStorage").focus();
			}

			var dataParam = {
					"saveplace":saveplace,
					"storage":storage,
					"fridgeSeq":fridgeSeq
				}

 			$.ajax({
	            url:'/api/fridge/saveplace/reg',
	            type:'post',
	            dataType:'text',
	            contentType: 'application/json',
	            data:JSON.stringify(dataParam),
	            success:function(data){
	            	alert("보관장소가 등록되었습니다.");
	            	location.reload();
	            },error:function(data){
	            	alert(data.responseText);
	            }
	        });	
		}

		// 보관장소 이름 수정
		function updateSaveplace(seq){
			
			var saveplaceName = $("#saveplaceName"+ seq).val();

			if(!saveplaceName) {
				alert("보관장소 이름을 입력해주세요.");
				$("#saveplaceName"+ seq).focus();
				return;
			}
			
			var dataParam = {
					"saveplaceSeq":seq,
					"saveplaceName":saveplaceName
			}
			
		}		
		

		// 보관장소 삭제
		function deleteSaveplace(seq){

			//var saveplaceName = document.getElementById('saveplaceName'+ seq).firstChild.nodeValue;	
			var saveplaceName = $("#saveplaceName"+ seq).val();

			alert(seq);
			alert(saveplaceName);
			
			var dataParam = {"saveplaceSeq" :seq,
							 "saveplaceName":saveplaceName}

			if(confirm("삭제하시겠습니까?")){
				 $.ajax({
					url:'/api/fridge/saveplace/delete',
					type:'post',
					dataType:'text',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					success:function(data){
						alert("삭제되었습니다.");
						location.reload();
					},error:function(data){
						alert(data.responseText);
					}
				}); 
			}else{
				return;
			} 	
		}
	
	</script>
</body>



</html>
