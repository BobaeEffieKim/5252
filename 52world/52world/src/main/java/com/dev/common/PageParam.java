package com.dev.common;

import lombok.Data;

@Data
public class PageParam {
	// 폴더, 로그인아이디, 홈피아이디,
	private String folder;
	private String loginId;
	private String homepeeId;
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getHomepeeId() {
		return homepeeId;
	}
	public void setHomepeeId(String homepeeId) {
		this.homepeeId = homepeeId;
	}

	
	
}
