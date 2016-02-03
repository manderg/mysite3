package com.hanains.mysite.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.hanains.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer arg1, 
			NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		if(this.supportsParameter(parameter) == false){ //파라미터가 밑에있는 메서드를 실행시키는데 파라미터를 따져보는거 오스유저가잇냐 유저브이오냐 
			return WebArgumentResolver.UNRESOLVED; // 리졀브 못하겠다. 해석안됨 
		}
		
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class); //HttpServletRequest로 변환하기 위한 메서드
		HttpSession session = request.getSession();
		if(session == null){
			return WebArgumentResolver.UNRESOLVED;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		return authUser;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return (parameter.getParameterAnnotation(AuthUser.class)!=null)
				&& (parameter.getParameterType().equals(UserVo.class) == true);
	}
}
