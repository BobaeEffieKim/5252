package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.GuestBookService;

public class GuestBookDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GuestBookService service = GuestBookService.getInstance();

		//포스트 아이디를 가져오고
		int postId = Integer.parseInt(req.getParameter("postId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		
		System.out.println("삭제할 post_id " +postId);

		//삭제실행
		boolean isDeleted = service.deleteGuestBook(postId);
			resp.sendRedirect("guestbook.do?pageNum="+pageNum+"&amount="+amount);


	}
}

