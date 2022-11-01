<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="d-flex justify-content-between align-items-center header-box">
	<%-- logo --%>
	<div>
		<a href="/timeline/timeline_view">
			<h1 class="font-weight-bold ml-5 text-white">SNS 게시판</h1>
		</a>
	</div>
	
	<div>
		<button type="button" id="likeSearchBtn" class="btn btn-warning" data-user-id="${userId}" >좋아요</button>
	</div>
	
	<%-- 로그인 정보 --%>
	<div class="mr-5">
		
		<c:if test="${not empty userName}">
		<span class="text-white">${userName}님 안녕하세요</span>
		<a href="/user/sign_out" class="ml-3 font-wieght-bold text-warning">로그아웃</a>
		</c:if>
		<c:if test="${empty userName}">
		<a href="/user/sign_in_view" class="ml-3 font-wieght-bold text-warning">로그인</a>
		</c:if>
	</div>
</div>   
    
<script>
$(document).ready(function() {
	$('#likeSearchBtn').on('click', function() {
		let userId = $(this).data('user-id');
		//alert(userId);
		
		$.ajax({
			type:"get"
			, url:"/timeline/timeline_view"
			, data:{"likeSearchId":userId}
		
			, success:function(data){
				if (data.code == 100) {
					document.location.reload(true);
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(){
				alert("에러");
			}
		}); //ajax
		
		
	});//좋아요 서치 버튼
	
}); //ready

</script>