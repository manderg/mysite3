package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.GuestbookService;
import com.hanains.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("/")
	public String list(Model model){
		System.out.println("/guestbook/list");
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}
	@RequestMapping("/insert")
	public String insert(@ModelAttribute GuestbookVo vo){
		guestbookService.insert(vo);
		System.out.println("/guestbook/insert");
		return "redirect:/guestbook/";
	}
	@RequestMapping("/deleteform")
	public String deleteform(){
		System.out.println("/guestbook/deleteform");
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo){
		guestbookService.delete(vo);
		System.out.println("/guestbook/delete");
		return "redirect:/guestbook/";
	}
	
}
