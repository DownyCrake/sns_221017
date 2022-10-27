package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 추가
	public int addLikeByUserIdAndPostId(int userId, int postId) {
		return likeDAO.insertLikeByUserIdAndPostId(userId, postId);
	}
	
	//좋아요 삭제
	public int deleteLikeByUserIdAndPostId(int userId, int postId) {
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
}
