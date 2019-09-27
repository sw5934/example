package com.spring.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//view의 이름이 아니다
@RestController
public class UploadImgController {

	//첨부파일 용도
	@Resource(name = "imgPath")
	private String imgPath;


	//ResponseEntity: 자료가 잘 전송되었는지 확인을 위한 응답코드도 같이 발송
	@RequestMapping("/uploadImg")
	public ResponseEntity<String> uploadImg(MultipartFile file, String id,
											HttpServletRequest request) throws Exception {

		ResponseEntity<String> result = null;

		int fileSize = 5 * 1024 * 1024;			//5MB 제한 

		//Bad_request : 400에러
		if(file.getSize() > fileSize) {
			return new ResponseEntity<String>("용량 초과입니다.", HttpStatus.BAD_REQUEST);
		}

		String savePath = request.getServletContext().getRealPath(imgPath + id + "/");
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		fileName = fileName + fileFormat;		

		File saveFile = new File(savePath + fileName);
		
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		try {
		file.transferTo(saveFile);
		result = new ResponseEntity<String>(request.getContextPath() + imgPath + id + "/" + fileName, HttpStatus.OK);
		} catch(Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	//return 타입은 어떤걸로 할건지 parameter는 어떤걸로 할건지가 중요!!
	//deleteImg메소드에서는 ResponseEntity<String> return 타입, 
	//(String fileName, String id, HttpServletRequest request)이 parameter
	@RequestMapping("/deleteImg")
	public ResponseEntity<String> deleteImg(String fileName, String id, HttpServletRequest request) throws Exception{
		
		ResponseEntity<String> result = null;
		
		String savePath = request.getServletContext().getRealPath(imgPath + "/" + id + "/");
		
		File target = new File(savePath + fileName);
		
		if(!target.exists()) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}else {
			try {
				target.delete();
				result = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			}catch(Exception e) {
				//e.printStackTrace(); 에러보고 싶을시
				result = new ResponseEntity<String>("Fail",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return result;		
	}	
}