package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.DiaryService;

public class DiaryDeleteControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DiaryService service = DiaryService.getInstance();

		//포스트 아이디를 가져오고
		int postId = Integer.parseInt(req.getParameter("postId"));
		System.out.println("삭제할 post_id " +postId);

		//삭제실행
		boolean isDeleted = service.deleteDiary(postId);
			resp.sendRedirect("diaryDetails.do");



	}

}