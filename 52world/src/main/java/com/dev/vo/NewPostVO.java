package com.dev.vo;

public class NewPostVO {
	private int postId;
	private String homepeeId;
	private String title;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getHomepeeId() {
		return homepeeId;
	}

	public void setHomepeeId(String homepeeId) {
		this.homepeeId = homepeeId;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private String writer;
	private String createDate;
	private String category;
}
