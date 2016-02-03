package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.GuestbookDao;
import com.hanains.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestDao;

	public List<GuestbookVo> list() {
		List<GuestbookVo> list = guestDao.getList();
		return list;
	}

	public void insert(GuestbookVo vo) {
		guestDao.insert(vo);
	}

	public void delete(GuestbookVo vo) {
		guestDao.delete(vo);
	}	
}

