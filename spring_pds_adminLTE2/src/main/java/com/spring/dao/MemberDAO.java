package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectMemberList() throws SQLException;

	public MemberVO selectMemberByID(String id) throws SQLException;

	public void insertMember(MemberVO member) throws SQLException;

	public void updateMember(MemberVO member) throws SQLException;

	public void deleteMember(String id) throws SQLException;

}
