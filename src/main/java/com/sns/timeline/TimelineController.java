package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	@RequestMapping("/timeline_view")
	public String timelineView(Model model
			,HttpSession session) {
		
		//List<Post> postList = postBO.getPostList();
		//model.addAttribute("postList", postList);
		
		List<CardView> cardViewList = timelineBO.generateCardList();
		model.addAttribute("cardViewList", cardViewList);
		
		
		model.addAttribute("viewName","timeline/timeline");
		return "template/layout";
	}
	
}
