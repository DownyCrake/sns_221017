package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId
			, HttpSession session
			){
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> like = new HashMap<>();
		// 좋아요 있으면 삭제 없으면 추가
		
		likeBO.existLikeByUserIdAndPostId(userId ,postId); 
		
		return like; 
	}
}
