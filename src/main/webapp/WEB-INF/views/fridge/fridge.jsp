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
	<link href="../../static/css/fridge-style.css" rel="stylesheet">


	<style type="text/css">
	 #aaa{
		 float:right; 
	 }
	</style>

</head>

<body id="page-top" >

	<!-- header 영역 -->
	<jsp:include page="../common/header.jsp"/>

	<div id="wrapper">

		<!-- Sidebar 영역 -->
		<jsp:include page="../common/sidebar.jsp"/>

		<div id="content-wrapper">
			<div class="container-fluid">
				<c:set var="saveplaceSeq" value="0"/>
				<c:forEach items="${savefoodList}" var="savefood" varStatus="status"> 
				<c:if test="${saveplaceSeq == 0 }">
				<c:set var="saveplaceSeq" value="${savefood.saveplace_seq }"/>
				<div class="saveplace-container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item ml-auto ml-md-0" style="width: auto;">
							<!-- <a href="#" id="saveplaceName${saveplace.saveplace_seq}">${saveplace.saveplace_name}</a> -->
								<input id="saveplaceName${savefood.saveplace_seq}" type="text"
									style="border:0px; background-color: transparent;" 
									value="${savefood.saveplace_name}" disabled/>
						</li>
						<li class="ml-auto mr-md-0" style="color: #6c757d;">
							<i id="saveplaceEdit${savefood.saveplace_seq}"
								class="far fa-edit" onclick="updateSaveplace(${savefood.saveplace_seq})"></i>
							<i class="far fa-trash-alt" onclick="deleteSaveplace(${savefood.saveplace_seq})"></i>
						</li>
					</ol>
					<div class="food-wrapper">
				</c:if>
				<c:if test="${saveplaceSeq != 0 && saveplaceSeq != savefood.saveplace_seq }">
				<c:set var="saveplaceSeq" value="${savefood.saveplace_seq }"/>
				</div>
				</div>
				<div class="saveplace-container">
					<ol class="breadcrumb">
						<li class="breadcrumb-item ml-auto ml-md-0" style="width: auto;">
							<!-- <a href="#" id="saveplaceName${saveplace.saveplace_seq}">${saveplace.saveplace_name}</a> -->
								<input id="saveplaceName${savefood.saveplace_seq}" type="text"
									style="border:0px; background-color: transparent;" 
									value="${savefood.saveplace_name}" disabled/>
						</li>
						<li class="ml-auto mr-md-0" style="color: #6c757d;">
							<i id="saveplaceEdit${savefood.saveplace_seq}"
								class="far fa-edit" onclick="updateSaveplace(${savefood.saveplace_seq})"></i>
							<i class="far fa-trash-alt" onclick="deleteSaveplace(${savefood.saveplace_seq})"></i>
						</li>
					</ol>
					<div class="food-wrapper">
				</c:if>
					<c:if test="${savefood.savefood_seq != null}">
					<div class="food">
						<input type="checkbox" value="${savefood.savefood_seq }" id="delete-savefood-${savefood.savefood_seq }" class="checkbox-none delete-savefood" disabled/>
						<label for="delete-savefood-${savefood.savefood_seq }">
							<img src="${pageContext.request.contextPath}${savefood.food_img}" onerror="this.src='${pageContext.request.contextPath}/resources/img/not-found.png'"/>
							<span>${pageContext.request.contextPath}${savefood.savefood_name}</span>
							<c:choose>
								<c:when test="${savefood.savefood_risk == 0 }">
									<div class="savefood-remain-day savefood-danger">
								</c:when>
								<c:when test="${savefood.savefood_risk == 1 }">
									<div class="savefood-remain-day savefood-warning">
								</c:when>
								<c:when test="${savefood.savefood_risk == 2 }">
									<div class="savefood-remain-day savefood-safe">
								</c:when>
							</c:choose>	
								<span>
								<c:choose>
									<c:when test="${savefood.savefood_remain_day > 99}">
										+99
									</c:when>
									<c:otherwise>
										${savefood.savefood_remain_day }
									</c:otherwise>
								</c:choose>
								</span>
							</div>
						</label>
					</div>
					</c:if>
					<c:if test="${status.last}">
						</div>
						</div>
					</c:if>
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
				<div class="col" style="align-items: flex-end; justify-content:flex-end; display: flex;">
					<button id="cancel-delete-btn" type="button" class="btn btn-secondary btn-toggle btn-hidden" onclick="changeDeleteMode(true)" style=" margin:10px 10px  10px  10px;">취소</button>
					<button id="batch-delete-btn" type="button" class="btn btn-primary btn-toggle" onclick="changeDeleteMode(false)" style=" margin:10px 10px  10px  10px;">선택삭제</button>
					<button type="button" class="btn btn-primary btn-toggle" onclick="showModal()" style=" margin:10px 10px  10px  10px;" >+</button>
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
	
	<!-- Saveplace Modal -->
	<div class="modal fade" id="savefoodModal" data-savefood-seq="" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" >
					<h5 class="modal-title" id="exampleModalLabel">식품 정보</h5>
				</div>
				<div class="modal-body-flex-row"><span class="modal-label">식품명  </span>
					<input type="text" id="input-food-name" class="form-control" disabled>
				</div>
				<div class="modal-body-flex-row"><span class="modal-label">식품종류  </span>
					<input type="text" id="input-food-category" class="form-control" disabled>
					<%-- <select class="input-item" id="input-category-list" disabled>
							<c:forEach items="${categoryList}" var="category" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1}">
										<option value='' selected>카테고리 선택</option>
										<option value="${category.category_seq}">${category.category_name}</option>							
									</c:when>
									<c:otherwise>
										<option value="${category.category_seq}">${category.category_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<select class="input-item" id="input-food-list" disabled>
							
						</select> --%>
				</div>
				<div class="modal-body-flex-row"><span class="modal-label">*저장장소  </span>
					<select class="input-item" id="input-fridge-list" disabled>
							<c:forEach items="${fridgeList}" var="fridge" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1}">
										<option value="${fridge.fridge_seq}" selected>${fridge.fridge_name}</option>
										<c:set var="inputFridgeSeq" value="${fridge.fridge_seq}"/>							
									</c:when>
									<c:otherwise>
										<option value="${fridge.fridge_seq}">${fridge.fridge_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<select class="input-item" id="input-saveplace-list" disabled>
							<c:forEach items="${saveplaceList}" var="saveplace" varStatus="status">
								<c:choose>
									<c:when test="${inputFridgeSeq == saveplace.fridge_seq && status.count == 1}">
										<option data-fridge-seq="${saveplace.fridge_seq}" data-storage-code="${saveplace.saveplace_storage_code }"  value="${saveplace.saveplace_seq}" selected>${saveplace.saveplace_name}</option>									
									</c:when>
									<c:when test="${inputFridgeSeq == saveplace.fridge_seq}">
										<option data-fridge-seq="${saveplace.fridge_seq}" data-storage-code="${saveplace.saveplace_storage_code }"  value="${saveplace.saveplace_seq}">${saveplace.saveplace_name}</option>									
									</c:when>
									<c:otherwise>
										<option class="unselected-fridge" data-fridge-seq="${saveplace.fridge_seq}"  data-storage-code="${saveplace.saveplace_storage_code }"  value="${saveplace.saveplace_seq}">${saveplace.saveplace_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				</div>
				<div class="modal-body-flex-row"><span class="modal-label">유통기한  </span>
					<input type="date" id="input-expi-date" class="form-control"  placeholder="" max="9999-12-31" disabled>
				</div>
				<div class="modal-body-flex-row"><span class="modal-label">식품량  </span>
					<input type="text" id="input-food-quantity" class="form-control" placeholder="0" maxlength="20" disabled>
				</div>
				<div class="modal-footer">
					<button id="savefood-cancel-btn" class="btn btn-secondary btn-hidden" type="button" data-dismiss="modal">취소</button>
					<button id="savefood-update-btn" type="button" class="btn btn-success btn-update" onclick="savefoodUpdate()">수정</button>
					<button id="savefood-delete-btn" type="button" class="btn btn-primary" onclick="savefoodDelete()">삭제</button>
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
		
		function savefoodDelete(){
			var savefoodSeq = $("#savefoodModal").get(0).dataset.savefoodSeq;

			if(!savefoodSeq){
				alert("잘못된 접근입니다.");
			}

			var dataParam = {
					"savefoodSeq":savefoodSeq
			}

			if(confirm("삭제하시겠습니까?")){
				 $.ajax({
					url:'/api/food/savefood/delete',
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
		
		function changeDeleteMode(isCancel){
			if(!isCancel){
				if($('#batch-delete-btn').is('.is-delete-mode')){

					var checkedSaveFood = $('.delete-savefood:checked');

					if(checkedSaveFood.length == 0){
						alert("선택된 식품이 없습니다.");
						return;
					}

					var dataParam = new Array();

					for(var i = 0; i < checkedSaveFood.length; i++){
						var savefoodSeq = {'savefoodSeq':checkedSaveFood.get(i).value};
						dataParam.push(savefoodSeq);
					}

					if(confirm("삭제하시겠습니까?")){
						 $.ajax({
							url:'/api/food/savefood/batchDelete',
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
				else{
					$(".food").addClass('delete-mode');
					$("#cancel-delete-btn").removeClass('btn-hidden');
					$("#batch-delete-btn").addClass('is-delete-mode');
					$("#batch-delete-btn").text('삭제');
					$(".delete-savefood").removeAttr('disabled');	
				}
			}
			else if(isCancel){
				$(".food").removeClass('delete-mode');
				$("#cancel-delete-btn").addClass('btn-hidden');
				$("#batch-delete-btn").removeClass('is-delete-mode');
				$("#batch-delete-btn").text('선택삭제');
				$(".delete-savefood").removeAttr('checked');
				$(".delete-savefood").attr('disabled', 'true');
			}
		}
		
		// 보관장소 등록 팝업
	 	function showModal() {
			$("#saveplaceModal").modal();
		} 

	 	$("#input-fridge-list").on("change", function(e){
			var fridgeSeq = $(e.target).val();
			saveplaceListFilter(fridgeSeq);
			$("#input-saveplace-list").change();
		})
		
		function saveplaceListFilter(fridgeSeq){
	 		var count = 0;
			$("#input-saveplace-list").children("option").each(function(i, saveplace){
				if(saveplace.dataset.fridgeSeq == fridgeSeq){
						saveplace.className = null;
						if(count == 0){
							saveplace.selected = true;
							}
						count++;
				}
				else{
						saveplace.className = "unselected-fridge";
				}
			})
	 	}
	
		$("#input-saveplace-list").on("change", function(e){
			var savefoodSeq = $("#savefoodModal").get(0).dataset.savefoodSeq;
			var saveplaceSeq = $("#input-saveplace-list").val();
			var storageCode = $("#input-saveplace-list").children("option[value='"+saveplaceSeq+"']").get(0).dataset.storageCode;
			changeExpiDate(savefoodSeq, storageCode);
		});

		function changeExpiDate(savefoodSeq, storageCode){
			if(!savefoodSeq){
				alert("잘못된 접근입니다.");
			}

			var dataParam = {
					"savefoodSeq":savefoodSeq,
					"storageCode":storageCode
			}

			 $.ajax({
					url:'/api/food/savefood/expidate',
					type:'post',
					dataType:'text',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					 success:function(data,textStatus,jqXHR){
			        	 if(jqXHR.status == "204"){
			        		 	alert(data.responseText);
				            }
			            else{
								$("#input-expi-date").val(data);
				            }
					},error:function(data){
						alert(data.responseText);
					}
			});
			
		}

		$(".food").on("click", function(e){
			var target = $(e.target).is(".food") ? $(e.target) : $(e.target).closest(".food");

			if($('#batch-delete-btn').is('.is-delete-mode')){
				if(target.children('.delete-savefood').is(':checked')){
					target.removeClass('delete-mode');
				}
				else{
					target.addClass('delete-mode');
				}
				return;
			}
			
			var savefoodSeq = target.children("input[type='checkbox']").val();
			$("#savefoodModal").get(0).dataset.savefoodSeq = savefoodSeq;
			savefoodModalInit(savefoodSeq);
			$("#savefoodModal").modal();	
		})
		
		function savefoodModalInit(savefoodSeq){
			$("#input-food-name").attr("disabled", "true");
			$("#input-fridge-list").attr("disabled", "true");
			$("#input-saveplace-list").attr("disabled", "true");
			$("#input-expi-date").attr("disabled", "true");
			$("#input-food-quantity").attr("disabled", "true");
			
			if(!savefoodSeq) {
				alert("잘못된 접근입니다.");
			}
			
			var dataParam = {
					"savefoodSeq":savefoodSeq
			}
			
			 $.ajax({
					url:'/api/food/savefood',
					type:'post',
					dataType:'json',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					 success:function(data,textStatus,jqXHR){
			        	 if(jqXHR.status == "204"){
			        		 	alert(data.responseText);
				            }
			            else{
			            		$("#input-food-name").val(data.savefood_name);
			            		$("#input-food-name").attr("placeholder", data.food_name);
			            		$("#input-food-category").val(data.category_name + "  >  " + data.food_name);
			            		$("#input-fridge-list").val(data.fridge_seq);
			            		saveplaceListFilter(data.fridge_seq);
			            		$("#input-saveplace-list").val(data.saveplace_seq);
			            		$("#input-expi-date").val(data.savefood_expi_date);
			            		$("#input-food-quantity").val(data.savefood_quantity);
			            		$("#input-food-name").get(0).dataset.orgVal = data.savefood_name;
			            		$("#input-fridge-list").get(0).dataset.orgVal = data.fridge_seq;
			            		$("#input-saveplace-list").get(0).dataset.orgVal = data.saveplace_seq;
			            		$("#input-expi-date").get(0).dataset.orgVal = data.savefood_expi_date;
			            		$("#input-food-quantity").get(0).dataset.orgVal = data.savefood_quantity;
			            		$("#savefood-cancel-btn").addClass("btn-hidden");
			            		$("#savefood-update-btn").addClass("btn-update");
			            		$("#savefood-update-btn").text("수정");
				            }
					},error:function(data){
						alert(data.responseText);
					}
				 });
			};

		function savefoodUpdate(){
			if($("#savefood-update-btn").is(".btn-update")){
				$("#savefood-cancel-btn").removeClass("btn-hidden");
				$("#savefood-update-btn").removeClass("btn-update");
				$("#savefood-update-btn").text("저장");
				$("#input-food-name").removeAttr("disabled");
				$("#input-fridge-list").removeAttr("disabled");
				$("#input-saveplace-list").removeAttr("disabled");
				$("#input-expi-date").removeAttr("disabled");
				$("#input-food-quantity").removeAttr("disabled");
			}
			else{
				if(
				$("#input-food-name").get(0).dataset.orgVal != $("#input-food-name").val() ||
				$("#input-fridge-list").get(0).dataset.orgVal !=  $("#input-fridge-list").val() ||
        		$("#input-saveplace-list").get(0).dataset.orgVal != $("#input-saveplace-list").val() ||
        		$("#input-expi-date").get(0).dataset.orgVal != $("#input-expi-date").val() ||
        		$("#input-food-quantity").get(0).dataset.orgVal != $("#input-food-quantity").val()
        		) {
					var savefoodSeq = $("#savefoodModal").get(0).dataset.savefoodSeq;
					var savefoodName = $("#input-food-name").val();
					var saveplaceSeq = $("#input-saveplace-list").val();
					var foodExpiDate = $("#input-expi-date").val();
					var foodQuantity = $("#input-food-quantity").val();

					if(!savefoodSeq) {
						alert("잘못된 접근입니다.");
						return;
					}
					if(!savefoodName) {
						savefoodName = $("#input-food-name").attr("placeholder");
					}
					if(!saveplaceSeq) {
						alert("저장장소를 선택해주세요.");
						$("#input-saveplace-list").focus();
						return;
					}
					if(!foodExpiDate) {
						alert("유통기한을 입력해주세요.");
						$("#input-expi-date").focus();
						return;
					}
	        		
					var dataParam = {
							"savefoodSeq":savefoodSeq,
							"savefoodName":savefoodName,
							"saveplaceSeq":saveplaceSeq,
							"savefoodExpiDate":foodExpiDate,
							"savefoodQuantity":foodQuantity
					}

					 $.ajax({
							url:'/api/food/savefood/update',
							type:'post',
							dataType:'text',
							contentType:'application/json',
							data:JSON.stringify(dataParam),
							 success:function(data,textStatus,jqXHR){
					        	 if(jqXHR.status == "204"){
					        		 	alert(data.responseText);
						            }
					            else{
										location.reload();
						            }
							},error:function(data){
								alert(data.responseText);
							}
					});
        		}
			}
		}
		
		// 보관장소 등록 처리
		function regSaveplace() {
			var saveplace = $("#inputSaveplace").val();
			var storage = $("#comboStorage").val();	
			var fridgeSeq = "${fridgeSeq}";

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

			var inputSaveplaceName = $("#saveplaceName"+ seq);
			var editIcon = $("#saveplaceEdit"+ seq);
			var editClassName = editIcon.attr("class");

			if(editClassName == "far fa-edit"){
	 			inputSaveplaceName.removeAttr("disabled");
				inputSaveplaceName.attr("style", "border:1px;"); 

				editIcon.attr("class", "fas fa-edit");
				
			} else {
				 $.ajax({
					url:'/api/fridge/saveplace/update',
					type:'post',
					dataType:'json',
					contentType:'application/json',
					data:JSON.stringify(dataParam),
					success:function(data){
						alert("수정이 완료되었습니다.");
					},error:function(data){
						alert(data.responseText);
					}
				}); 

				inputSaveplaceName.attr("style", "border:0px; background-color: transparent;"); 
	 			inputSaveplaceName.attr("disabled", "disabled");

 				editIcon.attr("class", "far fa-edit");
 				//fa-check-circle
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
