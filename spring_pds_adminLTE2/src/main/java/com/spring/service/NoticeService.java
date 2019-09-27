package com.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.Criteria;
import com.spring.controller.board.PageMaker;
import com.spring.dto.NoticeVO;

//service는 기능만!!
//메소드 네이밍은 연관성있게,규칙성있게!!
public interface NoticeService {

	// 리스트조회 by 관리자
	public List<NoticeVO> listByAdmin() throws SQLException;

	public List<NoticeVO> listByAdmin(Criteria cri) throws SQLException;

	public Map<String, Object> listByAdmin(PageMaker pageMaker) throws SQLException;

	// 리스트조회 by 사용자
	public Map<String, List<NoticeVO>> listByUsers() throws SQLException;

	// 글읽기
	public NoticeVO read(int nno) throws SQLException;

	// 글가져오기
	public NoticeVO getNotice(int nno) throws SQLException;

	// 글등록
	public void regist(NoticeVO notice) throws SQLException;

	// 글수정
	public void modify(NoticeVO notice) throws SQLException;

	// 글삭제
	public void remove(int nno) throws SQLException;
}
