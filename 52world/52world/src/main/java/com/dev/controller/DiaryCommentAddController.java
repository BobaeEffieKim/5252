package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryCommentVO;

public class DiaryCommentAddController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		DiaryService service=DiaryService.getInstance();
		
		//넘어와야 하는 값 포스트 아이디(해당 코멘트의 번호) 내용, 글쓴이
		int postId = Integer.parseInt(req.getParameter("postId"));
		String content=req.getParameter("content");
		String writer=req.getParameter("writer");
		
		DiaryCommentVO vo = new DiaryCommentVO();
		
		vo.setPostId(postId);
		vo.setContent(content);
		vo.setWriter(writer);
		
		req.setAttribute("diary", vo);
		service.updateComment(vo);
		
		
		HttpUtil.forward(req, resp, "diaryDetails.do");
		
	}

}
