package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryVO;

public class WriteDiaryControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DiaryService service = DiaryService.getInstance();
		String homepeeId = req.getParameter("homepee_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");

		DiaryVO vo = new DiaryVO();

		vo.setHomepeeId(homepeeId);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);

		service.writeDiary(vo);

		// 요청 처리결과 뷰페이지 전송
		req.setAttribute("diary", vo);
		HttpUtil.forward(req, resp, "diaryDetails.do");
	}

}
