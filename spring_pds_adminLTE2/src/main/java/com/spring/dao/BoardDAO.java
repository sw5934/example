package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException;
	public int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	public BoardVO selectBoardByBno(int bno) throws SQLException;
	
	public void insertBoard(BoardVO board) throws SQLException;
	public void updateBoard(BoardVO board) throws SQLException;
	public void deleteBoard(int bno) throws SQLException;
	
	//viewcnt 증가
	public void increaseViewCnt(int bno) throws SQLException;
	
	//board_seq.nextval 가져오기
	public int selectBoardSeqNext() throws SQLException;

}
