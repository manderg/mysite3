package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> list(Long page,String category, String keyword) {
		List<BoardVo> list = boardDao.getList(page,category,keyword);
		return list;
	}
	
	public void delete(Long no) {
		boardDao.delete(no);
	}

	public void insert(BoardVo vo) {
		boardDao.insert(vo);
	}

	public Long getLength(String category, String kwd) {
		Long length = boardDao.getLength(category, kwd);
		return length;
	}

	public BoardVo view(Long no) {
		//boardDao.increaseCount(no);
		BoardVo vo = boardDao.get(no);
		return vo;
	}

	public BoardVo modify(Long no) {
		BoardVo vo = boardDao.get(no);
		return vo;
	}
	
	public void update(BoardVo vo) {
		boardDao.update(vo);
	}

	public void reply(BoardVo vo) {
		System.out.println(vo.toString());
		boardDao.reply(vo);
	}

	public BoardVo get(Long no) {
		BoardVo vo = boardDao.get(no);
		return vo;
	}
	
}
