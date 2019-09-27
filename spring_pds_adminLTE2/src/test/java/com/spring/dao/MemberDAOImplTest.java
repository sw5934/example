package com.spring.dao;

import static org.junit.Assert.*;


import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class MemberDAOImplTest {
	
	@Autowired
	private MemberDAO memberDAO;


	@Test
	public void testSelectMemberList() throws Exception{
		List<MemberVO> memberList = memberDAO.selectMemberList();
		Assert.assertThat(memberList.size(), Matchers.greaterThan(0));
	}

	@Test
	public void testSelectMemberByID() throws Exception {
		String id = "mimi";
		MemberVO dataMember = memberDAO.selectMemberByID(id);
		Assert.assertEquals(id, dataMember.getId());
	
	}
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO member = new MemberVO();
		member.setId("mama");
		member.setPwd("mama");
		member.setEmail("mama@mama.com");
		member.setName("mama");
		member.setPhone("010-1234-5678");
		member.setPicture("noImage.jpg");
		
		memberDAO.insertMember(member);
		
		MemberVO dataMember = memberDAO.selectMemberByID(member.getId());
		Assert.assertEquals(member.getPwd(), dataMember.getPwd());
	}

	@Test
	public void testUpdateMember() throws Exception{
		MemberVO member = memberDAO.selectMemberByID("mimi");
		member.setPwd("1234");
		
		memberDAO.updateMember(member);
		
		MemberVO dataMember = memberDAO.selectMemberByID(member.getId());
		
		Assert.assertEquals(member.getPwd(), dataMember.getPwd());
	}
	
	@Test
	public void testDeleteMember() throws Exception{
		final String id = "mimi";
		
		MemberVO member = memberDAO.selectMemberByID(id);
		
		Assert.assertThat(member,Matchers.notNullValue());
		
		memberDAO.deleteMember(id);
		
		member = memberDAO.selectMemberByID(id);
		
		Assert.assertThat(member, Matchers.nullValue());
	}

}
