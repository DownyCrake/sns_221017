<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="timeline-wrap">
	<div id="timelineDiv" class="mt-5 mb-5">
	
	<div class="d-flex justify-content-center">

		<form id="postInputForm">
			<textarea class="w-100" id="post" name="post" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				<button type="button" name="postImageBtn" class="btn">이미지 업로드</button>				
				<input type="button" id="postBtn" class="btn btn-info text-white" value="게시">
			</div>		
		</form>
	</div>
		
		<div id="postViewAndComment" class="mt-5">
			<div class="d-flex justify-content-between p-2">
				<div class="font-weight-bold">${userName}</div>
				<button type="button" class="hidden-btn"> 
					<img src="/static/img/more-icon.png" height="20px" alt="게시글 삭제">
				</button>
			</div>
			<div id="postImgDiv" class="bg-dark">
				
			</div>
			<div class="p-2">
				<button type="button" class="hidden-btn">
					<img src="/static/img/heart-icon1.png" height="18px" alt="좋아요">					
				</button>
				좋아요
			</div>
			<div id="postDiv" class="mt-3 mb-3">
				ㅇㅇㅇ
			</div>
			<div class="p-2">
				<b>댓글</b>
			</div>
			<hr class="m-0">
			<div id="commentList">
				
			</div>
			<hr class="m-0">
			<div class="d-flex height-40px" >
				<input type="text" class="w-100" id="comment" name="" placeholder="댓글달기">
				<button type="button" class="" id="inputCommentBtn">게시</button>	
			</div>
		</div>
	
	
	</div>
</div>