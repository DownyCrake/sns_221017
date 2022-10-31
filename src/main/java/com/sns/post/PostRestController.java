package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private FileManagerService fileManageService;
	
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String Content
			, @RequestParam(value="file", required=false) MultipartFile file
			, HttpSession session
			){
		String userLoginId = (String)session.getAttribute("userLoginId");
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		String imagePath = fileManageService.saveFile(userLoginId, file);

		
		if (userId == null) {
			result.put("code", 300);
			result.put("result","false");

			result.put("errorMessage", "업로드에 실패했습니다");
			return result;
		}
		
		int row = postBO.addPost(userLoginId, userId, Content, file);
		
		if (row > 0) {
			result.put("code", 100);
			result.put("result","success");
		} else {
			result.put("errorMessage", "업로드에 실패했습니다. 관리자에게 문의해주세요");
		}
		
		return result; 
	}
	
	@DeleteMapping("/delete")
	public Map<String , Object> delete(
			@RequestParam("postId") int postId
			, HttpSession session
			){
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 300);
			result.put("errorMessage","로그인된 유저 정보가 없습니다");
			return result;
		}
		postBO.deletePost(postId, postId);
		
		
		return result;
	}
}
