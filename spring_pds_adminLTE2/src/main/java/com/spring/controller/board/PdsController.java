package com.spring.controller.board;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.MemberVO;
import com.spring.dto.PdsVO;
import com.spring.service.PdsService;
import com.spring.utils.DeleteFileUtil;
import com.spring.utils.UploadFileUtils;

@Controller
@RequestMapping("/board/pds/admin")
public class PdsController {

	@Autowired
	private AttachDAO attachDAO;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Autowired
	private PdsService service;

	@ModelAttribute("category")
	public String category() throws Exception{
		return "pds";
	}

	@ModelAttribute("loginUser")
	public MemberVO loginUser() throws Exception{
		MemberVO loginUser = new MemberVO();
		loginUser.setId("mimi");
		return loginUser;
	}
	@RequestMapping("/list")
	public void getPdsList(SearchCriteria cri, Model model)throws Exception{
		Map<String, Object> dataMap = service.getPdsList(cri);

		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registGET()throws Exception{}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public void registPOST(PdsVO pds, MultipartFile[] uploadFile, HttpServletResponse response)throws Exception{
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();

		for(MultipartFile file : uploadFile) {
			AttachVO attach = UploadFileUtils.uploadFile(uploadPath,file.getOriginalFilename(),pds.getWriter(), file.getBytes());
			attachList.add(attach);
		}
		pds.setAttachList(attachList);
		
		service.regist(pds);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script>");
		out.println("window.opener.location.reload();window.close();");
		out.println("</script>");
		
	}
	
	@RequestMapping("/detail")
	public void detail(int pno,Model model)throws Exception{
		PdsVO pds = service.readPds(pno);
		model.addAttribute("pds",pds);
	}

	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(int pno,Model model)throws Exception{
		
		PdsVO pds = service.getPdsByPno(pno);
		model.addAttribute("pds",pds);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public void modifyPOST(PdsVO pds,int[] deleteFile,  MultipartFile[] uploadFile, HttpServletResponse response)throws Exception{
		System.out.println(pds);
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();

		for(MultipartFile file : uploadFile) {
			AttachVO attach = UploadFileUtils.uploadFile(uploadPath,file.getOriginalFilename(),pds.getWriter(), file.getBytes());
			attachList.add(attach);
		}
		pds.setAttachList(attachList);
		
		if(deleteFile!=null) {
		for(int ano:deleteFile) {
			AttachVO attach = attachDAO.selectAttachByAno(ano);
			attachDAO.deleteAttach(ano);
			DeleteFileUtil.delete(attach,uploadPath);
			
		}}
		service.modify(pds);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script>");
		out.println("window.opener.location.reload();");
		out.println("location.href='detail?pno="+pds.getPno()+"';");
		out.println("</script>");
	}
	

	@RequestMapping("/remove")
	public void remove(int pno, HttpServletResponse response)throws Exception{

		for(AttachVO attach:attachDAO.selectAttachByPno(pno)) {
			DeleteFileUtil.delete(attach,uploadPath);
		}
		
		service.remove(pno);
		attachDAO.deleteAllAttach(pno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script>");
		out.println("window.opener.location.reload();window.close()");
		out.println("</script>");
	}
}
