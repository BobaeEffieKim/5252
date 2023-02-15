package com.dev.service;

import java.util.List;

import com.dev.common.Criteria;
import com.dev.dao.DiaryCommentDAO;
import com.dev.dao.DiaryDAO;
import com.dev.vo.DiaryCommentListVO;
import com.dev.vo.DiaryCommentVO;
import com.dev.vo.DiaryVO;

public class DiaryService {

	private static DiaryService instance = new DiaryService();
	DiaryDAO dao = new DiaryDAO();
	DiaryCommentDAO cdao = new DiaryCommentDAO();

	private DiaryService() {
	}

	public static DiaryService getInstance() {
		return instance;
	}

	// write diary
	public void writeDiary(DiaryVO vo) {
		dao.writeDiary(vo);
	}

	// 게시글 목록
	public List<DiaryVO> boardList(String homepeeId) {
		return dao.getList(homepeeId);
	}

	// 페이징
	public List<DiaryVO> getListPaging(String homepeeId, Criteria cri) {
		return dao.getListPaging(homepeeId, cri); // 10건씩
	}

	// delete diary

	public boolean deleteDiary(int postId) {
		return dao.deleteDiary(postId);
	}

	// update diary

	public void updateDiary(DiaryVO vo) {
		dao.updateDiary(vo);
	}

	// diary comment insert
	public void insetComment(DiaryCommentVO vo) {
		cdao.insetComment(vo);
	}

	// diary comment update
	public void updateComment(DiaryCommentVO vo) {
		cdao.updateComment(vo);
	}

	// diary Comment List

	public List<DiaryVO> getDiaryCntList(String pidList) {
		return cdao.getDiaryCntList(pidList);
	}

	// diary Comment 갯수 세기

	public List<DiaryCommentVO> commentCnt(String pidList) {
		return cdao.commentCnt(pidList); // 10건씩
	}

	// getNewComment
	public DiaryCommentVO getNewComment() {
		return cdao.getNewComment();
	}

	// diary Comment delete

	public boolean deleteComment(int commId) {
		return cdao.deleteComment(commId);
	}

}
