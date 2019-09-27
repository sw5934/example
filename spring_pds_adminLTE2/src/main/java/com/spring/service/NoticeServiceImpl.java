package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.PageMaker;
import com.spring.dao.NoticeDAO;
import com.spring.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService {

	private NoticeDAO noticeDAO;

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public List<NoticeVO> listByAdmin() throws SQLException {
		List<NoticeVO> notice = noticeDAO.selectNoticeList();
		return notice;
	}

	@Override
	public Map<String, List<NoticeVO>> listByUsers() throws SQLException {
		List<NoticeVO> listByPeriod = noticeDAO.selectNoticeListForPeriod();
		List<NoticeVO> listByImp = noticeDAO.selectNoticeListForImp();

		Map<String, List<NoticeVO>> listMap = new HashMap<String, List<NoticeVO>>();
		listMap.put("listByPeriod", listByPeriod);
		listMap.put("listByImp", listByImp);

		return listMap;
	}

	@Override
	public NoticeVO read(int nno) throws SQLException {

		NoticeVO notice = noticeDAO.selectNoticeByNNO(nno);
		noticeDAO.intcreaseViewcnt(nno);

		return notice;
	}

	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		NoticeVO notice = noticeDAO.selectNoticeByNNO(nno);
		return notice;
	}

	@Override
	public void regist(NoticeVO notice) throws SQLException {
		int nno = noticeDAO.selectNextSeq();
		notice.setNno(nno);
		
		noticeDAO.insertNotice(notice);

	}

	@Override
	public void modify(NoticeVO notice) throws SQLException {
		noticeDAO.updateNotice(notice);

	}

	@Override
	public void remove(int nno) throws SQLException {
		noticeDAO.deleteNotice(nno);

	}

	@Override
	public List<NoticeVO> listByAdmin(Criteria cri) throws SQLException {
		List<NoticeVO> noticeList = noticeDAO.selectNoticeList(cri);
		return noticeList;
	}

	@Override
	public Map<String, Object> listByAdmin(PageMaker pageMaker) throws SQLException {
		
		List<NoticeVO> noticeList = noticeDAO.selectNoticeList(pageMaker.getCri());
		
		int totalCount = noticeDAO.selectNoticeListCount();
		
		pageMaker.setTotalCount(totalCount);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("noticeList", noticeList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

}
