package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.NoticeDAO;
import com.spring.dao.SearchNoticeDAO;
import com.spring.dao.SearchNoticeDAOImpl;
import com.spring.dto.NoticeVO;

public class SearchNoticeListServiceImpl extends NoticeServiceImpl {
	
	private SearchNoticeDAO searchNoticeDAO;
	public void setSearchNoticeDAO(SearchNoticeDAO noticeDAO) {
		this.searchNoticeDAO = noticeDAO;
	}

	@Override
	public Map<String, Object> listByAdmin(PageMaker pageMaker) throws SQLException {
		 
		SearchCriteria scri = (SearchCriteria)pageMaker.getCri();
		
		List<NoticeVO> noticeList = searchNoticeDAO.selectSearchNoticeList(scri);
		
		int totalCount = searchNoticeDAO.selectSearchNoticeListCount(scri);
		pageMaker.setTotalCount(totalCount);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("noticeList", noticeList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	

}
