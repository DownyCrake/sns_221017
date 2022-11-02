package com.sns.like.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;
import com.sns.like.model.Like;

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
	public boolean existLikeByUserIdAndPostId(Integer userId, int postId) {
		return likeDAO.existLikeByUserIdAndPostId(userId, postId);
	}
	
	// 좋아요 버튼 동작 
	public int togleLike(int userId, int postId) {
		if (existLikeByUserIdAndPostId(userId, postId)) { // 체크 true> 체크되어있음 > 삭제버튼
			return likeDAO.deleteLikeByUserIdAndPostId(userId, postId);  // 성공시1
		}
		return likeDAO.insertLikeByUserIdAndPostId(userId, postId);  // 성공시 1
	}
	
	// 글 삭제
	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostId(postId);
	}
	
	// 체크된 라이크 리스트 만들기
	public List<Integer> searchLikePost(int userId) {
		List<Integer> result = new ArrayList<>();
		result = likeDAO.selectLikePost(userId);
		return result; 
	}
}
