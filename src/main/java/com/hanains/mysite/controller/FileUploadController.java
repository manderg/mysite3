package com.hanains.mysite.controller;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final Log LOG = LogFactory.getLog( FileUploadController.class );
	private static final String SAVE_PATH = "/temp/";
	  
	@RequestMapping( "/form" )
	public String form() {
		return "form";
	}
		
	@RequestMapping( "/forms" )
	public String forms() {
		return "forms";
	}
	
	@RequestMapping( "/upload" )
	public String upload(@RequestParam Long no,  
						@RequestParam( "uploadFile" ) 
						MultipartFile multipartFile, Model model ) {
		// 파일 처리 
		if( multipartFile.isEmpty() == false ) {
			
	        String fileOriginalName = multipartFile.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
	        String fileName = multipartFile.getName();
	        Long size = multipartFile.getSize();
	        
	        String saveFileName = genSaveFileName( extName );
	
	        LOG.debug( " ######## fileOriginalName : " + fileOriginalName );
	        LOG.debug( " ######## fileName : " + fileName );
	        LOG.debug( " ######## fileSize : " + size );
	        LOG.debug( " ######## fileExtensionName : " + extName );
	        LOG.debug( " ######## saveFileName : " + saveFileName );        
	
	        writeFile( multipartFile, SAVE_PATH, saveFileName );
	        
	        String url = "/profile-images/" + saveFileName;
	        model.addAttribute( "profileUrl", url );
	        System.out.println("업로드완료");
		}
        return "/board/write";
	}
        
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}
}
