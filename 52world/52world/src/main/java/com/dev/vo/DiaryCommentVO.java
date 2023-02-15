
package com.dev.vo;

public class DiaryCommentVO {

	private int commId;
	private int postId;
	private String content;
	private String writer;
	private String createDate;
	
	
	
	public int getCommId() {
		return commId;
	}
	public void setCommId(int commId) {
		this.commId = commId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "DiaryCommentVO [commId=" + commId + ", postId=" + postId + ", content=" + content + ", writer=" + writer
				+ ", createDate=" + createDate + "]";
	}
	
	
	
}

