package com.dev.common;

public class Page {

	//페이징 처리 하단 페이지 수 띄우는것
	private int startPage; //첫번째. 150페이지면 (11~20)
	private int endPage;   //마지막
	private boolean prev; //이전페이지
	private boolean next; //다음페이지
	
	private int total; //데이터의 전체 건수
	private Criteria cri;
	
	public Page(Criteria cri, int total) {
		
		this.cri=cri;
		this.total=total;
		this.endPage = (int)(Math.ceil(cri.getPageNum()/5.0))*5; //11~17
		this.startPage=this.endPage-4;
		int realEnd=(int) (Math.ceil(total*1.0/cri.getAmount()));
		
		if(this.endPage>realEnd) 
			this.endPage=realEnd;
		this.prev=this.startPage>1;
		this.next=this.endPage<realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	
}
