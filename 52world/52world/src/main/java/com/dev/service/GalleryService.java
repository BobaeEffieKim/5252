

package com.dev.service;

import java.util.List;

import com.dev.common.Criteria;
import com.dev.dao.GalleryCommentDAO;
import com.dev.dao.GalleryDAO;
import com.dev.vo.GalleryCommentVO;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.GalleryVO;

public class GalleryService {

	private static GalleryService instance = new GalleryService();
	GalleryDAO gdao = new GalleryDAO();
	GalleryCommentDAO gcdao = new GalleryCommentDAO(); 

	private GalleryService() {
	}

	public static GalleryService getInstance() {
		return instance;
	}

	/*
	 * public List<GalleryVO> showGallery(){ return gdao.galleryList(); }
	 */

	// 갤러리 전체보기 리스트
	public List<GalleryVO> showGallery(String homepeeId, String folderName) {
		return gdao.galleryList(homepeeId, folderName);
	}

	// 갤러리에 사진추가
	public void insertGallery(GalleryVO vo) {
		gdao.insertGallery(vo);
	}

	// 사진게시정보 수정
	public void editGallery(GalleryVO vo) {
		gdao.updateGallery(vo);
	}

	// 사진 삭제
	public boolean delGallery(int postId) {
		return gdao.deleteGallery(postId);
	}

	// 게시번호로 사진 검색
	public GalleryVO searchById(int postId) {
		return gdao.searchById(postId);
	}

	//////////////////////////////////////////////////////

	// 폴더 생성
	public void makeGalleryFolder(GalleryFolderVO vo) {
		gdao.insertGalleryFolder(vo);
	}

	// 폴더 리스트 보여주기
	public List<GalleryFolderVO> showGalleryFolder() {
		return gdao.galleryFolderList();
	}

	// 폴더 이름 검색
	public GalleryFolderVO searchFolderName(String folderName) {
		return gdao.searchFName(folderName);
	}

	// 사용자별 폴더리스트 보기
	public List<GalleryFolderVO> showFolderByHId(String homepeeId) {
		return gdao.galleryFListByHId(homepeeId);
	}

	// 폴더 수정
	public void editGalleryFolder(String folderName, String editFolderName, int secret, String homepeeId) {
		gdao.updateGalleryFolder(folderName, editFolderName, secret, homepeeId);
	}

	// 폴더 안 사진들 이동
	public void moveGalleryFolder(String folderName, String editFolderName, String homepeeId) {
		gdao.moveGalleryFolder(folderName, editFolderName, homepeeId);
	}

	
	// 폴더 삭제
	   public void delGalleryFolder(GalleryFolderVO vo) {
	      gdao.deleteGalleryFolder(vo);
	      gdao.deleteGallery(vo);
	   }

	// 좋아요
	public void likeGallery(GalleryVO vo) {
		gdao.likes(vo);
	}

	// 페이징

	public List<GalleryVO> getListPaging(String homepeeId, Criteria cri, String fNm) {
		return gdao.GalleryPaging(homepeeId, cri, fNm);
	}

	/////////////////////////////////////////////////////////////////////////////////
	 // comment insert
	   public void insetComment(GalleryCommentVO vo) {
	      gcdao.insetComment(vo);
	   }
	   
	   // comment delete
	   public boolean deleteComment(int commId) {
	      return gcdao.deleteComment(commId);
	   }
	   
	   //  comment update
	   public void updateComment(GalleryCommentVO vo) {
	      gcdao.updateComment(vo);
	   }
	   
	   //  Comment List
	   
	   public List<GalleryCommentVO> getList(String pidList) {
	      return gcdao.getList(pidList); // 10건씩

	   }
	   
	   // getNewComment
	   public GalleryCommentVO getNewComment() {
	      return gcdao.getNewComment();
	   }

	
	
}

