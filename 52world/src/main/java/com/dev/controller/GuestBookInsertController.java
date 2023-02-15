package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.service.GuestBookService;
import com.dev.vo.GuestBookVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class GuestBookInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GuestBookService service = GuestBookService.getInstance();

		String content = req.getParameter("content");//
		int secret = Integer.parseInt(req.getParameter("only"));
		
		System.out.println(secret);

		HttpSession session = req.getSession();

		// 홈피 아이디를 가져와야함(homepeeId)
		MiniHomepeeVO mhvo = (MiniHomepeeVO) session.getAttribute("Homepee");

		// 작성자를 가져와야함(id)
		MemberVO mvo = (MemberVO) session.getAttribute("member");

		// 방명록을 넣어야함
		GuestBookVO gvo = new GuestBookVO();
		// 홈피주인아이디
		gvo.setHomepeeId(mhvo.getHomepeeId());
		System.out.println("홈피주인아이디 : " + mhvo.getHomepeeId());
		// 작성자아이디
		gvo.setWriter(mvo.getId());
		System.out.println("작성자아이디 : " + mvo.getId());
		// 내용물
		
		
		gvo.setContent(content);
		System.out.println("내용 : " + content);
		
		// 비밀
		gvo.setSecret(secret);
		System.out.println("비밀여부 : " + secret);
		
		gvo.setProfile(mvo.getProfile());
		System.out.println("작성자 프로필  : " + mvo.getProfile());

		service.writeGuestBook(gvo);

		req.setAttribute("guestBook", gvo);

		//탭을 누른것과 같게 넘어감
		RequestDispatcher rd = req.getRequestDispatcher("/guestbook.do");

		try {
			rd.forward(req, resp);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
