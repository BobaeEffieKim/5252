package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class MyPageController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String gender = req.getParameter("gender");
		String emain_Id = req.getParameter("email_Id");
		String email_domain = req.getParameter("email_domain");
		String firstPhone = req.getParameter("firstPhone");
		String secondPhone = req.getParameter("secondPhone");
		String lastPhone = req.getParameter("lastPhone");

		MemberService ms = MemberService.getInstance();
		MemberVO member = ms.getMember(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(year+"/"+month+"/"+day);
		member.setGender(gender);
		member.setEmail(emain_Id+"@"+email_domain);
		member.setPhone(firstPhone+"-"+secondPhone+"-"+lastPhone);
		ms.updateInfo(member);
		
		
		String homepeeName = req.getParameter("homepeeName");
		String backColor = req.getParameter("backColor");
		String tabColor = req.getParameter("tabColor");
		MiniHomepeeVO home = ms.getMinihomeInfo(id);
		home.setName(homepeeName);
		home.setBackgroundColor(backColor);
		home.setTabColor(tabColor);
		ms.updateHomepee(home);
		
		
		
		HttpUtil.forward(req, resp, "minihomepee.do");
	}

}
