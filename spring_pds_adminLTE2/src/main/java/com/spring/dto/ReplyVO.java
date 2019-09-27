package com.spring.dto;

import java.util.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private String replyText;
	private String replyer;
	private Date regdate;
	private Date updatedate;
	
	public ReplyVO() {}
	public ReplyVO(int rno, int bno, String replyText, String replyer, Date regdate, Date updatedate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyText = replyText;
		this.replyer = replyer;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replytext) {
		this.replyText = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replytext=" + replyText + ", replyer=" + replyer
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
}
