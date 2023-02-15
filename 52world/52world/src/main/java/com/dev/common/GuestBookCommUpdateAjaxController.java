package com.dev.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.GuestBookService;

public class GuestBookCommUpdateAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");

		int commId = Integer.parseInt(req.getParameter("commId"));
		String content = req.getParameter("content");
		
		GuestBookService gbservice = GuestBookService.getInstance();
		gbservice.updateComment(content, commId);

	}

}

	
