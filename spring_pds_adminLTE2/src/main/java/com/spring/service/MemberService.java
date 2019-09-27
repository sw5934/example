package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;

//service : 기능구현을 위해 하나의 기능에 하나의 메소드
public interface MemberService {
	public List<MemberVO> getMemberList() throws SQLException;

	public MemberVO getMember(String id) throws SQLException;

	public void regist(MemberVO member) throws SQLException;

	public void modify(MemberVO member) throws SQLException;

	public void remove(String id) throws SQLException;

}
