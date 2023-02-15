package com.dev.service;

import java.util.List;

import com.dev.dao.BffCommentDAO;
import com.dev.dao.BffDAO;
import com.dev.vo.BffCommentVO;
import com.dev.vo.BffVO;

public class BffService {

	private static BffService instance = new BffService();
	BffDAO dao = new BffDAO();
	BffCommentDAO cdao = new BffCommentDAO();

	private BffService() {
	}

	public static BffService getInstance() {
		return instance;
	}

	// 일촌신청
	public void requestBff(BffVO vo) {
		dao.requestBff(vo);
	}

	// 일촌 목록 보기
	public List<BffVO> requestBffList(String id) {
		return dao.requestBffList(id);
	}
	
	// 일촌 목록 보기 + 이름까지 포함해서 조인문... 할수 있을까???????????
	public List<BffVO> getBffReqList(String id) {
		return dao.getBffReqList(id);
	}	

	// 일촌 수락 (Null → Y) UPDATE
	public void yesBff(int id) {
		dao.updateBff(id);
	}

	// 일촌 거절 (Null → N) DELETE
	public void noBff(int id) {
		dao.deleteBff(id);
	}
	// 친구찾기(이름으로 검색)
	
	// 일촌리스트
	public List<BffVO> getBffList(String id) {
		return dao.getBffList(id);
	}

	// 일촌평 등록
	public void insertComment(BffCommentVO vo) {
		cdao.insertComment(vo);
	}
	
	// 일촌평 수정
	public void updateComment(BffCommentVO vo) {
		cdao.updateComment(vo);
	}
	// 일촌평 삭제
	public boolean deleteComment(int commId) {
		return cdao.deleteComment(commId);
	}

	// 일촌평 목록(아이디로 검색)
	public List<BffCommentVO> getCommentList(String homepeeId) {
		return cdao.getCommentList(homepeeId);
	}
}
