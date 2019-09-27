package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException {

		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<BoardVO> boardList=
				session.selectList("Board.selectSearchBoardList", cri,rowBounds);

		return boardList;
	}

	@Override
	public int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Board.selectSearchBoardListCount",cri);
		return count;
	}
	
	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		BoardVO board = session.selectOne("Board.selectBoardByBno", bno);
		return board;
	}


	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		session.update("Board.insertBoard",board);

	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		session.update("Board.updateBoard",board);

	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		session.update("Board.deleteBoard",bno);

	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		session.update("Board.increaseViewCnt",bno);

	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		int seq_num= session.selectOne("Board.selectBoardSeqNext");
		return seq_num;
	}


}
