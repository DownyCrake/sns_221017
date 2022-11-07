package com.sns.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.test.dao.TestDAO;

@Controller
public class testController {
	
	@Autowired
	private TestDAO testDAO;
	
	@RequestMapping("/test")
	public String test() {
		return "/test";
	}
	
	@ResponseBody
	@RequestMapping("/test/comment")
	public Map<String, Object> selectComment(){
		Map<String, Object> comment = new HashMap<>();
		comment = testDAO.selectComment();
		return comment ;
	}
	
	@RequestMapping("/test/main")
	public String testMain(){
		return "/testMain";
	}
}
