package com.hanains.mysite.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;


@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return "하이염";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public Object json(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "success"); //success or fail
		map.put("message", null); //실패시 메시지
		map.put("data", new Boolean(true));
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object checkEmail(@RequestParam(value="email", required=true, defaultValue="")String email){
		System.out.println("요청들어옴");

		UserVo vo = userService.getUser(email);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "success"); //success or fail
		map.put("message", null); //실패시 메시지
		map.put("data", vo == null); //vo가 없으면 사용가능한 이메일 -true
		return map;
	}
}
