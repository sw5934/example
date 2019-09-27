package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.board.Criteria;
import com.spring.dto.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<NoticeVO> selectNoticeList() throws SQLException {
		List<NoticeVO> noticeList = session.selectList("Notice.selectNoticeList");
		return noticeList;
	}

	@Override
	public List<NoticeVO> selectNoticeListForPeriod() throws SQLException {
		List<NoticeVO> noticeList = session.selectList("Notice.selectNoticeListForPeriod", null);
		return noticeList;
	}

	@Override
	public List<NoticeVO> selectNoticeListForImp() throws SQLException {
		List<NoticeVO> noticeList = session.selectList("Notice.selectNoticeListForImp", null);
		return noticeList;
	}

	@Override
	public NoticeVO selectNoticeByNNO(int nno) throws SQLException {
		NoticeVO notice = session.selectOne("Notice.selectNoticeByNNO", nno);
		return notice;
	}
	
	@Override
	public int selectNextSeq()throws SQLException{
		int nextVal = session.selectOne("Notice.selectNextSeq");
		return nextVal;
	}

	@Override
	public void insertNotice(NoticeVO notice) throws SQLException {
		session.update("Notice.insertNotice",notice);
	}

	@Override
	public void updateNotice(NoticeVO notice) throws SQLException {
		session.update("Notice.updateNotice",notice);
	}

	@Override
	public void deleteNotice(int nno) throws SQLException {
		session.update("Notice.deleteNotice",nno);		
	}
	
	@Override
	public void intcreaseViewcnt(int nno) throws SQLException {
		session.update("Notice.increaseViewCnt",nno);
	}

	@Override
	public List<NoticeVO> selectNoticeList(Criteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<NoticeVO> noticeList = session.selectList("Notice.selectNoticeList", null, rowBounds);
		
		return noticeList;
	}

	@Override
	public int selectNoticeListCount() throws SQLException {
		int count = session.selectOne("Notice.selectNoticeListCount");
		return count;
	}

}
