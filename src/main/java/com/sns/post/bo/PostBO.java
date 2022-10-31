package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManageService;
	
	public int addPost(String userLoginId, int userId, String content, MultipartFile file ) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = fileManageService.saveFile(userLoginId, file);
		}
		
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public void deletePost(int postId, int userId) {
		// 기존글 가져오기
		Post post = postDAO.selectPostByPostId(postId);
		if (post == null) {
			log.warn("[delete post] 삭제할 게시글이 없습니다. postId:{}",postId);
		}
		
		// 이미지가 있으면 이미지 삭제
		if (post.getImagePath() != null) {
			fileManageService.deleteFile(post.getImagePath());
		}
		
		// 글 삭제
		postDAO.deletePostByPostIdAndUserId(postId, userId);
		
		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// 댓글들 삭제
		commentBO.deleteCommentByPostId(postId);
		
	}
}
