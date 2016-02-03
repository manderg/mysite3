package com.hanains.mysite.dao;

import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private OracleDataSource oracleDataSource;
	
	@Autowired
	private SqlSession sqlSession;


	public UserVo get(String email, String password){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getbyEmailAndPassword", map);
		return userVo;
	}	
	
	public UserVo get( Long no ) {
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert",vo);
	}

	public UserVo get(String email) {
		//System.out.println("email은 " +email);
		UserVo vo = sqlSession.selectOne("user.getByEmail",email);
		return vo;
	}
}