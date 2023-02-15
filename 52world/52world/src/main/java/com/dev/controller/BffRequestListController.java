package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.BffService;
import com.dev.vo.BffVO;
import com.dev.vo.MemberVO;

public class BffRequestListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 세션 정보를 담아서 보내기
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");

		System.out.println(member.getId()+"=로그인한 아이디입니다.");
		BffService service = BffService.getInstance();
		//List<BffVO> bffList = service.requestBffList(member.getId());

		//이름까지 가져오는거.. 안되면 28번줄 살려야함
		List<BffVO> bffList = service.getBffReqList(member.getId());
		
		req.setAttribute("list", bffList);
		
		System.out.println(bffList.toString()+"=이름 포함한bffList입니다");
		

		HttpUtil.forward(req, resp, "WEB-INF/jsp/home/bffRequestList.jsp");

	}

}