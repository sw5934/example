package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;


import com.spring.controller.board.SearchCriteria;
import com.spring.dto.NoticeVO;


public class SearchNoticeDAOImpl extends NoticeDAOImpl implements SearchNoticeDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException {
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();

		RowBounds rowBounds = new RowBounds(offset, limit);

		List<NoticeVO> noticeList = session.selectList("Notice.selectSearchNoticeList", cri, rowBounds);

		return noticeList;
	}

	@Override
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Notice.selectSearchNoticeListCount",cri);
		return count;
	}

}
