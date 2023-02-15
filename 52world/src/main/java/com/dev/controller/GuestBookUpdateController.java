package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.GuestBookService;

public class GuestBookUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/json;charset=utf-8");

		int postId = Integer.parseInt(req.getParameter("postId"));
		String content = req.getParameter("content");
		
		GuestBookService gbservice = GuestBookService.getInstance();
		gbservice.updateGuestBook(content, postId);

	}

}