package com.sns.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;
import com.sns.interceptor.PermissionInterceptor;

@Configuration 
public class webMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor interceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")  // 웹주소   http://localhost/ ~~
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);  // 맥은 file:/  슬래시 1개  실제 파일 주소
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")	//   /post/post_list_view  /** 아래 디렉토리까지 확인
		.excludePathPatterns("/favicon.ico", "/error", "/user/sign_out", "/static/**");	
	}
}

