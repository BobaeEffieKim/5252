package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;

public class MemberLogoutControl implements Controller {

	// kbb) 로그아웃 컨트롤러

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		// 모든 세션 삭제
		session.invalidate();
		// 로그아웃하면 제일초기화면(로그인 메인으로 이동)
		 HttpUtil.forward(req, resp, "memberLoginForm.do");
	}

}