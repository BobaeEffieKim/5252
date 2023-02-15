package com.dev.vo;

import java.util.List;

public class DiaryCommentListVO extends DiaryVO {

	private List<DiaryCommentVO> commentList;

	public List<DiaryCommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<DiaryCommentVO> commentList) {
		this.commentList = commentList;
	}

}
