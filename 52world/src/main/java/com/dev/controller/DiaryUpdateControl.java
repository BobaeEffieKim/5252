package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.Criteria;
import com.dev.common.HttpUtil;
import com.dev.common.Page;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryVO;
import com.dev.vo.MiniHomepeeVO;

public class DiaryUpdateControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		DiaryService service = DiaryService.getInstance();

		int postId =Integer.parseInt(req.getParameter("postId"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");


		DiaryVO vo = new DiaryVO();

		vo.setPostId(postId);
		vo.setTitle(title);
		vo.setContent(content);

		req.setAttribute("diary", vo);
		service.updateDiary(vo);


		// 요청 처리결과 뷰페이지 전송

		HttpUtil.forward(req, resp, "diaryDetails.do");



	}

}