package com.hanains.mysite.dao;

import java.util.List;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private OracleDataSource oracleDataSource;
	
	@Autowired
	private SqlSession sqlSession;


	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}	
	
	public void delete( GuestbookVo vo ) {		
		sqlSession.update("guestbook.delete",vo);
	}
	
	public void insert( GuestbookVo vo ) {		
		sqlSession.update("guestbook.insert",vo);
	}	
}
