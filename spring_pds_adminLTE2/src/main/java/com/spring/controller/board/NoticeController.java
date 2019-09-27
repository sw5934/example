package com.spring.controller.board;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.NoticeVO;
import com.spring.service.NoticeService;

@Controller
@RequestMapping("/board/notice")
public class NoticeController {
	private static final Logger logger=	LoggerFactory.getLogger(NoticeController.class);

	@Resource(name="searchNoticeService")
	private NoticeService service;

	@ModelAttribute("category")
	public String category() throws Exception{
		return "notice";
	}

//	@RequestMapping("/admin/list")
//	public void noticeList(Model model) throws Exception {
//		List<NoticeVO> noticeList = service.listByAdmin();
//
//		model.addAttribute("noticeList", noticeList);
//	}

	@RequestMapping("/admin/listPage")
	public String noticeListPage(Criteria cri, Model model)throws Exception{
		//logger.info(cri.toString());
		String url = "board/notice/admin/list";


		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		Map<String, Object> dataMap = service.listByAdmin(pageMaker);

		model.addAllAttributes(dataMap);

		return url;
	}

	@RequestMapping("/admin/list")
	public String noticeSearchList(SearchCriteria cri, Model model)throws Exception{
		String url = "board/notice/admin/list";

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		Map<String, Object> dataMap = service.listByAdmin(pageMaker);

		model.addAllAttributes(dataMap);

		return url;
	}

	@RequestMapping(value="/admin/regist",method=RequestMethod.GET)
	public void registGet()throws Exception{}
	
	//simpledateFormat 사용
	@RequestMapping(value="/admin/regist", method=RequestMethod.POST)
	public void registPost(NoticeVO notice, HttpServletResponse response)throws Exception{
		
		logger.info(notice.toString());
		
		service.regist(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글등록을 완료했습니다.');");
		out.println("window.opener.location.reload();window.close();");
		out.println("</script>");
		
	}
	
	@RequestMapping("/admin/detail/{nno}")
	public String detail(@PathVariable("nno") int nno,Model model ) throws Exception{
		
		String url = "board/notice/admin/detail";
		
		NoticeVO notice = service.read(nno);
		
		model.addAttribute("notice", notice);
		
		return url;
	}
	
	@RequestMapping("/admin/modify/{nno}")
	public String modifyGET(@PathVariable("nno") int nno, Model model)throws Exception{
		
		String url = "board/notice/admin/modify";
		
		NoticeVO notice = service.getNotice(nno);
		
		model.addAttribute("notice", notice);
		
		return url;
	}
	
	@RequestMapping("/admin/modify")
	public void modifyPOST(NoticeVO notice, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		service.modify(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글수정을 완료했습니다.');");
		out.println("window.opener.location.reload();");
		out.println("self.location.href='"+request.getContextPath()+"/board/notice/admin/detail/"+notice.getNno()+"';");
		out.println("</script>");
		
	}
	
	@RequestMapping("/admin/delete/{nno}")
	public void delete(@PathVariable("nno") int nno, HttpServletResponse response)throws Exception{
		
		service.remove(nno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글삭제를 완료했읍니다.');");
		out.println("window.opener.location.reload();window.close()");
		out.println("</script>");
		
	}
	
	
	
	
	
	
	
	
	
	
}