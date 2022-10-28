package com.sns.like.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 추가
	public int addLikeByUserIdAndPostId(Integer userId, int postId) {
		return likeDAO.insertLikeByUserIdAndPostId(userId, postId);
	}
	
	//좋아요 삭제
	public int deleteLikeByUserIdAndPostId(Integer userId, int postId) {
		return likeDAO.deleteLikeByUserIdAndPostId(userId, postId);
	}
	
	
	//좋아요 개수
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostId(postId);
	}
	
	//좋아요 여부 확인
	public boolean existLikeByUserIdAndPostId(int userId, int postId) {
		return likeDAO.existLikeByUserIdAndPostId(userId, postId);
	}
	
	// 좋아요 버튼 동작 
	public int togleLike(Integer userId, int postId) {
		if (existLikeByUserIdAndPostId(userId, postId)) { // 체크 true> 체크되어있음 > 삭제버튼
			likeDAO.deleteLikeByUserIdAndPostId(userId, postId);  // 성공시1
			return 0;
		}
			likeDAO.insertLikeByUserIdAndPostId(userId, postId);  // 성공시 1
			return 1; 
	}
}
