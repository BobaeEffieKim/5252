package com.dev.vo;

public class DiaryVO {

	private int postId;
	private String homepeeId;
	private String title;
	private String content;
	private String writer;
	private String createDate;
	private int views;
	private int likes;
	private String like_list;
	private String folder;
	private int rn;

	private int cnt; // 댓글 수.

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getLike_list() {
		return like_list;
	}

	public void setLike_list(String like_list) {
		this.like_list = like_list;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "DiaryVO [postId=" + postId + ", homepeeId=" + homepeeId + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", createDate=" + createDate + ", views=" + views + ", likes=" + likes
				+ ", like_list=" + like_list + ", folder=" + folder + ", rn=" + rn + ", cnt=" + cnt + "]";
	}

}
