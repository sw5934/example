package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.controller.board.Criteria;
import com.spring.dto.NoticeVO;

public interface NoticeDAO {
	public List<NoticeVO> selectNoticeList() throws SQLException;

	public List<NoticeVO> selectNoticeList(Criteria cri) throws SQLException;

	public int selectNoticeListCount() throws SQLException;

	public List<NoticeVO> selectNoticeListForPeriod() throws SQLException;

	public List<NoticeVO> selectNoticeListForImp() throws SQLException;

	public NoticeVO selectNoticeByNNO(int nno) throws SQLException;

	public int selectNextSeq() throws SQLException;

	public void insertNotice(NoticeVO notice) throws SQLException;

	public void updateNotice(NoticeVO notice) throws SQLException;

	public void deleteNotice(int nno) throws SQLException;

	public void intcreaseViewcnt(int nno) throws SQLException;

}
