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
	<link href="../../../static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

	<!-- Page level plugin CSS-->
	<link href="../../../static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="../../../static/css/sb-admin.css" rel="stylesheet">

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

			<input type="button" class="btn btn-primary" onclick="regPop()" value="추가">
			<input type="button" id="deleteBtn" class="btn btn-primary" onclick="foodDelete()" value="삭제">
			<p>
		
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<i>${foodList[0].category_name}</i>
				</li>
				<li class="breadcrumb-item active">Overview</li>
			</ol>
			
			<!-- DataTables Example -->
		 		<div class="card-body">
		            <div class="table-responsive">
						<table class="table table-bordered" id="dataTable">
							<thead>
								<tr style="text-align : center">
									<th><input type="checkbox"></th>
									<th>이미지</th>
									<th>식품명</th>
									<th>유통기한</th>
									<th>수정</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${foodList}" var="food" varStatus="list"> 
								<tr>
									<td style="text-align:center; vertical-align:middle;">
										<input type="checkbox" value="${food.food_seq}" class="foodSeq">
									</td>
									<td style="text-align:center; vertical-align:middle; width: 100px; height:100px; overflow:hidden;">
									<c:choose>
										<c:when test="${empty food.food_img}">
											<img id="previewImg${food.food_seq}" style="width: 70px; height:auto;" src="${pageContext.request.contextPath}/resources/img/no-img.png">
										</c:when>
										<c:otherwise>
											<img id="previewImg${food.food_seq}" style="width: 70px; height:auto;" src="${pageContext.request.contextPath}${food.food_img}" onerror="this.src='${pageContext.request.contextPath}/resources/img/not-found.png'">
										</c:otherwise>
									</c:choose>
										<input type="file" id="updateImg${food.food_seq}" style="display: none;" onchange="preview(false, document.getElementById('updateImg${food.food_seq}'), document.getElementById('previewImg${food.food_seq}'))">
										<input type="button" id="updateButton${food.food_seq}" onclick="document.getElementById('updateImg${food.food_seq}').click();" value="수정" style="display: none;">
									</td>
									<td style="vertical-align:middle;">
										<input id="foodName${food.food_seq }" type="text" class="form-control" style="border:0px; background:#ffffff" value="${food.food_name}" disabled>
									</td>
									<td style="text-align:center; vertical-align:middle;">
										<input id="foodExpiDate${food.food_seq }" type="text" class="form-control" style="border:0px; background:#ffffff" value="${food.food_expi_date}" disabled>
									</td>
									<td style="text-align:center; vertical-align:middle;">
										<input type="button" class="btn btn-primary updateBtn" value="수정">
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
		            </div>
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
				<div class="modal-header" >
					<h5 class="modal-title" id="exampleModalLabel">식품 추가</h5>
				</div>
				<div class="modal-body-flex-row" style="overflow: hidden;"><div style="min-width: 80px;  margin: auto 0;">이미지 :</div>
					<img id="previewImg" src="${pageContext.request.contextPath}/resources/img/add-img.png" style="width:70px; height: auto; border: 1px solid grey" onclick="addImg()">
					<input type="file" id="inputImg" style="width:0px; height:0px;" onchange="preview(true)">
				</div>
				<div class="modal-body-flex-row"><div style="min-width: 80px; margin: auto;">식품명 :</div>
					<input type="text" id="inputFoodName" class="form-control" placeholder="식품명" required="required">
				</div>
				<div class="modal-body-flex-row"><div style="min-width: 80px; margin: auto;">유통기한 :</div>
					<input type="number" id="inputExpiDate" class="form-control" placeholder="0" required="required" maxlength="3" oninput="numberMaxLength(this)">
					<div style="min-width: 30px; margin: auto; text-align: center;">일</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-success" onclick="checkDuplicate()">완료</button>
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
	<script src="../../../static/vendor/jquery/jquery.min.js"></script>
	<script src="../../../static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../../../static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="../../../static/vendor/chart.js/Chart.min.js"></script>
	<script src="../../../static/vendor/datatables/jquery.dataTables.js"></script>
	<script src="../../../static/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../../../static/js/sb-admin.min.js"></script>
	<script src="../../../static/js/jquery.form.js"></script>

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
			var foodName = $("#inputFoodName").val();
			var expiDate = $("#inputExpiDate").val();

			if(!foodName) {
				alert("식품명을 입력해주세요.");
				$("#inputFoodName").focus();
				return;
			}
			if(!expiDate) {
				alert("유통기한을 입력해주세요.");
				$("#inputExpiDate").focus();
				return;
			}
			
			var dataParam = {
				"foodName":foodName,
				"foodExpiDate":expiDate,
			}
			
			$.ajax({
				url:'/api/admin/food/check',
				type:'post',
				dataType:'json',
				contentType:'application/json',
				data:JSON.stringify(dataParam),
				success:function(data){
					if(Object.keys(data).length > 0){
						var str = "";
						$.each(data, function(i){
							str += "카테고리 : " + data[i].category_name + ", 식품 : " + data[i].food_name+"\n";
							})
						if(confirm("다음과 같이 유사한 식품정보가 존재합니다. 계속 진행하시겠습니까?\n" + str)){
							foodReg();
						}else{
							return;
						}
					}else{
						foodReg();
					}
				},error:function(data){
					alert(data.responseText);
				}
			});
		}

		/* 등록 */
		function foodReg() {
			var foodName = $("#inputFoodName").val();
			var expiDate = $("#inputExpiDate").val();
			var categorySeq = "${categorySeq}";
			var foodImg  = $("#inputImg")[0].files[0];

			if(!foodName) {
				alert("식품명을 입력해주세요.");
				$("#inputFoodName").focus();
				return;
			}
			if(!expiDate) {
				alert("유통기한을 입력해주세요.");
				$("#inputExpiDate").focus();
				return;
			}
			
			var dataParam = {
					"foodName":foodName,
					"foodExpiDate":expiDate,
					"categorySeq":categorySeq
				}

			$.ajax({
				url:'/api/admin/food/reg',
				type:'post',
				dataType:'json',
				contentType:'application/json',
				data:JSON.stringify(dataParam),
				success:function(data){
					if(!foodImg){
						alert(foodName +  " 식품 등록이 완료되었습니다.");
						location.reload();
						}
					else{
						regImg(data, foodImg, foodName);
						}
				},error:function(data){
					alert(data.responseText);
				}
			});
		}
		
		/*이미지 등록 */
		function regImg(data, foodImg, foodName) {
			var foodSeq  = data.food_seq;
			var formData = new FormData();

			formData.append("foodSeq", foodSeq);
			formData.append("foodImg", foodImg);

			$.ajax({
				url:'/api/admin/food/regImg',
				type:'post',
				dataType:'text',
				enctype:'multipart/form-data',
				processData:false,
				contentType:false,
				data:formData,
				success:function(response){
					if(!foodName){
						alert("수정이 완료되었습니다.");
						}
					else{
						alert(foodName +  " 식품 등록이 완료되었습니다.");
						location.reload();
						}
				},error:function(response){
					if(response.status == 413){
						alert("이미지 사이즈가 너무 큽니다.");
						}
					alert(response.responseText);
				}
			});
		}

		/* 유통기한 입력 제한 */
		function numberMaxLength(e){
	        if(e.value.length > e.maxLength){
	            e.value = e.value.slice(0, e.maxLength);
	        }
		}
		/* onclick="onclick=document.all.file.click()" */
		/* 수정 */
		$('.updateBtn').click(function(){
			var foodSeq = $(this).parent().parent().children(":first-child").children().val();
			var foodName = $("#foodName"+foodSeq).val();
			var foodExpiDate = $("#foodExpiDate"+foodSeq).val();
			var updateImg = $("#updateImg"+foodSeq).get(0).files[0];

			if(!foodName) {
				alert("식품명을 입력해주세요.");
				$("#foodName"+foodSeq).focus();
				return;
			}
			if(!foodExpiDate) {
				alert("유통기한을 입력해주세요.");
				$("#foodExpiDate"+foodSeq).focus();
				return;
			}
			
			var dataParam = {
					"foodSeq":foodSeq,
					"foodName":foodName,
					"foodExpiDate":foodExpiDate
			}
			
			var btn = $(this);
			var inputFoodName = $("#foodName"+foodSeq);
			var inputExpiDate = $("#foodExpiDate"+foodSeq);
			var updateBtn = $("#updateButton"+foodSeq);
			
			if (btn.val() == "수정") {
				inputFoodName.removeAttr("disabled");
				inputFoodName.attr("style", "border:1px solid #000000; background:#ffffff");
				inputExpiDate.removeAttr("disabled");
				inputExpiDate.attr("style", "border:1px solid #000000; background:#ffffff");
				updateBtn.removeAttr("style");
				
				var len = inputFoodName.val().length;
				inputFoodName[0].focus();
				inputFoodName[0].setSelectionRange(len, len);
				
				btn.attr("value", "저장");

			}else {
 				 $.ajax({
					url:'/api/admin/food/update',
					type:'post',
					dataType:'json',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					success:function(data){
						if(!updateImg){
							alert("수정이 완료되었습니다.");
							}
						else{
							regImg(data, updateImg);
							}
					},error:function(data){
						alert(data.responseText);
					}
				}); 
				
 				inputFoodName.attr("disabled", "disabled");
 				inputFoodName.attr("style", "border:0px; background:#ffffff");
 				inputExpiDate.attr("disabled", "disabled");
 				inputExpiDate.attr("style", "border:0px; background:#ffffff");
 				updateBtn.attr("style", "display: none;");
				
				btn.attr("value", "수정");
			}
		});

		/* 삭제 */
		function foodDelete() {
			/* 체크된 것들 가져와야함 */
			var dataParam = new Array();
			var checkedFood = $(".foodSeq:checked");

			if(checkedFood.length == 0){
				alert("삭제할 식품을 선택해주세요.");
				return;
			}
			
			var str = "";
			
			for(var i = 0; i < checkedFood.length; i++){
				var foodSeq = {"foodSeq":checkedFood.get(i).value};
				str += $("#foodName"+checkedFood.get(i).value).val() + "\n";
				dataParam.push(foodSeq);				
				}
			if(confirm("삭제하시겠습니까? \n" + str)){
				 $.ajax({
					url:'/api/admin/food/delete',
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

		/*이미지 첨부*/
		function addImg() {
			$("#inputImg").click();
		}
		
		/*이미지 미리보기*/
		function preview(isNew, file, img) {
			var reader = new FileReader;

			if(isNew){
				reader.onload = function(){
					$("#previewImg").attr("src", reader.result);
					}

				reader.readAsDataURL($("#inputImg").get(0).files[0]);
				}
			else{
				reader.onload = function(){
					img.src = reader.result;
					}

				reader.readAsDataURL(file.files[0]);
				}
		}
	</script>
</body>

</html>
