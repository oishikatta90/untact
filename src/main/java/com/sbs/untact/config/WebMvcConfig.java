package com.sbs.untact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// beforeActionInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	// needToAdmInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToAdmInterceptor")
	HandlerInterceptor needToAdmInterceptor;

	// needToLoginInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterceptor;

	// needToLogoutInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needToLogoutInterceptor")
	HandlerInterceptor needToLogoutInterceptor;

	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
		
		// 관리자 상태에서만 접속할 수 있는 URI 전부 기술
		registry.addInterceptor(needToAdmInterceptor)
		.addPathPatterns("/adm/**");

		// 로그인 없이도 접속할 수 있는 URI 전부 기술
		registry.addInterceptor(needToLoginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/adm/**")
				.excludePathPatterns("/resource/**")
				.excludePathPatterns("/usr/home/main")
				.excludePathPatterns("/usr/member/login")
				.excludePathPatterns("/usr/member/doLogin")
				.excludePathPatterns("/usr/member/join")
				.excludePathPatterns("/usr/member/doJoin")
				.excludePathPatterns("/usr/article/list")
				.excludePathPatterns("/usr/article/detail")
				.excludePathPatterns("/usr/reply/list")
				.excludePathPatterns("/usr/member/findLoginId")
				.excludePathPatterns("/usr/member/doFindLoginId")
				.excludePathPatterns("/usr/member/findLoginPw")
				.excludePathPatterns("/usr/member/doFindLoginPw")
				.excludePathPatterns("/usr/file/test*")
				.excludePathPatterns("/usr/file/doTest*")
				.excludePathPatterns("/test/**")
				.excludePathPatterns("/error");

		// 로그인 상태에서 접속할 수 없는 URI 전부 기술
		registry.addInterceptor(needToLogoutInterceptor)
				.addPathPatterns("/adm/member/login")
				.addPathPatterns("/adm/member/doLogin")
				.addPathPatterns("/usr/member/login")
				.addPathPatterns("/usr/member/doLogin")
				.addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/doJoin");
		
	}
}
