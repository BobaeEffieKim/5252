package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.DiaryService;

public class DiaryCommentDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		int id= Integer.parseInt(req.getParameter("id"));
		
		DiaryService service=DiaryService.getInstance();
		boolean isDeleted = service.deleteComment(id); //메소드, 함수 : 반환값, 매개변수
		
		//"{\"retCode\":\"Success\"}"
		try {
			if(isDeleted)
				resp.getWriter().print("{\"retCode\":\"Success\"}");
			else 
				resp.getWriter().print("{\"retCode\":\"Fail\"}");
		} catch (IOException e) {
		
			e.printStackTrace();
		}


	}

}
