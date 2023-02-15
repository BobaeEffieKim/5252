package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GuestBookService;
import com.dev.vo.GuestProfileVO;

public class GusetBookSecretController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 포스트 아이디를 가져오고
		int postId = Integer.parseInt(req.getParameter("postId"));
		System.out.println("포스트번호" + postId);

		int secret = Integer.parseInt(req.getParameter("secret"));
		System.out.println("현재비밀상태" + secret);

		GuestProfileVO vo = new GuestProfileVO();

		if (secret == 1) {
			vo.setSecret(0);
			vo.setPostId(postId);
		} else {
			vo.setSecret(1);
			vo.setPostId(postId);
		}

		GuestBookService service = GuestBookService.getInstance();
		service.secretGuestBook(secret, postId);

		resp.sendRedirect("guestbook.do");

	}

}