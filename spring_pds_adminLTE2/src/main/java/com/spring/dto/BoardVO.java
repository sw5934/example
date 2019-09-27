package com.spring.dto;

import java.util.Date;

public class BoardVO {

	private int bno; 				// 게시글 번호
	private String title;
	private String writer;
	private String content;
	private int viewcnt; 			// 조회수
	private Date regDate; 			// 등록날짜
	private Date updatedate; 		// 수정날짜

	private int replycnt;			//게시글에 달린 댓글수 

	public BoardVO() {}				//변수들의 초기화를 위해 생성자를 생성

	//인스턴스를 생성하는 시점에 만드는 생성자
	public BoardVO(int bno, String title, String writer, String content, int viewcnt, Date regDate, Date updatedate,
			int replycnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
		this.updatedate = updatedate;
		this.replycnt = replycnt;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", viewcnt="
				+ viewcnt + ", regDate=" + regDate + ", updatedate=" + updatedate + ", replycnt=" + replycnt + "]";
	}

}