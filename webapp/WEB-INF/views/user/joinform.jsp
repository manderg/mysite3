<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function(){
		$( "#join-form" ).submit(function(){
			//이름 체크
			if( $( "#name" ).val() == "" ) {
				alert( "이름이 비어 있습니다." );
				$( "#name" ).focus();
				return false;
			}
			
			//이메일 중복체크
			if( $( "#image-checked" ).is( ":visible" ) == false ) {
				alert( "이메일 중복 체크를 해야 합니다." );
				$( "#email" ).focus();
				return false;
			}
			
			//비밀번호
			if( $( "#password" ).val() == "" ) {
				alert( "비밀번호가 비어 있습니다." );
				$( "#password" ).focus();
				return false;
			}
			
			//약관동의
			if( $( "#agree-prov" ).is( ":checked" ) == false ) {
				alert( "약관 동의를 해 주세요." );
				return false;
			}
			
			return true;	
		});
		
		$("#email").change(function(){
			$( "#image-checked" ).hide();
			$( "#btn-checkemail" ).show();
		});
		
		$("#btn-checkemail").click(function(){
			console.log("clicked!!");
			var email = $("#email").val();
			if(email ==""){
				return;
			
			}
			console.log(email);
			
			$.ajax({
				url:"${pageContext.request.contextPath}/api/user/checkemail",
				type:"get",
				dataType:"json",
				data: "email=" + email,
				//contentType:"application/json",  // 위에있는 대이터를 제이슨으로 보내겟다는 것, 서버쪽에 데이터를 보낼때 타입이 제이슨 타입이다.
				success:function(response){
					console.log(response);
					if(response.result=="fail"){
						console.error(response.message);
						return;
					}
					if( response.data == false ) {
						alert( "사용중 인 이메일입니다. 다른 이메일을 사용해 주세요." );
						$("#email").val("");
						$("#email").focus();
						return;
					}
					$( "#image-checked" ).show();
					$( "#btn-checkemail" ).hide();
				},
				error:function(jqXHR, status, error){
					console.error(status + ":" + error);
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
					<input type="hidden" name="a" value="join">
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<img id="image-checked" src="${pageContext.request.contextPath}/assets/images/checked.png" style="width:12px; display:none">
					<input id="btn-checkemail" type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>