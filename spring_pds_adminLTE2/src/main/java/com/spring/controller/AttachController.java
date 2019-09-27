package com.spring.controller;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.utils.DownloadFileUtils;

@RestController
@RequestMapping("/attach")
public class AttachController {
	
	@Autowired
	private AttachDAO attachDAO;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping("/get")
	public ResponseEntity<byte[]> getAttach(int ano)throws Exception{
		AttachVO attach = attachDAO.selectAttachByAno(ano);
		
		String fileName = attach.getUuid() + "$$" + attach.getFileName();
		String filePath = attach.getUploadPath();
		filePath = uploadPath+filePath+File.separator+fileName;
		
		return DownloadFileUtils.download(filePath);
	}
	

	@RequestMapping("/thum")
	public ResponseEntity<byte[]> getThum(int ano)throws Exception{
		AttachVO attach = attachDAO.selectAttachByAno(ano);
		
		String fileName = "s_" + attach.getUuid() + "$$" + attach.getFileName();
		String filePath = attach.getUploadPath();
		filePath = uploadPath+filePath+File.separator+fileName;
		
		return DownloadFileUtils.download(filePath);
	}
}
