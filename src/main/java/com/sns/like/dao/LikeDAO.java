package com.sns.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeDAO {

	public int selectLikeCountByPostId(int postId);
	
	public int insertLikeByUserIdAndPostId(
			@Param("userId") int userId
			, @Param("postId") int postId);

	public int deleteLikeByUserIdAndPostId(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	public boolean existLikeByUserIdAndPostId(
			@Param("userId") Integer userId
			, @Param("postId") int postId);

	public void deleteLikeByPostId(int postId);
	
	public List<Like>selectLikePost(int userId);
}
