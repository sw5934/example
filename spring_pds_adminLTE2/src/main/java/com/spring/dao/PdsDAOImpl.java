package com.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	

	@Override
	public List<PdsVO> selectPdsList(SearchCriteria cri) throws Exception {
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<PdsVO> pdsList = session.selectList("Pds.selectSearchPdsList",cri,rowBounds);
		
		return pdsList;
	}

	@Override
	public int selectPdsListCount(SearchCriteria cri) throws Exception {
		int count = session.selectOne("Pds.selectPdsListCount",cri);
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws Exception {
		PdsVO pds = session.selectOne("Pds.selectPdsByPno",pno);
		return pds;
	}

	@Override
	public int selectNextSeq() throws Exception {
		int nextSeq = session.selectOne("Pds.selectPdsSeqNext");
		return nextSeq;
	}

	@Override
	public void increaseViewCnt(int pno) throws Exception {
		session.update("Pds.increaseViewCnt",pno);
		
	}

	@Override
	public void insertPds(PdsVO pds) throws Exception {
		session.update("Pds.insertPds",pds);
		
	}

	@Override
	public void updatePds(PdsVO pds) throws Exception {
		session.update("Pds.updatePds",pds);
		
	}

	@Override
	public void deletePds(int pno) throws Exception {
		session.update("Pds.deletePds",pno);
		
	}

}
