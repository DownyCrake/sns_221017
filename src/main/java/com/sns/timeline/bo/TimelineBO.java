package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public List<CardView> generateCardList(Integer userId) {  //로그인이 안된 사람도 카드 목록이 보여야 하기 때문에 Integer userId
		List<CardView> cardViewList = new ArrayList<>();
		
		//글 목록들을 가져온다
		List<Post> postList = postBO.getPostList();
		
		//반복문 > cardView에 넣음
		
		for (int i = 0; i < postList.size(); i++) {
		 
			CardView cardView = new CardView(); 
			cardView.setPost(postList.get(i));
			
			User user = userBO.getUserById(postList.get(i).getUserId());
			cardView.setUser(user);
			
			
			
			cardView.setCommentViewList(commentBO.generateCommentViewListByPostId( postList.get(i).getId() ));
			
			//(commentBO.generateCommentViewListByPostId( postList.get(i).getId() ) );
			
			
			// post 당 like 의 총개수
			cardView.setLikeCount(likeBO.getLikeCountByPostId(postList.get(i).getId() ));
			// 내가 좋아요를 눌렀는지
			cardView.setFilledLike(likeBO.existLikeByUserIdAndPostId(userId ,postList.get(i).getId()) );
				
			
			cardViewList.add(cardView);
		}
		
		
		
		//카드 채우기
		
		return cardViewList;
	}
}
