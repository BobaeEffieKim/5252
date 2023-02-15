

package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryCommentVO;
import com.google.gson.Gson;

public class GalleryCommentController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/json;charset=UTF-8");
		
		GalleryCommentVO vo = new GalleryCommentVO();
		int postId = Integer.parseInt(req.getParameter("postId"));
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		vo.setPostId(postId);
		vo.setContent(content);
		vo.setWriter(writer);
		
		GalleryService service = GalleryService.getInstance();
		service.insetComment(vo);
		vo = service.getNewComment();
		
		String json = new Gson().toJson(vo);
		resp.getWriter().write(json);
		
		
	}

}

