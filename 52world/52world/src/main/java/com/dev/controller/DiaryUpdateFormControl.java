package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryVO;

public class DiaryUpdateFormControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// 포스트 아이디를 가져오고 와서 그 내용을 수정폼에 띄우고 싶은데 이렇게 하는게 아닌가요,,?

		  int postId = Integer.parseInt(req.getParameter("postId"));
		  System.out.println("수정할 post_id " + postId); 
		  String title =req.getParameter("title"); 
		  String content = req.getParameter("content");

		  req.setAttribute("postId", postId); 
		  req.setAttribute("title", title);
		  System.out.println("수정할 제목 " + title);

		  req.setAttribute("content", content); 
		  System.out.println("수정할 내용" + content);


		HttpUtil.forward(req, resp, "diary/diaryUpdate.tiles");




	}

}