package com.dev.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.GuestBookService;

public class GusetBookCommDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		GuestBookService service = GuestBookService.getInstance();
	
		int commId = Integer.parseInt(req.getParameter("commId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int amount = Integer.parseInt(req.getParameter("amount"));
	
		
		System.out.println("삭제할 comm_id "+ commId);
		
		//삭제실행
		boolean isDeleted = service.deleteComment(commId);
		
		resp.sendRedirect("guestbook.do?pageNum="+pageNum+"&amount="+amount);
	}

}
