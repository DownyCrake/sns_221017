<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="timeline-wrap">
	<div id="timelineDiv" class="mt-5 mb-5">
	
	<div class="d-flex justify-content-center">

		<c:if test="${not empty userId}">
		<div id="postInputDiv">
			<textarea class="w-100" id="content" name="content" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				
				<div class="d-flex">
					<input type="file" id="file" class="d-none" accept="/gjf, .jpg, .png, .jpeg" >
					
					<a href="#" id="fileUpLoadBtn">
					<img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png" >
					</a>
					
					<%-- 업로드된 임시 파일 이름 저장 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
				
				<input type="button" id="postCreateBtn" class="btn btn-info text-white" value="게시">
			</div>		
		</div>
		</c:if>
		
	</div>
		
		<c:forEach items="${cardViewList}" var="cardView" varStatus="postListStatus">
		
		<div id="postViewAndComment" class="mt-5">
			<div class="d-flex justify-content-between p-2">
				<div class="font-weight-bold">${cardView.user.name }</div>
				
				<c:if test="${cardView.post.userId eq userId}">
					<button type="button" class="hidden-btn more-btn" data-toggle="modal" data-target="#modal" data-post-id="${cardView.post.id}"> 
						<img src="/static/img/more-icon.png" height="20px" alt="게시글 옵션 더보기">
					</button>
				</c:if>
				
				
			</div>
			<div id="postImgDiv" class="bg-dark">
				<img src="${cardView.post.imagePath}" width="600px" alt="게시한 이미지" > 
			</div>
			<div class="p-2">
		
			<!-- 좋아요 버튼 표시 -->
				
				<c:choose>
					<c:when test="${cardView.filledLike eq true }">
						<button type="button" class="hidden-btn like-btn" data-post-id="${cardView.post.id}" data-user-id="${userId}">
							<img src="/static/img/heart-icon2.png" height="18px" alt="좋아요 삭제">					
						</button>
					</c:when>
					
					<c:otherwise>
					<button type="button" class="hidden-btn like-btn" data-post-id="${cardView.post.id}" data-user-id="${userId}">
						<img src="/static/img/heart-icon1.png" height="18px" alt="좋아요 추가">					
					</button>
					</c:otherwise>
				</c:choose>
				
			
				좋아요 ${cardView.likeCount}
			</div>
			<div id="postDiv" class="mt-3 mb-3">
				<b>${cardView.user.name }</b>  ${cardView.post.content}
			</div>
			<div class="p-2">
				<b>댓글</b>
			</div>
			<div id="commentList" class="border-top">
				<c:forEach items="${cardView.commentViewList}" var="commentView">
					<div class="d-flex">
						<div class="m-2">
							<span class="font-weight-bold">${commentView.user.name}</span> : ${commentView.comment.content}
						</div>
						<div class="mt-1">
							<c:if test="${userId eq commentView.user.id }">
							<button type="button" class="delteCommentBtn hidden-btn" data-comment-id="${commentView.comment.id}">
							<img src="/static/img/x-icon.png" width="10px" alt="삭제버튼"></button>
							</c:if>
						</div>
					</div>	
				</c:forEach>
			</div>
			<c:if test="${not empty userId}">
			<div class="d-flex height-40px border-top" >
				<input type="text" class="w-100" id="comment" placeholder="댓글달기">
				<button type="button" class="comment-btn" id="inputCommentBtn" data-post-id="${cardView.post.id}">게시</button>	
			</div>
			</c:if>
		</div>
		
		</c:forEach>
	
	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="modal">
<%-- modal-dialog-centered  모달창을 수직으로 가운데 정렬 --%>
  <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
    <div class="modal-content text-center">
      <%-- 모달 창 안에 내용 넣기 --%>
      <div class="p-3 border-bottom">
	      <button type="button" class="btn btn-danger" id="delPostBtn">삭제</button>
      </div>
      <div class="p-3">
	      <%-- data-dismiss="modal" 모달 창 닫힘 --%>
	      <button type="button" class="btn" data-dismiss="modal">취소</button>  
      </div>
    </div>
  </div>
</div>




<script>
$(document).ready(function(){
	// 파일 업로드 이미지 (a)를 클릭 => 파일 선택창이 떠야함
	$('#fileUpLoadBtn').on('click', function(e){
		e.preventDefault();   // a태그의 기본 동작 멈춤 (화면이 위로 올라가는것 방지)
		$('#file').click(); //inputfile을 클릭한 것과 같은 효과				
	});//파일 업로드 버튼 클릭
	
	//사용자가 파일 업로드 했을때
	$('#file').on('change', function(e) {
		//alert("체인지");
		
		let fileName = e.target.files[0].name;
		
		let ext = fileName.split('.').pop().toLowerCase();
		
		//확장자 유효성 확인
		if (fileName.split('.').length < 2 || 
				(ext != 'gif'
					&& ext != 'png'
						&& ext != 'jpeg'
							&& ext != 'jpg')  ) {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$(this).val('');
			$('#fileName').text('');
			return;
		}
		//상자에 업로드된 이름 노출
		$('#fileName').text(fileName);
		
	});// 파일 업로드
	
	$('#postCreateBtn').on('click', function() {
		
		let content = $('#content').val().trim();
		if (content =='') {
			alert("글 내용을 입력하세요");
			return;
		}
		if ( $('#file')[0].files[0] == null  ){
			alert("그림을 선택해주세요");
			return;
		}
			
		let formData = new FormData();
		formData.append('content', content);
		formData.append('file', $('#file')[0].files[0]);
		
		$.ajax({
			type:"post"
			, url:"/post/create"
			, data: formData
			, enctype:"multipart/form-data"  // 파일 업로드를 위한 필수 설정
			, processData:false  // 파일 업로드를 위한 필수 설정
			, contentType:false  // 파일 업로드를 위한 필수 설정			
				
			, success:function(data){
				if (data.code == 100) {
					alert("글이 저장되었습니다");
					location.href = "/timeline/timeline_view";
				} else if (data.code ==300) {
					alert(data.errorMessage);	
					location.href = "/timeline/timeline_view";
				}else {
					alert(data.errorMessage);	
					location.href = "/user/sign_in_view";
				}
			}
			, error:function(e){
				alert("저장에 실패했습니다");
			}
			
		});//ajax
	});// 글쓰기 게시 클릭
	
	//댓글 게시버튼 클릭
	$('.comment-btn').on('click', function() {
		// alert("댓글");
		let postId = $(this).data('post-id');
		// alert(postId);
		//지금 클릭된 게시버튼의 형제인 input 태그를 가져온다 (siblings)
		let comment = $(this).siblings('input').val().trim();
		//alert(postId + comment);
		
		$.ajax({
			type:"post"
			, url:"/comment/create"
			, data: {"postId":postId, "comment":comment}

			,success:function(data){
				if (data.code == 100){
					alert("댓글이 저장되었습니다");
					document.location.reload(true);
				} else if (data.code == 300) {
					alert(data.errorMessage);
				} else {
					alert(data.errorMessage);
				}
			}
			
			, error:function(e){
				alert("저장에 실패했습니다");
			}
			
		});//ajax
	}); //댓글 게시 클릭
	
	$('.like-btn').on('click', function() {
		let userId = $(this).data('user-id');
		if (userId =='') {
			alert("로그인해주세요");
			return;
		}
		
		let postId = $(this).data('post-id');
		$.ajax({
			type:"get"
			, url:"/like/"+postId
			
			,success:function(data){
				if (data.code == 100){
					document.location.reload(true);
				} else {
					alert("유저정보 없음");
					document.location.reload(true);
				}
			}
			
			, error:function(e){
				alert("저장에 실패했습니다");
			}
		});
	}); //좋아요 버튼 클릭
	
	$('.delteCommentBtn').on('click', function() {
		let commentId = $(this).data('comment-id');
		// alert(commentId);
		
		$.ajax({
			type:"delete"
			, url:"/comment/delete"
			, data:{"commentId":commentId}
			
			,success:function(data){
				if (data.code == 100) {
					document.location.reload(true);
				} else{
					alert(data.errorMessage);
					document.location.reload(true);
				}
			}
			, error:function(e){
				alert("에러");
			}
			
		}); // ajax
		
	}); //삭제 버튼 클릭
	
	//더보기 (...) 버튼 클릭 -> 글번호를 modal에 넣는다
	$('.more-btn').on('click',function(e) {
		e.preventDefault();
		let postId = $(this).data("post-id"); //get
		// alert(postId);
		
		$('#modal').data('post-id',postId); //set   data-post-id = "4"
	}); // 더보기버튼 클릭
	
	
	//모달창 안에 있는 (글)삭제하기 버튼 클릭
	$('#modal #delPostBtn').on('click', function(e) {
		e.preventDefault();
		let postId =$('#modal').data('post-id');
		alert(postId);
		
		// ajax 글삭제
		$.ajax({
			
		}); //ajax
		
	}); //모달창
	
	
});//ready


</script>