<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div id="signUpDiv" class="d-flex justify-content-center align-items-center">

	<form id="signUpForm" method="post" class="sign-up-box" action="/user/sign_up">
			<div class="form-group d-flex m-0">
				<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디 입력">
				<button type="button" id="loginCheckBtn" class="btn btn-success btn-sm ml-1">중복확인</button>
			</div>	
				<small id="idCheckLength" class="text-danger d-none isDuplicationText">아이디가 4글자 이하입니다.</small>
				<small id="idCheckDuplicated" class="text-danger d-none isDuplicationText">중복된 아이디입니다.</small>
				<small id="idCheckOk"class="text-secondary d-none isDuplicationText">사용가능한 아이디입니다.</small>
				<small class="text-danger">&nbsp</small>
			<div class="form-group m-0">
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호 입력">
			</div>	
			<small class="text-danger">&nbsp</small>
			
			<div class="form-group m-0">
				<input type="password" id="passwordCheck" name="passwordCheck" class="form-control" placeholder="비밀번호 확인">
			</div>	
			<small class="text-danger">&nbsp</small>
			<div class="form-group m-0">
				<input type="text" id="name" name="name" class="form-control" placeholder="이름 입력">
			</div>	
			<small class="text-danger">&nbsp</small>
			<div class="form-group m-0">
				<input type="text" id="email" name="email" class="form-control" placeholder="이메일 주소">
			</div>	
			<small class="text-danger">&nbsp</small>
			<button type="submit" id="signUpBtn" class="btn btn-primary  btn-block text-white">회원가입</button>
	</form>
</div>

<script>
$(document).ready(function(){
	
	$('#loginCheckBtn').on('click', function(){
		let loginId = $('#loginId').val().trim();
		//alert(loginId);
		if (loginId.length < 4) {
			$('#idCheckLength').removeClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			return;
		}	
		
		
	}); // 중복확인버튼 클릭
	
	
	
});//ready
</script>
