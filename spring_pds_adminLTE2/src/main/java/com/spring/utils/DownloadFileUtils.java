package com.spring.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadFileUtils {
	
	public static ResponseEntity<byte[]> download(String filePath)throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String formatName=filePath.substring(filePath.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream(filePath); 
				
			if(mType!=null) {
				headers.setContentType(mType);
			}else {
				String fileName=filePath.substring(filePath.indexOf("$$")+2);
				
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition","attachment; fileName=\""+new String(fileName.getBytes("utf-8"),"ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			in.close();
		}
		return entity;
	}
		
		
		
//	
//	public static void download(HttpServletRequest request,HttpServletResponse response,String filePath)throws Exception {
//		
//		ServletContext context = request.getServletContext();
//		
//		String mimeType = context.getMimeType(filePath);
//		
//		if(mimeType==null) {
//			mimeType = "application/octet-stream";
//		}
//		
//		response.setContentType(mimeType);
//
//		File downloadFile = new File(filePath);
//
//		FileInputStream inStream = new FileInputStream(downloadFile);
//		
//		response.setContentLength((int)downloadFile.length());
//		
//		String formatName=filePath.substring(filePath.lastIndexOf(".")+1);
//		MediaType mType = MediaUtils.getMediaType(formatName);
//		HttpHeaders headers = new HttpHeaders();
//		
//		if(mType!=null) {
//			headers.setContentType(mType);
//		}else {
//			String fileName=filePath.substring(filePath.indexOf("$$")+2);
//			
//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//			headers.add("Content-Disposition","attachment; fileName=\""+new String(fileName.getBytes("utf-8"),"ISO-8859-1")+"\"");
//		}
//		
//		
//		
//		
//		
////		String downloadFileName = filePath.split("\\$\\$")[1];
////		
////		downloadFileName = new String(downloadFile.getName().getBytes("utf-8"),"ISO-8859-1");
////		
////		String headerKey = "Content-Disposition";
////		String HeaderValue = String.format("attachment; fileName=\"%s\"", downloadFileName);
////		
////		response.setHeader(headerKey, HeaderValue);
//		
//		OutputStream outStream = response.getOutputStream();
//		byte[] buffer = new byte[4096];
//		int bytesRead = -1;
//		
//		while((bytesRead = inStream.read(buffer)) != -1) {
//			outStream.write(buffer, 0, bytesRead);			
//		}
//		
//		inStream.close();
//		outStream.close();
//	}
}
