package com.dev.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryCommentVO;
import com.google.gson.Gson;

public class DiaryCommentController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		DiaryCommentVO vo = new DiaryCommentVO();
		int postId = Integer.parseInt(req.getParameter("postId"));
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		vo.setContent(content);
		vo.setPostId(postId);
		vo.setWriter(writer);
		
		DiaryService service = DiaryService.getInstance();
		service.insetComment(vo);
		vo = service.getNewComment();
		
		String json = new Gson().toJson(vo);
		resp.getWriter().write(json);

	}

}
