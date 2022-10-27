package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO; 
	
	@RequestMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("comment") String comment
			, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
			
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 300);
			result.put("result","false");
			result.put("errorMessage", "업로드에 실패했습니다");
			return result;
		}
		
		int row = commentBO.addComment(postId, userId, comment);
		if (row > 0) {
			result.put("code", 100);
			result.put("result","success");
		} else {
			result.put("code", 600);
			result.put("errorMessage", "업로드에 실패했습니다. 관리자에게 문의해주세요");
		}
		
		return result;
	}
}
