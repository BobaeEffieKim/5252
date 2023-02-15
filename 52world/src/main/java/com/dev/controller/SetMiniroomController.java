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

public class SetMiniroomController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String src = req.getParameter("src");
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");

		MemberService service = MemberService.getInstance();
		service.setMinihomeMiniroom(src, member.getId());
		HttpUtil.forward(req, resp, "minihomepee.do?id="+member.getId());
	}

}
