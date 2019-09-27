package com.spring.controller.board;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.BoardVO;
import com.spring.dto.MemberVO;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board/free/admin")
public class FreeController {	

	@Autowired
	private BoardService bService;
		
	
	private static final Logger logger=	LoggerFactory.getLogger(FreeController.class);

	@ModelAttribute("category")
	public String category() throws Exception{
		return "free";
	}	

	@ModelAttribute("loginUser")	//임시 로그인 아이디
	public MemberVO loginUser() throws Exception{
		MemberVO loginUser = new MemberVO();
		loginUser.setId("mimi");
		return loginUser;
	}	
		
	@RequestMapping("/list")
	public ModelAndView freeList(SearchCriteria cri, ModelAndView modelnView) throws SQLException{
		
		String url = "board/free/admin/list";
		
		Map<String, Object> dataMap = bService.getBoardList(cri);
		
		modelnView.addObject("dataMap", dataMap);
		modelnView.setViewName(url);
		
		return modelnView;		
	}
	
	@RequestMapping("/detail")
	public ModelAndView freeDetail(int bno, ModelAndView modelnView) throws SQLException{
		String url="board/free/admin/detail";
		
		BoardVO board = bService.readBoard(bno);
		
		modelnView.addObject("board", board);
		modelnView.setViewName(url);
		
		return modelnView;
	}

	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registGet()throws Exception{}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public void freeRegist(BoardVO board, HttpServletResponse response)throws Exception{
		
		bService.regist(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='/board/board/free/admin/list';window.close();");
		out.println("</script>");
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyForm(int bno, Model model) throws Exception{
		
		BoardVO board = bService.getBoardForModify(bno);
		model.addAttribute("board", board);
	}

	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public void updatePOST(BoardVO board, HttpServletResponse response) throws Exception{
		
		board.setUpdatedate(new Date());
		
		bService.modify(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload();");
		out.println("location.href='detail?bno="+board.getBno()+"';");
		out.println("</script>");
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public void remove(int bno, HttpServletResponse response) throws Exception{
		
		bService.remove(bno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글을 삭제하였습니다.')");
		out.println("window.opener.location.reload();window.close()");
		out.println("</script>");
	}

}
