package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	
	public int addComment(int postId, int userId, String content) {
		
		return commentDAO.insertComment(postId, userId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	//input 글번호
	// output 가공된 댓글 객체 (화면용)
	public List<CommentView> generateCommentViewListByPostId(int postId){
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		List<Comment> commentList = commentDAO.selectCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			User user = userBO.getUserById(comment.getUserId());
			
			commentView.setComment(comment);
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}
	
}
