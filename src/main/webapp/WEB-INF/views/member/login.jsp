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
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">로그인</div>
			<div class="card-body">
				<form>
					<div class="form-group">
						<div class="form-label-group">
							<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
							<label for="inputEmail">아이디(이메일)</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
							<label for="inputPassword">비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label>
								<input type="checkbox" value="remember-me">
								자동 로그인
							</label>
						</div>
					</div>
					<a class="btn btn-primary btn-block" href="javascript:login();">로그인</a>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="join">회원가입</a>
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
		function login() {
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
	            url:'/api/member/login',
	            type:'post',
	            dataType:'text',
	            contentType: 'application/json',
	            data:JSON.stringify(dataParam),
	            success:function(data){
	            	location.href = data;
	            },error:function(data){
	            	alert(data);
	            }
	        });
		}
	</script>
</body>

</html>
