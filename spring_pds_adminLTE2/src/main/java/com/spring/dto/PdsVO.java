package com.spring.dto;

import java.sql.Date;
import java.util.List;

public class PdsVO {
	private int pno;
	private String title;
	private String writer;
	private String content;
	private int viewCnt;
	private Date regDate;
	private Date updateDate;
	private List<AttachVO> attachList;
	

	public PdsVO() {
		super();
	}
	public PdsVO(int pno, String title, String writer, String content, int viewCnt, Date regDate, Date updateDate,
			List<AttachVO> attachList) {
		super();
		this.pno = pno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.attachList = attachList;
	}
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "PdsVO [pno=" + pno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", viewCnt="
				+ viewCnt + ", regDate=" + regDate + ", updateDate=" + updateDate + ", attachList=" + attachList + "]";
	}

}
