package com.hanains.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	public UserVo login(String email, String password){
		UserVo authUser = userDao.get(email,password);
		return authUser;
	}

	public UserVo getUser(String email) {
		System.out.println("서비스들어옴");
		UserVo userVo = userDao.get(email);
		System.out.println("dao실행후 다시 서비스로");
		return userVo;
	}
}
