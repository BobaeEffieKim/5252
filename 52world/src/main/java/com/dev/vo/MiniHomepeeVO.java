package com.dev.vo;

import lombok.ToString;

@ToString
public class MiniHomepeeVO {
	private String homepeeId;
	private String backgroundColor;
	private String bgm;
	private String miniroom;
	private String name;
	private int visitTotal;
	private int visitToday;
	private String tabColor;
	private String visiter;

	public String getVisiter() {
		return visiter;
	}

	public void setVisiter(String visiter) {
		this.visiter = visiter;
	}

	public String getTabColor() {
		return tabColor;
	}

	public void setTabColor(String tabColor) {
		this.tabColor = tabColor;
	}

	public String getHomepeeId() {
		return homepeeId;
	}

	public void setHomepeeId(String homepeeId) {
		this.homepeeId = homepeeId;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBgm() {
		return bgm;
	}

	public void setBgm(String bgm) {
		this.bgm = bgm;
	}

	public String getMiniroom() {
		return miniroom;
	}

	public void setMiniroom(String miniroom) {
		this.miniroom = miniroom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVisitTotal() {
		return visitTotal;
	}

	public void setVisitTotal(int visitTotal) {
		this.visitTotal = visitTotal;
	}

	public int getVisitToday() {
		return visitToday;
	}

	public void setVisitToday(int visitToday) {
		this.visitToday = visitToday;
	}

}
