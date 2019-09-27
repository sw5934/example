package com.spring.utils;

import java.io.File;

import org.springframework.http.MediaType;

import com.spring.dto.AttachVO;

public class DeleteFileUtil {
	public static void delete(AttachVO attach, String uploadPath)throws Exception{
		
		MediaType mtype = MediaUtils.getMediaType(attach.getFileType());

		String filePath = uploadPath+""
						+attach.getUploadPath().replace("\\", File.separator)
						+File.separator;
						
		String fileName = attach.getUuid()
						+"$$"
						+attach.getFileName();

		System.out.println(filePath + fileName);
		
		
		if(mtype != null) {
			File targetThum = new File(filePath + "s_" + fileName);
			targetThum.delete();
		}
		
		File target = new File(filePath + fileName);
		target.delete();
	}
}