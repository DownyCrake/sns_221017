package com.sns.timeline;

import java.util.List;

import com.sns.comment.model.Comment;
import com.sns.like.model.Like;
import com.sns.post.model.Post;

public class CardView {
	// 글1개 
	private Post post;

	// 댓글N개
	private List<Comment> commentList;

	// 좋아요
	private int likeCount;
	
	//로그인된 사람이 좋아요를 눌렀는지
	private boolean isLike;

	
	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}


	
}
