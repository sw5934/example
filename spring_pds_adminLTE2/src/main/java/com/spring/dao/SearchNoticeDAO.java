package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.SearchCriteria;
import com.spring.dto.NoticeVO;

public interface SearchNoticeDAO extends NoticeDAO {
	
	public List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException;
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException;

}
