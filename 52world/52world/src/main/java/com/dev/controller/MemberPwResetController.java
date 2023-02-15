package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberPwResetController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberService service = MemberService.getInstance();

	
		String password = req.getParameter("password");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		System.out.println("++++++++++++");
		System.out.println(password);
		System.out.println(id);
		System.out.println(name);
		System.out.println(phone);
		MemberVO vo = new MemberVO();
		vo.setPassword(password);
		vo.setId(id);
		vo.setName(name);
		vo.setPhone(phone);

		service.pwReset(vo);
		
		
		HttpUtil.forward(req, resp, "memberLoginForm.do");
	}

}
