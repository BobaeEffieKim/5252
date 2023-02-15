package com.dev.vo;

// kbb) 사집첩 폴더에 대한 VO

public class GalleryFolderVO {

	private String folderName;
	private int secret;
	private String homepeeId;

	public String getHomepeeId() {
		return homepeeId;
	}

	public void setHomepeeId(String homepeeId) {
		this.homepeeId = homepeeId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}

}