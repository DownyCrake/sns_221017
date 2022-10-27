package com.sns.timeline;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {
	// 글1개 
	private Post post;

	// 댓글N개
	private List<CommentView> commentViewList;

	// 유저정보
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 좋아요 개수
	private int likeCount;
	
	//로그인된 사람이 좋아요를 눌렀는지
	private boolean filledLike;  // 눌렀으면 true  아니면 false


	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}

	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}






	
}
