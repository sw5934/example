package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.controller.board.PageMaker;
import com.spring.controller.board.SearchCriteria;
import com.spring.dao.AttachDAO;
import com.spring.dao.PdsDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.PdsVO;

public class PdsServiceImpl implements PdsService{
	
	private PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}

	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	@Override
	public Map<String, Object> getPdsList(SearchCriteria cri) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<PdsVO> pdsList = pdsDAO.selectPdsList(cri);
		int totalCount = pdsDAO.selectPdsListCount(cri);
		for(PdsVO pds:pdsList) {
			List<AttachVO> attachList = attachDAO.selectAttachByPno(pds.getPno());
			pds.setAttachList(attachList);
		}
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		System.out.println(pdsList.get(1).toString());
		dataMap.put("pdsList", pdsList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public PdsVO getPdsByPno(int pno) throws Exception {
		PdsVO pds = pdsDAO.selectPdsByPno(pno);
		List<AttachVO> attachList = attachDAO.selectAttachByPno(pds.getPno());
		pds.setAttachList(attachList);
		return pds;
	}

	@Override
	public PdsVO readPds(int pno) throws Exception {
		PdsVO pds = pdsDAO.selectPdsByPno(pno);
		List<AttachVO> attachList = attachDAO.selectAttachByPno(pds.getPno());
		pds.setAttachList(attachList);
		pdsDAO.increaseViewCnt(pno);
		return pds;
	}

	@Override
	public void regist(PdsVO pds) throws Exception {
		int pno = pdsDAO.selectNextSeq();
		pds.setPno(pno);

		pdsDAO.insertPds(pds);
		
		List<AttachVO> attachList = pds.getAttachList();
		if(attachList != null) {
			for(AttachVO attach:attachList) {
				attach.setPno(pno);
				attach.setAttacher(pds.getWriter());
				attachDAO.insertAttach(attach);
				
			}
		}
	}

	@Override
	public void modify(PdsVO pds) throws Exception {
		pdsDAO.updatePds(pds);
		
		List<AttachVO> attachList = pds.getAttachList();
		if(attachList != null) {
			for(AttachVO attach:attachList) {
				attach.setPno(pds.getPno());
				attach.setAttacher(pds.getWriter());
				attachDAO.insertAttach(attach);				
			}			
		}
	}

	@Override
	public void remove(int pno) throws Exception {
		pdsDAO.deletePds(pno);
	}

}
