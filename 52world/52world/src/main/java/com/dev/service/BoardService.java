package com.dev.service;

import java.util.List;

import com.dev.dao.NewPostDAO;
import com.dev.vo.NewPostVO;

public class BoardService {
	private static BoardService instance = new BoardService();
	NewPostDAO dao = new NewPostDAO();

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardService();
		}
		return instance;
	}

	// 최근게시물 5개 불러오기
	public List<NewPostVO> getNew(String homepeeId) {
		return dao.getNew(homepeeId);
	}

	// 전체게시판 게시물 갯수 가져오기
	public int[] getPostNumber(String homepeeId) {
		int[] arr = new int[6];
		arr[0] = dao.dToday(homepeeId);
		arr[1] = dao.dTotal(homepeeId);
		arr[2] = dao.gToday(homepeeId);
		arr[3] = dao.gTotal(homepeeId);
		arr[4] = dao.gbToday(homepeeId);
		arr[5] = dao.gbTotal(homepeeId);
		return arr;
	}
}
