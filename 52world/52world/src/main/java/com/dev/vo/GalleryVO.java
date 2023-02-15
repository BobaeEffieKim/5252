package com.dev.vo;

// kbb) 사집첩에 필요한 데이터에 대한 정보

public class GalleryVO {

	private int postId;
	private String homepeeId;
	private String title;
	private String content;
	private String img;
	private String writer;
	private String createDate;
	private int views;
	private int likes;
	private String likeList;
	private String folder;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getLikeList() {
		return likeList;
	}

	public void setLikeList(String likeList) {
		this.likeList = likeList;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}