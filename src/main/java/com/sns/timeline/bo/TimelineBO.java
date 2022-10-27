package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.like.dao.LikeDAO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;
import com.sns.timeline.CardView;

@Service
public class TimelineBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
	public List<CardView> generateCardList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		//글 목록들을 가져온다
		List<Post> postList = postDAO.selectPostList();
		
		//반복문 > cardView에 넣음
		
		for (int i = 0; i < postList.size(); i++) {
		 
			CardView cardView = new CardView(); 
			cardView.setPost(postList.get(i));
			
			cardView.setCommentList(commentDAO.selectCommentList( postList.get(i).getId() ) );
			cardView.setLikeCount(likeDAO.selectLikeCountByPostId( postList.get(i).getId() ) );
			
			cardViewList.add(cardView);
		}
		return cardViewList;
	}
}
