package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class SearchMemberController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");

		MemberService service = MemberService.getInstance();

		// 속성을 가져와 값이 있으면 연결되게

		List<MemberVO> vo = service.nameList(name);
		if (vo.size() == 0) {
			req.setAttribute("result", "검색된 정보가 없습니다.");
		}
		req.setAttribute("friend", vo);

		try {
			HttpUtil.forward(req, resp, "WEB-INF/jsp/home/searchMemberOutput.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}