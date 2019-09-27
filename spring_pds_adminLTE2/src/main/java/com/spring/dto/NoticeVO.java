package com.spring.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NoticeVO {
	private int nno;					//글번호
	private String title;				//글제목
	private String id;					//작성자 id
	private String content;				//내용
	private int viewcnt;				//조회수	
	private Date startDate;				//게시 시작일
	private Date endDate;				//게시 종료일
	private Date regDate;				//작성일
	private int imp;					//중요 구분 0:일반, 1:중요
	
	public NoticeVO() {}	
	
	public NoticeVO(int nno, String title, String id, String content, int viewcnt, Date startDate, Date endDate,
			Date regDate, int imp) {
		super();
		this.nno = nno;
		this.title = title;
		this.id = id;
		this.content = content;
		this.viewcnt = viewcnt;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = regDate;
		this.imp = imp;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getStartDate() {
		return startDate;
	}

	//Date를 String타입으로 변경
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getImp() {
		return imp;
	}
	public void setImp(int imp) {
		this.imp = imp;
	}

	@Override
	public String toString() {
		return "NoticeVO [nno=" + nno + ", title=" + title + ", id=" + id + ", content=" + content + ", viewcnt="
				+ viewcnt + ", startDate=" + startDate + ", endDate=" + endDate + ", regDate=" + regDate + ", imp="
				+ imp + "]";
	}
	
	

}
