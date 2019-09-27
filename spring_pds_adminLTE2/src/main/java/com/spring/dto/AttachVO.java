package com.spring.dto;

import java.sql.Date;

public class AttachVO {
	private int ano;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private int pno;
	private String attacher;
	private Date regDate;
	private String uuid;
	public AttachVO() {
		super();
	}
	public AttachVO(int ano, String uploadPath, String fileName, String fileType, int pno, String attacher, Date regDate,
			String uuid) {
		super();
		this.ano = ano;
		this.uploadPath = uploadPath;
		this.fileName = fileName;
		this.fileType = fileType;
		this.pno = pno;
		this.attacher = attacher;
		this.regDate = regDate;
		this.uuid = uuid;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getAttacher() {
		return attacher;
	}
	public void setAttacher(String attacher) {
		this.attacher = attacher;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String toString() {
		return "AttachVO [ano=" + ano + ", uploadPath=" + uploadPath + ", fileName=" + fileName + ", fileType="
				+ fileType + ", pno=" + pno + ", attacher=" + attacher + ", regDate=" + regDate + ", uuid=" + uuid
				+ "]";
	}
}
