package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GuestBookService;
import com.dev.vo.GuestBookCommentVO;
import com.google.gson.Gson;

public class GuestBookCommentInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//ajax로 넘길땐 이렇게 해야함
		/* resp.setContentType("text/json; charset=utf-8"); */
	GuestBookService service = GuestBookService.getInstance();
		

	String content = req.getParameter("content");
	int postId = Integer.parseInt(req.getParameter("postId"));
	String writer = req.getParameter("writer");
	int commId = Integer.parseInt(req.getParameter("commId"));
	
	System.out.println("댓글 내용 : " +content);
	System.out.println("postId : "+postId);
	System.out.println("댓글 작성자 : "+writer);
	System.out.println("댓글 번호 "+commId);
	
	
	
	GuestBookCommentVO vo = new GuestBookCommentVO();
	
	
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setPostId(postId);
	vo.setCommId(commId);

	
	req.setAttribute("guestComm",vo);
	service.insertComment(vo);
	
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


