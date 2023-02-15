package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.dao.GuestBookCommentDAO;
import com.dev.service.GuestBookService;
import com.dev.vo.GuestBookCommentVO;
import com.google.gson.Gson;

public class GuestBookCommentController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		GuestBookCommentVO vo = new GuestBookCommentVO();

		int postId = Integer.parseInt(req.getParameter("postId"));
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		

		
		vo.setContent(content);
		vo.setPostId(postId);
		vo.setWriter(writer);

	
		GuestBookService service = GuestBookService.getInstance();
		
		service.insertComment(vo);
		
		vo =service.getNewComment();
		
		String json = new Gson().toJson(vo);
		resp.getWriter().write(json);
		
		
	}

}
