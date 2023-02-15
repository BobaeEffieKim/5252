package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.BffService;
import com.dev.vo.BffCommentVO;

public class BffCommentInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		BffService service = BffService.getInstance();

		String homepeeId = req.getParameter("homepee_id");
		String content = req.getParameter("content");
		String bffName = req.getParameter("bffName");
		String writer = req.getParameter("writer");
		

		System.out.println(bffName);


		BffCommentVO vo = new BffCommentVO();

		vo.setHomepeeId(homepeeId);
		vo.setContent(content);
		vo.setBffName(bffName);
		vo.setWriter(writer);

		service.insertComment(vo);

		// 요청 처리결과 뷰페이지 전송
		req.setAttribute("bffComment", vo);
		HttpUtil.forward(req, resp, "minihomepee.do");
	}


	}
