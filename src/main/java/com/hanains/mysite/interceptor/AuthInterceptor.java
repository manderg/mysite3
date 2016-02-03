package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AuthInterceptor.preHandle Called");
		
		if(handler instanceof HandlerMethod == false){  //이미지나 css의 요청에 대한 부분을 걸러 내기위해
			return true; 
		}
		
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class); //디스페쳐 핸들러 매핑 코드를 까보자.
		if(auth == null){ //메서드에 auth를 안달아놨다면 true를 리턴 달려있는 경우에 아래로내려가 작업수행 -> 인증이 필요한 접근이 아니라는의미
			return true;
		}
		
		HttpSession session = request.getSession();
		if(session==null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		UserVo vo = (UserVo)session.getAttribute("authUser");
		if(vo==null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		return true;
	}	
}
