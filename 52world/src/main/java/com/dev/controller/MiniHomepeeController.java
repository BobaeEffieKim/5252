package com.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.BffService;
import com.dev.service.BoardService;
import com.dev.service.MemberService;
import com.dev.vo.BffCommentVO;
import com.dev.vo.BffVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;
import com.dev.vo.NewPostVO;

public class MiniHomepeeController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberService service = MemberService.getInstance();
		BffService bservice = BffService.getInstance();

		MiniHomepeeVO homepee;
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		MemberVO login = (MemberVO) session.getAttribute("member");

		// 파라미터로 홈피주인 아이디 받음
		String id = req.getParameter("id");
		System.out.println("------------페이지이동------------");


		
		// 홈피주인 정보가 제대로 들어오면
		if (service.searchMember(id) != null) {
			// 그 정보로 홈피정보 설정해줌
			homepee = service.getMinihomeInfo(id);
			member = service.searchMember(homepee.getHomepeeId());
			
		
			

		} else {
			// 홈피정보 안들어옴 기존홈피정보로 연결
			System.out.println("홈피정보 안들어옴 기존 홈피정보로 연결");
			homepee = (MiniHomepeeVO) session.getAttribute("Homepee");

			// 기존홈피정보도 없으면 세션 로그인아이디로 홈피정보 설정
			if (homepee == null) {
				System.out.println("기존 홈피정보도 없음 로그인계정 홈피로 연결");
				// 로그인정보 가져오기
				member = (MemberVO) session.getAttribute("member");

				// 로그인정보없으면 로그인하러보냄
				if (member == null) {
					HttpUtil.forward(req, resp, "WEB-INF/jsp/login/loginForm.jsp");
					return;
				}
				homepee = service.getMinihomeInfo(member.getId());
			}
			// 있으면 그걸로 홈피정보 설정
			member = service.searchMember(homepee.getHomepeeId());
		}
		// 디비에서 멤버정보 갱신해옴
		member = service.searchMember(member.getId());
		System.out.println(member.getProfile());

		// 방문자수 카운트
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formatedNow = now.format(formatter);
		int today = Integer.parseInt(formatedNow);
		int homedate = homepee.getVisitToday()%1000000;
		// 오늘 방문자수 아직없는 경우
		if (homedate < today) {
			homepee.setVisitToday(0);
			homepee.setVisiter("");
			// 내홈피 아니면 방문자수 올라감
			if(!login.getId().equals(homepee.getHomepeeId())) {
				homepee.setVisitToday(today+1000000);
				homepee.setVisitTotal(homepee.getVisitTotal()+1);
				homepee.setVisiter(login.getId());
				service.visitMinihomepee(homepee);
				System.out.println("방문자 +1");
			}
		}else {
			// 내홈피 아니고
			if(!login.getId().equals(homepee.getHomepeeId())) {
				// 첫방문이면
				if(!homepee.getVisiter().contains(login.getId())) {
					homepee.setVisitToday(homepee.getVisitToday()+1000000);
					homepee.setVisitTotal(homepee.getVisitTotal()+1);
					homepee.setVisiter(homepee.getVisiter()+" "+login.getId());
					service.visitMinihomepee(homepee);
					System.out.println("방문자 +1");
				}
			}
		}
		
		

		// 일촌리스트 가져오기
		List<BffVO> list = bservice.getBffList(login.getId());
		session.setAttribute("bffList", list);
		for (BffVO b : list) {
			if (b.getReqId().equals(homepee.getHomepeeId())) {
				session.setAttribute("isFriend", b.getReqName());
				break;
			}
			session.setAttribute("isFriend", null);
		}
		// 최신글 가져오기
		List<NewPostVO> newList = BoardService.getInstance().getNew(homepee.getHomepeeId());
		System.out.println(newList.size());
		req.setAttribute("newList", newList);

		// 게시판별로 글수 가져오기
		int[] postNumber = BoardService.getInstance().getPostNumber(homepee.getHomepeeId());
		req.setAttribute("postNumber", postNumber);

		// 일촌평 가져오기
		List<BffCommentVO> commentList = bservice.getCommentList(member.getId());
		req.setAttribute("commentList", commentList);

		// 홈피정보도 그냥 세션에 담아버리자
		session.setAttribute("Homepee", homepee);
		session.setAttribute("homeOwner", member);
		System.out.println("login = " + login.getId());
		System.out.println("homepee = " + homepee.getHomepeeId());
		System.out.println("homeOwner = " + member.getId());
		HttpUtil.forward(req, resp, "home/home.tiles");

	}

}
