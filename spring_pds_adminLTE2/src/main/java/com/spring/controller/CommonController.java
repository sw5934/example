package com.spring.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;
import com.spring.dto.MenuDTO;
import com.spring.utils.ListUpMenu;

@Controller
@RequestMapping("/")
public class CommonController {
	
	private static final Logger logger=	LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private MemberDAO memberDAO;

	/*
	@ModelAttribute("menus")
	public List<MenuDTO> menu(HttpServletRequest request) throws Exception {
		List<MenuDTO> menus = ListUpMenu.listMenu(request);
		return menus;
	}
	*/

	/*
	@RequestMapping("/{no}")
	public String page(Model model, @PathVariable("no") String no) throws Exception {
		String url = "page"+no;
		model.addAttribute("no", no);
		return url;
	}
	*/
	
	@RequestMapping("/main.htm")
	public void main() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
		logger.info("request url : /main");
		
	}
	
	@RequestMapping("/menu")
	@ResponseBody	//viewResolver로 넘기지 않겠다.
	public ResponseEntity<List<MenuDTO>> main(HttpServletRequest request){
		
		logger.info("request url : /main");
		
		ResponseEntity<List<MenuDTO>> responseEntity = null;
		
		try {
		List<MenuDTO> menuList = ListUpMenu.listMenu(request);
		responseEntity = new ResponseEntity(menuList,HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@RequestMapping("/subMenu")
	@ResponseBody
	public ResponseEntity<List<MenuDTO>> subMenu(@RequestBody Map<String,String> menuMap, HttpServletRequest request)throws Exception{
		
		String menu_code = (String)menuMap.get("menu_code");
		
		logger.info("request url : /subMenu");		
		logger.info("request data : "+menu_code);
		
		ResponseEntity<List<MenuDTO>> responseEntity=null;
		
		try {
			List<MenuDTO> subMenuList = ListUpMenu.listMenu(request, menu_code);
			responseEntity = new ResponseEntity<List<MenuDTO>>(subMenuList,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String loginGET()throws Exception{
		String url = "common/login";
		return url;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void loginPOST(String id, String pwd,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("request url : /login (post)");
		logger.info("id : "+id+", pwd : "+pwd);
		String message = "";
		String url= "";
		
		MemberVO member = memberDAO.selectMemberByID(id);
		
		if(member !=null) { //아이디가 있는경우
			if(pwd.equals(member.getPwd())) { //로그인성공
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", member);
				message = "로그인 성공 하셨습니다요";
				url = "main";
			}else { //패스워드 불일치
				message = "패스워드가 불일치합니다요";
				url = "login";
			}
		
			
	}else { //아이디가 없는경우
			message = "아이디가 존재하지 않는대요";
			url = "login";
		}		
	
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("self.location='"+url+"';");
		out.println("</script>");
	
	}
}
