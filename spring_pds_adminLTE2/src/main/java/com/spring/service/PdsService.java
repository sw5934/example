package com.spring.service;

import java.util.Map;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.PdsVO;

public interface PdsService {
	
	Map<String,Object> getPdsList(SearchCriteria cri)throws Exception;
	
	PdsVO getPdsByPno(int pno)throws Exception;
	
	PdsVO readPds(int pno)throws Exception;
	
	void regist(PdsVO pds)throws Exception;
	
	void modify(PdsVO pds)throws Exception;
	
	void remove(int pno)throws Exception;

}
