package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.DiaryService;
import com.dev.vo.MemberVO;

public class DiaryController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 다이어리 기능 만들어야 함 -> 리스트만 먼저 보이게 하고, 하단에 글쓰기 버튼 보이게 하기


				DiaryService service=DiaryService.getInstance();

				// 로그인 정보 가져오기
				HttpSession session = req.getSession();
				MemberVO member = (MemberVO) session.getAttribute("member");


				req.setAttribute("list", service.boardList(member.getId()));


				HttpUtil.forward(req, resp, "diary/diary.tiles");



	
	}

}
