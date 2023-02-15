package com.dev.service;

import java.util.List;

import com.dev.dao.DiaryDAO;
import com.dev.dao.GalleryDAO;
import com.dev.dao.ItemsDAO;
import com.dev.dao.MemberDAO;
import com.dev.dao.MiniHomepeeDAO;
import com.dev.vo.DiaryFolderVO;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class MemberService {

	MemberDAO dao = new MemberDAO();
	MiniHomepeeDAO mdao = new MiniHomepeeDAO();
	ItemsDAO idao = new ItemsDAO();
	GalleryDAO gdao = new GalleryDAO();
	DiaryDAO ddao = new DiaryDAO();

	private static MemberService instance = null;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}

	public MemberVO searchMember(String id) {
		return dao.searchMember(id);
	}

	// 미니홈피에 정보 가져오기
	public MiniHomepeeVO getMinihomeInfo(String id) {
		return mdao.selectHomepee(id);
	}

	// 배경음악 설정
	public void setMinihomeBgm(String src, String id) {
		mdao.setMinihomeBgm(src, id);
	}

	// 미니룸 설정
	public void setMinihomeMiniroom(String src, String id) {
		mdao.setMinihomeMiniroom(src, id);
	}

	// 프사바꾸기
	public void changeProfile(MemberVO vo) {
		dao.updateProfile(vo);
	}

	// 회원가입
	// nhj
	public void addMember(MemberVO vo, MiniHomepeeVO mvo, GalleryFolderVO gvo, DiaryFolderVO dvo) {
		dao.insertMember(vo);
		mdao.insertHomepee(mvo);
		idao.defaultMiniroom(vo.getId());
		gdao.insertGalleryFolder(gvo);
		ddao.insertGalleryFolder(dvo);
	}

	public int tryLogin(String id, String password) {
		return dao.loginCheck(id, password);
	}

	// nhj

	// 아이디 중복체크
//	public boolean doubleIdCheck(String id) {
//		return dao.checkId(id);
//	}

	// 회원목록
	public List<MemberVO> memberList() {
		return dao.getList();
	}

	// 회원 조회
	public MemberVO getMember(String id) {
		return dao.searchMember(id);
	}

	// 회원정보삭제
	public boolean eraseMember(String id) {
		return dao.DeleteMember(id);
	}

	// 아이디 중복체크
	public MemberVO idCheck(String id) {
		return dao.selectId(id);
	}

	// 폰번호 중복체크
	public MemberVO phoneCheck(String phone) {
		return dao.selectPhone(phone);
	}

	// sek

	// 이름검색 (친구찾기)
	public List<MemberVO> nameList(String name) {
		return dao.searchNameList(name);
	}

	// 회원정보 수정
	public void updateInfo(MemberVO member) {
		dao.updateInfo(member);
	}

	// 홈피정보 수정
	public void updateHomepee(MiniHomepeeVO home) {
		mdao.setMinihome(home);
	}

	// 홈피 방문자 등록
	public void visitMinihomepee(MiniHomepeeVO home) {
		mdao.visitMinihomepee(home);
	}

	
	///////////////////////////// kbb 아이디 비번 찾기
	//이름, 생일, 전화번호로 id 검색
	public MemberVO findId(String name, String birth, String phone) {
		return dao.lookUpId(name, birth, phone);
	}
	
	//이름, 아이디, 전화번호로 pw 검색 
	public MemberVO findPw(String name, String id, String phone) {
		return dao.lookUpPassword(name, id, phone);
	}
	
	//pw 재설정
	public void pwReset(MemberVO vo) {
		dao.updatePw(vo);
	}
	
	
}