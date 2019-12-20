<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	<!-- Custom styles for this template-->
	<link href="../../static/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">회원가입</div>
			<div class="card-body">
				<form>
					<div class="form-group">
						<div class="form-label-group">
							<input type="email" id="inputEmail" class="form-control" placeholder="아이디(이메일)" required="required">
							<label for="inputEmail">아이디(이메일)</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" required="required">
									<label for="inputPassword">비밀번호</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="password" id="confirmPassword" class="form-control" placeholder="비밀번호 확인" required="required">
									<label for="confirmPassword">비밀번호 확인</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="text" id="authKey" class="form-control" placeholder="인증키" required="required" autofocus="autofocus">
									<label for="authKey">인증키</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-label-group">
									<a class="btn btn-primary btn-block" href="javascript:getAuthKey();" style="line-height:2.3">인증번호 생성</a>
								</div>
							</div>
							
						</div>
					</div>
					
					<a class="btn btn-primary btn-block" href="javascript:auth();">회원가입</a>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="login">로그인 페이지 이동</a>
					<a class="d-block small" href="#">비밀번호 찾기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../../static/vendor/jquery/jquery.min.js"></script>
	<script src="../../static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../../static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script>
		function getAuthKey() {
			var memberId = $("#inputEmail").val();
			if(!memberId) {
				alert("아이디를 입력해주세요.");
				$("#inputEmail").focus();
			}
			
			var dataParam = {
				"memberId":$("#inputEmail").val()
			}
			
			$.ajax({
	            url:'/api/member/authkey',
	            type:'post',
	            dataType:'text',
	            contentType: 'application/json',
	            data:JSON.stringify(dataParam),
	            success:function(data){
	            	$("#authKey").val(data)
	            },error:function(data){
	            	alert(data.responseText);
	            }
	        });
		}
		
		function join() {
			var dataParam = {
				"memberId":$("#inputEmail").val(),
				"password":$("#inputPassword").val()
			}
			
			var memberId = $("#inputEmail").val();
			if(!memberId) {
				alert("아이디를 입력해주세요.");
				$("#inputEmail").focus();
			}
			
			var password = $("#inputPassword").val();
			if(!password) {
				alert("비밀번호를 입력해주세요.");
				$("#inputPassword").focus();
			}
			
			$.ajax({
	            url:'/api/member/users',
	            type:'post',
	            dataType:'text',
	            contentType: 'application/json',
	            data:JSON.stringify(dataParam),
	            success:function(data){
	            	alert("회원가입 되었습니다. 로그인으로 이동합니다.");
	            	location.href = "/view/member/login";
	            },error:function(data){
	            	alert(data.responseText);
	            }
	        });
		}
		
		function auth() {
			var dataParam = {
				"memberId":$("#inputEmail").val(),
				"authKey":$("#authKey").val()
			}
			
			var memberId = $("#inputEmail").val();
			if(!memberId) {
				alert("아이디를 입력해주세요.");
				$("#inputEmail").focus();
			}
			
			var authKey = $("#authKey").val();
			if(!authKey) {
				alert("인증번호를 입력해주세요.");
				$("#authKey").focus();
			}
			
			$.ajax({
	            url:'/api/member/auth',
	            type:'post',
	            dataType:'text',
	            contentType: 'application/json',
	            data:JSON.stringify(dataParam),
	            success:function(data){
	            	join();
	            },error:function(data){
	            	alert(data.responseText);
	            }
	        });
		}
		
	</script>
</body>

</html>
