package com.dev.service;

import java.util.List;

import com.dev.common.Criteria;
import com.dev.dao.GuestBookCommentDAO;
import com.dev.dao.GuestBookDAO;
import com.dev.vo.DiaryCommentVO;
import com.dev.vo.GuestBookCommentVO;
import com.dev.vo.GuestBookVO;
import com.dev.vo.GuestProfileVO;

public class GuestBookService {

	private static GuestBookService instance = new GuestBookService();
	GuestBookDAO dao = new GuestBookDAO();
	GuestBookCommentDAO cdao = new GuestBookCommentDAO();

	private GuestBookService() {
	}

	public static GuestBookService getInstance() {
		return instance;
	}

	// 방명록 쓰기
	public void writeGuestBook(GuestBookVO vo) {
		dao.writeGuestBook(vo);
	}

	// 방명록 리스트 보기
	public List<GuestProfileVO> listGuestBook(String homepeeId) {
		return dao.getList(homepeeId);
	}

	// 페이징

	public List<GuestProfileVO> getListPaging(String homepeeId, Criteria cri) {
		return dao.getGuestBookPaging(homepeeId, cri);
	}


	//방명록 삭제
	public boolean deleteGuestBook(int postId) {
		return dao.deleteGuestBook(postId);
	}
	
	//방명록 내용 수정
	public void updateGuestBook(String content,int postId) {
		dao.updateGuestBook(content, postId);
	}

	//비밀로 하기
	public void secretGuestBook(int secret,int postId) {
		dao.secretGuestBook(secret, postId);
	}
	

	//방명록 댓글 쓰기
	public void insertComment(GuestBookCommentVO cvo) {
		cdao.insertComment(cvo);
	}
	
	//방명록 댓글 수정
	public void updateComment(String content,int commId) {
		cdao.updateGuestBookComment(content, commId);
	}
	
	//방명록 댓글 삭제
	public boolean deleteComment(int commId) {
		return cdao.deleteGuestBookComment(commId);
	}
	
	//방명록 댓글 리스트 보기
	public List<GuestBookCommentVO> listGuestBookComment(String pidList){
		return cdao.commentList(pidList);
	}
	
	//새로 등록한 댓글 찾기
	public GuestBookCommentVO getNewComment() {
		return cdao.getNewComment();
	}
	
	
	//댓글 개수 세기
	public List<GuestBookCommentVO> commentCnt(String pidList) {
		return cdao.commentCnt(pidList); // 10건씩
	}
	
	
}