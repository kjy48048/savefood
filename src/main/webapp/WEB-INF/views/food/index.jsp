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
										<c:set var="fridgeSeq" value="${fridge.fridge_seq}"	/>							
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
									<c:when test="${fridgeSeq == saveplace.fridge_seq && status.count == 1}">
										<option data-fridge-seq="${saveplace.fridge_seq}" value="${saveplace.saveplace_seq}" selected>${saveplace.saveplace_name}</option>									
									</c:when>
									<c:when test="${fridgeSeq == saveplace.fridge_seq}">
										<option data-fridge-seq="${saveplace.fridge_seq}" value="${saveplace.saveplace_seq}">${saveplace.saveplace_name}</option>									
									</c:when>
									<c:otherwise>
										<option class="unselected-fridge" data-fridge-seq="${saveplace.fridge_seq}" value="${saveplace.saveplace_seq}">${saveplace.saveplace_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<div id="added-food" class="added-food-none">
							<div>
								<span id="added-result">
								</span>
							</div>
						</div>
					</div>
					<div class="search-utility utility">
						<input class="utility-item" type="text" id="search-text" placeholder="검색"/>
						<input type="button" id="search-btn" class="utility-item"/>
					</div>
				</div>
			
				<div class="category-container">
					<div class="item-wrapper">
						<a class="category-item" href="#" data-category-seq="0" style="color:orange;">전체</a>
					</div>
					<c:forEach items="${categoryList}" varStatus="status" var="category">
						<div class="item-wrapper">
							<a class="category-item" href="#" data-category-seq="${category.category_seq}">${category.category_name}</a>
						</div>
					</c:forEach>
				</div>
				
				<div class="food-container" >
					<c:forEach items="${foodListList}" var="foodList" varStatus="status">
						<div class="food-list">
							<h5>${foodList[0].category_name }</h5>
							<div class="food-wrapper">
								<c:forEach items="${foodList}" var="food">
									<div class="food" data-food-seq="${food.food_seq}">
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
		$(document).ready(function(){
			result = [<c:forEach var="saveplace" items="${saveplaceList}" varStatus="status">
				{	
					saveplaceSeq:${saveplace.saveplace_seq},
					addedFood:0
				}
				<c:if test="${!status.last}">,</c:if>
			</c:forEach>];	
		});
	
		$(".category-item").on("click", function(e){
				e.preventDefault();
				var categorySeq = $(e.target).get(0).dataset.categorySeq;
				selectCategory(categorySeq);
				$(".category-item").removeAttr("style");
				$(e.target).attr("style","color:orange;");
			});
		
		function selectCategory(categorySeq) {
				var dataParam = {
						"categorySeq":categorySeq
						};
				
				$.ajax({
			        url:'/api/food/list',
			        type:'post',
			        dataType:'json',
			        contentType: 'application/json',
			        data:JSON.stringify(dataParam),
			        success:function(data,textStatus,jqXHR){
			        	 if(jqXHR.status == "204"){
			        		 	$(".food-container *").remove();
			        		 	$(".food-container").append('<div class="no-content"><span>검색된 정보가 없습니다.</span></div>');
				            }
			            else{
			            		appendFoodList(data);
				            }
			        },error:function(data){
			        	alert(data.responseText);
			        }
			    });
			}
		
		$("#search-text").on("keydown", function(e){
			if(e.keyCode == 13){
				var searchText = $(e.target).val();
				searchFood(searchText);
				$(".category-item").removeAttr("style");
			}
		});
		
		$("#search-btn").on("click", function(e){
			e.preventDefault();
			var searchText = $("#search-text").val();
			searchFood(searchText);
			$(".category-item").removeAttr("style");
		});
		
		
		function searchFood(searchText) {
			var dataParam = {
					"searchText":searchText
					};
			
			$.ajax({
		        url:'/api/food/search',
		        type:'post',
		        dataType:'json',
		        contentType: 'application/json',
		        data:JSON.stringify(dataParam),
		        success:function(data,textStatus,jqXHR){
		        	 if(jqXHR.status == "204"){
		        		 	$(".food-container *").remove();
		        		 	$(".food-container").append('<div class="no-content"><span>검색된 정보가 없습니다.</span></div>');
			            }
		            else{
			            	appendFoodList(data);
			            }
		        },error:function(data){
		        	alert(data.responseText);
		        }
		    });
		}

		$("#fridge-list").on("change", function(e){
				var fridgeSeq = $(e.target).val();
				var count = 0;
					$("#saveplace-list").children("option").each(function(i, saveplace){
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
			
			})
			
		$("#saveplace-list").on("change", function(e){
			var saveplaceSeq = $(e.target).val();
			showAddedFood(saveplaceSeq);
		});
			
		$(".food-container").on("click", ".food", function(e){
			if($("#check-auto-reg").get(0).checked){
				var saveplaceSeq = $("#saveplace-list").val();
				var div = e.target.nodeName != 'DIV' ? e.target.parentElement : e.target;
				var foodSeq = div.dataset.foodSeq;

				if(!saveplaceSeq){
						alert("저장장소를 선택해주세요.");
					};

				if(!foodSeq){
						alert("잘못된 선택입니다.");
					};

				var dataParam = {
						"saveplaceSeq":saveplaceSeq,
						"foodSeq":foodSeq
						};

				$.ajax({
			        url:'/api/food/auto/reg',
			        type:'post',
			        dataType:'text',
			        contentType: 'application/json',
			        data:JSON.stringify(dataParam),
			        success:function(data,textStatus,jqXHR){
			        	 if(jqXHR.status == "204"){
			        		 	
				            }
			            else{ 
				            	var orgTop = $(div).children('img').offset().top;
				            	var orgLeft = $(div).children('img').offset().left;
				            	var moveTop = $("#saveplace-list").offset().top;
				            	var moveLeft = $("#saveplace-list").offset().left;
				            	var clone = div.firstElementChild.cloneNode();
				            	$(clone).css({
				            		width: "50px",
				            		height: "50px",
				            		position: "absolute"
					            	});

				            	$(clone).offset({top:orgTop, left:orgLeft});

				            	$('body').get(0).appendChild(clone);
								$(clone).animate({top:moveTop, left:moveLeft}, 300, function(){
										$(clone).remove();
									});
								result.forEach(function(item){
										if(item.saveplaceSeq == saveplaceSeq){
												item.addedFood++;
											}
									})
								showAddedFood(saveplaceSeq);
 				            }
			        },error:function(data){
			        	alert(data.responseText);
			        }
			    });
			}
		});

		function showAddedFood(saveplaceSeq){
				result.forEach(function(item){
					if(item.saveplaceSeq == saveplaceSeq){
						if(item.addedFood > 0){
								$("#added-result").get(0).innerHTML = item.addedFood;
								$("#added-food").attr("class", "added-food-block");
							}
						else{
								$("#added-food").attr("class", "added-food-none");
							}
						}
					});
			}
		
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
		
		function appendFoodList(data){
				$(".food-container *").remove();
	    		var textHtml = "";
				$.each(data, function(i, foodList){
					textHtml += '<div class="food-list">';
					textHtml += '<h5>'+ foodList[0].category_name + '</h5>';
					textHtml += '<div class="food-wrapper">';
					$.each(foodList, function(j, food){
						textHtml += '<div class="food" data-food-seq="'+ food.food_seq +'">';
						textHtml +=	'<img src="${pageContext.request.contextPath}'+food.food_img+'" onerror="this.src=\'${pageContext.request.contextPath}/resources/img/not-found.png\'"/>'; 
						textHtml +=	'<span>'+food.food_name+'</span>';
						textHtml +=	'</div>'
					})
					textHtml+= '</div>';
					textHtml+= '</div>';
				})
				$(".food-container").append(textHtml);
			}
	 	function showModal() {
			$("#saveplaceModal").modal();
		} 
	</script>
	
</body>



</html>