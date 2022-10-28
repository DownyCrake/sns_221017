package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public int selectLikeCountByPostId(int postId);
	
	public int insertLikeByUserIdAndPostId(
			@Param("userId") Integer userId
			, @Param("postId") int postId);

	public int deleteLikeByUserIdAndPostId(
			@Param("userId") Integer userId
			, @Param("postId") int postId);
	
	public boolean existLikeByUserIdAndPostId(
			@Param("userId") int userId
			, @Param("postId") int postId);
}
