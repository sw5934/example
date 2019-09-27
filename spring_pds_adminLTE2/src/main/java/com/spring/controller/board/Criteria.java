package com.spring.controller.board;

//상태유지
public class Criteria {
	private int page; // 페이지 번호
	private int perPageNum; // 한페이지 당 보여지는 리스트 개수

	public Criteria() { // 따로 수정없으면 1번페이지에 10개씩 출현
		this(1, 10);
	}

	public Criteria(int page, int perPageNum) {
		super();
		this.page = page;
		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			page = 1;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum > 50) {
			perPageNum = 50;
		}
		this.perPageNum = perPageNum;
	}

	public int getPageStartRowNum() { // 각 페이지마다 시작하는 행번호
		return (this.page - 1) * perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

}
