package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping( "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform(){
		System.out.println("joinform");
		return "/user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		System.out.println("joinsuccess");
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		System.out.println("joinSuccess");
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		System.out.println("loginform");
		return "/user/loginform";
	}
//	
//	@RequestMapping("/login")
//	public String login(HttpSession session, @ModelAttribute UserVo vo){
//		System.out.println("loginform");
//		UserVo authUser = userService.login(vo.getEmail(),vo.getPassword());
//		System.out.println("로그인된 분은 : " + authUser.toString());
//		session.setAttribute("authUser",authUser);
//		return "redirect:/";
//	}
	
	@RequestMapping("/checkemail")
	public String checkemail(String email){
		System.out.println("checkemail");
		
		return "redirect:/";
	}
	
//	// 순서 : /login 요청이 들어옴 디스페쳐에서 login에 찾아들어옴 userService를 통해 비밀번호와 패스워드가 맞는 유저를 대려옴
//	@RequestMapping("/logout")
//	public String logout(HttpSession session){
//		System.out.println("loginlogout");		
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
}
