package com.dev.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.controller.BffAnswerController;
import com.dev.controller.BffAnswerFormController;
import com.dev.controller.BffAnswerOutputController;
import com.dev.controller.BffCommentDeleteAjaxController;
import com.dev.controller.BffCommentInsertController;
import com.dev.controller.BffRequestController;
import com.dev.controller.BffRequestFormController;
import com.dev.controller.BffRequestListController;
import com.dev.controller.BuyMiniroomController;
import com.dev.controller.BuyMusicController;
import com.dev.controller.BuyMusicResultController;
import com.dev.controller.CheckIdAjaxController;
import com.dev.controller.CheckPhoneAjaxController;
import com.dev.controller.DiaryCommentAddController;
import com.dev.controller.DiaryCommentCntController;
import com.dev.controller.DiaryCommentController;
import com.dev.controller.DiaryCommentDeleteAjaxController;
import com.dev.controller.DiaryCommentDeleteController;
import com.dev.controller.DiaryDeleteControl;
import com.dev.controller.DiaryDetailsControl;
import com.dev.controller.DiaryJsonController;
import com.dev.controller.DiaryListPagingControl;
import com.dev.controller.DiaryUpdateControl;
import com.dev.controller.DiaryUpdateFormControl;
import com.dev.controller.EditProfileController;
import com.dev.controller.GalleryAddController;
import com.dev.controller.GalleryAddFormController;
import com.dev.controller.GalleryCommentController;
import com.dev.controller.GalleryCommentDeleteAjaxController;
import com.dev.controller.GalleryDeleteController;
import com.dev.controller.GalleryDeleteFolderController;
import com.dev.controller.GalleryDeleteFolderFormController;
import com.dev.controller.GalleryLikeController;
import com.dev.controller.GalleryListController;
import com.dev.controller.GalleryMakeFolderController;
import com.dev.controller.GalleryMakeFolderFormController;
import com.dev.controller.GalleryUpdateController;
import com.dev.controller.GalleryUpdateFolderController;
import com.dev.controller.GalleryUpdateFolderFormController;
import com.dev.controller.GalleryUpdateFormController;
import com.dev.controller.GuestBookCommentController;
import com.dev.controller.GuestBookCommentInsertController;
import com.dev.controller.GuestBookDeleteController;
import com.dev.controller.GuestBookInsertController;
import com.dev.controller.GuestBookUpdateController;
import com.dev.controller.GuestbookController;
import com.dev.controller.GuestbookUpdateControl;
import com.dev.controller.GusetBookSecretController;
import com.dev.controller.MarketController;
import com.dev.controller.MemberIdCheckController;
import com.dev.controller.MemberIdCheckFormController;
import com.dev.controller.MemberIdFindAjaxController;
import com.dev.controller.MemberInsertController;
import com.dev.controller.MemberInsertFormController;
import com.dev.controller.MemberLoginControl;
import com.dev.controller.MemberLoginFormControl;
import com.dev.controller.MemberLogoutControl;
import com.dev.controller.MemberPwCheckController;
import com.dev.controller.MemberPwCheckFormController;
import com.dev.controller.MemberPwResetController;
import com.dev.controller.MiniHomepeeController;
import com.dev.controller.MusicSearchController;
import com.dev.controller.MyPageController;
import com.dev.controller.ProfileController;
import com.dev.controller.SearchMemberController;
import com.dev.controller.SearchMemberFormController;
import com.dev.controller.SetMiniroomController;
import com.dev.controller.SetMusicController;
import com.dev.controller.SettingController;
import com.dev.controller.WriteDiaryControl;
import com.dev.controller.WriteDiaryFormControl;

public class FrontController extends HttpServlet {

	String enc;
	Map<String, Controller> mappings;

	@Override
	public void init(ServletConfig config) throws ServletException {
		enc = config.getInitParameter("charset");
		mappings = new HashMap<>();

		// miniHomepee 첫화면에서 동작 - lgh
		mappings.put("/minihomepee.do", new MiniHomepeeController()); // 미니홈피 첫화면 출력
		mappings.put("/editProfile.do", new EditProfileController()); // 프로필변경
		mappings.put("/profile.do", new ProfileController()); // 프로필변경

		// kbb) logIn & logOut
		mappings.put("/memberLoginForm.do", new MemberLoginFormControl()); // 로그인 화면
		mappings.put("/memberLogin.do", new MemberLoginControl()); // 로그인 처리
		mappings.put("/memberLogout.do", new MemberLogoutControl()); // 로그아웃 처리
		
		// kbb) id Check
		mappings.put("/memberIdCheckForm.do", new MemberIdCheckFormController()); //id체크폼
		mappings.put("/memberIdCheck.do", new MemberIdCheckController()); //id처리
		mappings.put("/memberIdfindAjax.do", new MemberIdFindAjaxController()); // 아이디 중복체크
		// kbb) pw Check
		mappings.put("/memberPwCheckForm.do", new MemberPwCheckFormController()); // pw체크폼
		mappings.put("/memberPwCheck.do", new MemberPwCheckController()); // pw 처리
		mappings.put("/memberPwReset.do", new MemberPwResetController()); // pw 처리

		// SEK 친구찾기 (이름로검색)
		mappings.put("/searchMemberForm.do", new SearchMemberFormController()); // 메인-친구찾기FORM
		mappings.put("/searchMember.do", new SearchMemberController()); //친구찾기 FORM 내용 처리

		
		// SEK 일촌신청
		mappings.put("/bffRequsetForm.do", new BffRequestFormController()); //일촌신청 요청FORM
		mappings.put("/bffRequest.do", new BffRequestController());//일촌신청 처리

		// SEK 일촌신청 수락
		mappings.put("/bffRequestList.do", new BffRequestListController()); //일촌신청들어온 리스트로 넘겨줌
		mappings.put("/bffAnswerForm.do", new BffAnswerFormController()); //일촌신청 리스트 중 한가지 항목
		mappings.put("/bffAnswer.do", new BffAnswerController()); //일촌 수락 처리
		mappings.put("/bffAnswerOutput.do", new BffAnswerOutputController()); //일촌 처리결과 출력

		// SEK 일촌평
		mappings.put("/bffCommentInsert.do", new BffCommentInsertController()); //일촌평 쓰기
		mappings.put("/bffCommentDeleteAjax.do", new BffCommentDeleteAjaxController());  //일촌평 삭제 아작스
		
	//	mappings.put("/bffCommentUpdateForm.do", new BffCommentUpdateFormController()); ▶ 업는 기능 삭제필요★★★★
	//	mappings.put("/bffCommentUpdate.do", new BffCommentUpdateController()); ▶ 업는 기능 삭제필요★★★★


		// SEK 다이어리 -> 최근 게시물로 보이게 수정보완 필요
		mappings.put("/writeDiaryForm.do", new WriteDiaryFormControl()); // 다이어리작성 등록처리.
		mappings.put("/writeDiary.do", new WriteDiaryControl()); // 다이어리작성 등록처리.
		mappings.put("/diaryListPaging.do", new DiaryListPagingControl()); // 페이징 처리 게시글

		mappings.put("/diaryDetails.do", new DiaryDetailsControl()); // <- 다이어리 첫화면으로 사용★★★★★★★

		mappings.put("/diaryUpdateForm.do", new DiaryUpdateFormControl()); // 다이어리 수정 폼
		mappings.put("/diaryUpdate.do", new DiaryUpdateControl()); // 다이어리 수정


		mappings.put("/diaryDelete.do",new DiaryDeleteControl()); //다이어리 삭제
		
		
		// SEK 다이어리 코멘트 컨트롤러 
		mappings.put("/addDiaryComment.do", new DiaryCommentAddController()); //다이어리 게시글 별 댓글 추가 화면

		mappings.put("/diaryCommentDeleteAjax.do", new DiaryCommentDeleteAjaxController());

		// SEK 다이어리 코멘트 아작스중..
		mappings.put("/removeDiaryCommentAjax.do", new DiaryCommentDeleteController());
		mappings.put("/DiaryJson.do", new DiaryJsonController());

		mappings.put("/diaryComment.do", new DiaryCommentController());  //아작스로 추가되는 댓글 컨트롤러
		mappings.put("/diaryCommentCnt.do", new DiaryCommentCntController()); //포스트 별 다이어리 코멘트 갯수 보여주는 컨트롤러


		// 회원가입
		mappings.put("/clauseAgreementForm.do", new ClauseAgreementFormController());//약관동의		
		mappings.put("/memberInsertForm.do", new MemberInsertFormController());// 회원가입페이지로
		mappings.put("/memberInsert.do", new MemberInsertController()); // 회원가입
		mappings.put("/idcheckAjax.do", new CheckIdAjaxController()); // 아이디 중복체크
		mappings.put("/phoneCheckAjax.do", new CheckPhoneAjaxController());// 폰번호 중복체크

		// kbb) 사진첩
		mappings.put("/galleryAddForm.do", new GalleryAddFormController()); // 새로운 사진 등록 화면
		mappings.put("/galleryAdd.do", new GalleryAddController()); // 사진등록 처리
		mappings.put("/galleryUpdateForm.do", new GalleryUpdateFormController()); // 사진수정 화면
		mappings.put("/galleryUpdate.do", new GalleryUpdateController()); // 사진수정 처리
		mappings.put("/galleryDelete.do", new GalleryDeleteController()); // 사진삭제 처리
		mappings.put("/galleryList.do", new GalleryListController()); // 사진 리스트
		mappings.put("/galleryLike.do", new GalleryLikeController()); // 좋아요

		// kbb) 사진첩 폴더
		mappings.put("/galleryMakeFolderForm.do", new GalleryMakeFolderFormController()); // 폴더 생성폼
		mappings.put("/galleryMakeFolder.do", new GalleryMakeFolderController()); // 폴더 생성처리
		mappings.put("/galleryUpdateFolderForm.do", new GalleryUpdateFolderFormController()); // 폴더수정폼
		mappings.put("/galleryUpdateFolder.do", new GalleryUpdateFolderController()); // 폴더수정처리
		mappings.put("/galleryDeleteFolderForm.do", new GalleryDeleteFolderFormController()); // 폴더삭제폼
		mappings.put("/galleryDeleteFolder.do", new GalleryDeleteFolderController()); // 폴더삭제처리

		// kbb) 사진첩 댓글
		mappings.put("/galleryComment.do", new GalleryCommentController());	//아작스 댓글추가 컨트롤러
		mappings.put("/galleryCommentDeleteAjax.do", new GalleryCommentDeleteAjaxController());

		// 방명록
		mappings.put("/guestbook.do", new GuestbookController()); // 방명록 첫화면이 보임
		mappings.put("/guestBookInsert.do", new GuestBookInsertController()); // 실제 값이 입력됨
		/*
		 * mappings.put("/guestBookList.do", new GuestBookListController());
		 * mappings.put("/guestBookListPaging.do", new GuestBookListPagingController());
		 */
		mappings.put("/gusetBookDelete.do", new GuestBookDeleteController()); // 방명록 삭제
		mappings.put("/guestBookUpdate.do", new GuestBookUpdateController()); // 방명록 수정
		mappings.put("/guestBookSecret.do", new GusetBookSecretController()); // 방명록 비밀로하기
		/*
		 * mappings.put("/guestBookCommentInsert.do", new
		 * GuestBookCommentInsertController()); // 방명록 댓글 입력하기
		 */		
		
		mappings.put("/guestBookComment.do", new GuestBookCommentController());// 방명록 댓글
		/*
		 * mappings.put("/gusetBookCommDelete.do", new GusetBookCommDeleteController());
		 * //방명록 댓글 삭제
		 */		
		mappings.put("/guestCommentDeleteAjax.do", new GuestCommentDeleteAjaxController()); //방명록 댓글 삭제
		 mappings.put("/guestBookCommUpdate.do", new GuestBookCommUpdateAjaxController());	//방명록 댓글 수정	
		
		 
		 // 마켓
		mappings.put("/market.do", new MarketController()); // 마켓 첫화면
		mappings.put("/musicSearch.do", new MusicSearchController()); // 검색 리스트 보여줌
		mappings.put("/buyMusic.do", new BuyMusicController()); // 구매확인창
		mappings.put("/buyMusicResult.do", new BuyMusicResultController()); // 구매처리완료
		mappings.put("/buyMiniroom.do", new BuyMiniroomController()); // 미니룸 구매처리
		mappings.put("/setMusic.do", new SetMusicController()); // 배경음악 설정
		mappings.put("/setMiniroom.do", new SetMiniroomController()); // 미니룸 설정

		// 설정
		mappings.put("/setting.do", new SettingController()); // 설정 첫화면
		mappings.put("/mypage.do", new MyPageController());

		// ajax 연습 방명록 업데이트인가?
		mappings.put("/guestbookUpdate.do", new GuestbookUpdateControl());

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(enc);
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = uri.substring(contextPath.length());
		System.out.println(path);
		mappings.get(path).execute(req, resp);

	}
}
