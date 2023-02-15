package com.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class CheckPhoneAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");

		MemberService service = MemberService.getInstance();

		String phoneCheck = req.getParameter("phone");
		PrintWriter out = resp.getWriter();
		MemberVO vo = service.phoneCheck(phoneCheck);

		if (vo == null) {// 같은값이 없을때
			out.print("0");
		} else {
			out.print("1");
		}
	}
}