package com.spring.dao;

import java.util.List;

import com.spring.dto.AttachVO;

public interface AttachDAO {
	
	List<AttachVO> selectAttachByPno(int pno)throws Exception;
	
	AttachVO selectAttachByAno(int ano)throws Exception;

	void insertAttach(AttachVO attach)throws Exception;
	
	void deleteAttach(int ano)throws Exception;
	
	void deleteAllAttach(int pno)throws Exception;
}
