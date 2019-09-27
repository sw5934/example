package com.spring.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.dto.MemberVO;

public class LoginCheckFilter implements Filter{

	private List<String> exURLs = new ArrayList<String>();
	
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludeURLNames = filterConfig.getInitParameter("exclude");
		StringTokenizer st = new StringTokenizer(excludeURLNames,",");
		while(st.hasMoreElements()) {
			exURLs.add(st.nextToken());
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		HttpSession session = httpReq.getSession();
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}
		
		if(loginUser==null) {
			session.setAttribute("msg", "로그인은 필수입니다.");
			String url=httpReq.getContextPath()+"/login";
			httpResp.sendRedirect(url);
		}else {
			chain.doFilter(request, response);
		}
		
		
		
	}
	
	private boolean excludeCheck(String url) {
		for(String exURL:exURLs) {
			if(url.contains(exURL)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
