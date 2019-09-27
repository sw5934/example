package com.spring.dao;

import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.PdsVO;

public interface PdsDAO {
	List<PdsVO> selectPdsList(SearchCriteria cri)throws Exception;
	int selectPdsListCount(SearchCriteria cri)throws Exception;
	
	PdsVO selectPdsByPno(int pno)throws Exception;
	
	int selectNextSeq()throws Exception;
	
	void increaseViewCnt(int pno)throws Exception;
	
	void insertPds(PdsVO pds)throws Exception;
	
	void updatePds(PdsVO pds)throws Exception;
	
	void deletePds(int pno)throws Exception;
	
}
