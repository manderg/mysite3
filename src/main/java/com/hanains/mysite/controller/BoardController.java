package com.hanains.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.annotation.AuthUser;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.service.FileUploadService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final String SAVE_PATH = "/temp/";
	@Autowired
	BoardService boardService;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping("/")
	public String list(
			@RequestParam(value = "page", defaultValue = "1") Long page,
			@RequestParam(value = "category", defaultValue = "title") String category,
			@RequestParam(value = "kwd", required = false) String kwd,
			Model model) {
		System.out.println("/board/list");
		Long length = boardService.getLength(category,kwd);
		List<BoardVo> list = boardService.list(page, category, kwd);
		
		model.addAttribute("length", Math.ceil((double)length/10));
		model.addAttribute("l",length); //갯수
		model.addAttribute("list", list);
		model.addAttribute("category", category);
		model.addAttribute("kwd", kwd);
		model.addAttribute("page",page);
		
		return "/board/list";
	}
	
	@Auth
	@RequestMapping("/insert")
	public String insert(
			@AuthUser UserVo authUser,
			@RequestParam(value = "no", required = false) Long no,
			HttpSession session, 
			@ModelAttribute BoardVo vo, 
//			@RequestParam("uploadFile")MultipartFile multipartFile,
			Model model) { //추후 @AuthUser UserVo vo 로 만들어주어야함
		System.out.println(authUser);

		if (session.getAttribute("authUser") == null) {
			return "redirect:/";
		} else {
			UserVo userVo = (UserVo) session.getAttribute("authUser");
			Long memberNo = userVo.getNo();
			if (no != null) {
				BoardVo targetboardvo = boardService.get(no); // 여기선 grp_no,
																// seq_no, lvl만
																// 필요함.
				vo.setMemberNo(memberNo);
				vo.setGrp_no(targetboardvo.getGrp_no());
				vo.setSeq_no(targetboardvo.getSeq_no());
				vo.setLvl(targetboardvo.getLvl());
				//System.out.println("출력된 vo : " + vo.toString());
				boardService.reply(vo);

			} else {
				//System.out.println("no가 없으므로 일반 글달기입니다.");
				vo.setMemberNo(memberNo);
				//System.out.println(vo.toString());
				boardService.insert(vo);
			}
//			if(multipartFile!=null){
//				String fileOriginalName = multipartFile.getOriginalFilename();
//		        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
//		        String fileName = multipartFile.getName();
//		        Long size = multipartFile.getSize();
//		        
//		        String saveFileName = fileUploadService.genSaveFileName( extName );
//		
//		        System.out.println( " ######## fileOriginalName : " + fileOriginalName );
//		        System.out.println( " ######## fileName : " + fileName );
//		        System.out.println( " ######## fileSize : " + size );
//		        System.out.println( " ######## fileExtensionName : " + extName );
//		        System.out.println( " ######## saveFileName : " + saveFileName );        
//		
//		        //fileUploadService.writeFile( multipartFile, SAVE_PATH, saveFileName );
//		        
//		        String url = "/profile-images/" + saveFileName;
//		        model.addAttribute( "profileUrl", url );
//		        System.out.println("업로드완료");
//			}			
			System.out.println("/board/insert");
			return "redirect:/board/";
		}
	}

	@Auth
	@RequestMapping("/write")
	public String write(@RequestParam(value = "no", required = false) Long no,
			Model model) {
		if (no != null) {// 댓글을 위한 write부분
			//System.out.println("no가 있으므로 model에 no를 찾아주입합니다.");
			// BoardVo vo = boardService.get(no);
			// System.out.println("출력된 vo : " + vo.toString());
			model.addAttribute("no", no);
		}
		System.out.println("/board/write");
		return "/board/write";
	}

	@RequestMapping("/view")
	public String view(@RequestParam(value = "no") Long no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo", vo);
		System.out.println("/board/view");
		return "/board/view";
	}

	@Auth
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "no") Long no) {
		boardService.delete(no);
		System.out.println("/board/delete");
		return "redirect:/board/";
	}

	@Auth
	@RequestMapping("/modify")
	public String modify(@RequestParam(value = "no") Long no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo", vo);
		System.out.println("/board/modify");
		return "/board/modify";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardVo vo) {
		boardService.update(vo);
		System.out.println("/board/update");
		return "redirect:/board/";
	}
}
