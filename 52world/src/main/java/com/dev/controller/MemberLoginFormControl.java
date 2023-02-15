package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.vo.MemberVO;

public class MemberLoginFormControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션의 로그인 정보 가져옴
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("member");

		if(vo == null) {
			// 로그인정보 없으면 로그인 폼으로 이동
			System.out.println("로그인화면으로 이동");
			HttpUtil.forward(req, resp, "WEB-INF/jsp/login/loginForm.jsp");
		} else {
			HttpUtil.forward(req, resp, "minihomepee.do");
		}
	}

}
