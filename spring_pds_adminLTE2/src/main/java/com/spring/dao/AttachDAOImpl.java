package com.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<AttachVO> selectAttachByPno(int pno) throws Exception {
		List<AttachVO> attachList = session.selectList("Attach.selectAttachByPno",pno);
		System.out.println("123");
		System.out.println(attachList);
		return attachList;
	}

	@Override
	public AttachVO selectAttachByAno(int ano) throws Exception {
		AttachVO attach = session.selectOne("Attach.selectAttachByAno",ano);
		return attach;
	}

	@Override
	public void insertAttach(AttachVO attach) throws Exception {
		session.update("Attach.insertAttach",attach);
		
	}

	@Override
	public void deleteAttach(int ano) throws Exception {
		session.update("Attach.deleteAttach",ano);
		
	}

	@Override
	public void deleteAllAttach(int pno) throws Exception {
		session.update("Attach.deleteAllAttach",pno);
		
	}

}
