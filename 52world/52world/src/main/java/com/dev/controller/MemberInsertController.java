package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.MemberService;
import com.dev.vo.DiaryFolderVO;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class MemberInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 입력. -> 뷰페이지
		MemberService service = MemberService.getInstance();

		String id = req.getParameter("id"); //아이디
		String password = req.getParameter("password"); //패스워드
		String name = req.getParameter("name"); //이름
		
		String year = req.getParameter("year"); //연
		String month =req.getParameter("month"); //월
		String day = req.getParameter("day"); //일
		String birth = year+"/"+month+"/"+day;
		String gender = req.getParameter("userGender"); //연/월/일 출력
		
		String emailId = req.getParameter("email_id"); //이메일 아이디
		System.out.println("이메일"+emailId);
		String emailDomain = req.getParameter("email_domain"); //이메일 도메인주소
		String email = emailId+"@"+emailDomain; //아이디@주소
		
		String firstPhone = req.getParameter("firstPhone"); //앞3자리
		String secondPhone = req.getParameter("secondPhone"); //중간4자리
		String lastPhone = req.getParameter("lastPhone"); //마지막4자리
		String phone = firstPhone+"-"+secondPhone+"-"+lastPhone; //xxx-xxxx-xxxx 출력
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setBirth(birth);
		vo.setGender(gender);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MiniHomepeeVO mvo = new MiniHomepeeVO();
		mvo.setHomepeeId(id);
		mvo.setName(id);
		mvo.setBackgroundColor("#a5d6dd");
		mvo.setMiniroom("/52world/asset/default_miniroom.jpg");
		
		GalleryFolderVO gvo = new GalleryFolderVO();
		gvo.setFolderName("사진첩");
		gvo.setHomepeeId(id);
		gvo.setSecret(0);
		
		DiaryFolderVO dvo = new DiaryFolderVO();
		dvo.setFolderName("다이어리");
		dvo.setHomepeeId(id);
		dvo.setSecret(0);
		
		
		service.addMember(vo, mvo, gvo, dvo);

		// 요청처리 결과 뷰 페이지 전송. vo를 memeber라는 값에 넣음
		req.setAttribute("member", vo);

		RequestDispatcher rd = req.getRequestDispatcher("memberLoginForm.do");
		
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
