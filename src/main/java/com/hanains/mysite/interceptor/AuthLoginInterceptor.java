package com.hanains.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		
		//리스너가만드는 어플리케이션 컨택스트를 가져오는것 웹어플리케이션 컨택스트를 가져오는 것  - 컨트롤러랑 서블릿들이 다들어있음 여기서 유저 서비스를 빼올것임
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService userService = applicationContext.getBean(UserService.class);
		
		UserVo authUser = userService.login(email,password);
		if(authUser == null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		return false;
	}// -> 로그인 인터셉터로 
}
