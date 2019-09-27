package com.spring.utils;


import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.spring.dto.AttachVO;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static AttachVO uploadFile(String uploadPath, String originalName, String loginUser_id, byte[] fileData)throws Exception{
		
		AttachVO attach = new AttachVO();
		
		attach.setAttacher(loginUser_id);
		
		String uid = UUID.randomUUID().toString().replace("-","");
		
		attach.setUuid(uid);
		
		String saveName=uid+"$$"+originalName;
		String savePath=calcPath(uploadPath,loginUser_id);
		
		attach.setUploadPath(savePath);
		attach.setFileType(originalName.substring(originalName.lastIndexOf(".")+1));
		attach.setFileName(originalName);
		
		File target = new File(uploadPath+savePath,saveName);
		FileCopyUtils.copy(fileData,  target);
		logger.info(target.getAbsolutePath());
		
		String formatName=originalName.substring(originalName.lastIndexOf(".")+1);
		
		if(MediaUtils.getMediaType(formatName)!=null) {
			makeThumbnail(uploadPath, savePath, saveName);			
		}else {
//			makeIcon(uploadPath, savePath, saveName);
		}
		
		
		
		
		
		
		return attach;
	}
	
	private static String calcPath(String uploadPath, String loginUser_id)throws Exception{
		
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE)+1);
		
		String savePath	= File.separator+loginUser_id+datePath;
		
		File path = new File(uploadPath+savePath);
		
		if(!path.exists()) {
			path.mkdirs();
		}
		
		logger.info(savePath);
		
		return savePath;
	}
	
	private static void makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
		
		BufferedImage sourceImg= ImageIO.read(new File(uploadPath+path,fileName));
		BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName=uploadPath+path+File.separator+"s_"+fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	}
	
//	private static void makeIcon(String uploadPath, String path, String fileName)throws Exception{
//		String iconName = uploadPath + path + File.separator+fileName;
//	}
}
