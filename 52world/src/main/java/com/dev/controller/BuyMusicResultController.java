package com.dev.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MarketService;
import com.dev.vo.ItemsVO;
import com.dev.vo.MemberVO;

public class BuyMusicResultController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		MarketService service = MarketService.getInstance();

		String list = req.getParameter("id");

		StringTokenizer st = new StringTokenizer(list, "_");
		while (st.hasMoreTokens()) {
			int id = Integer.parseInt(st.nextToken());
			ItemsVO vo = new ItemsVO();
			vo.setBuyerId(member.getId());
			vo.setCategory("music");
			vo.setItemId(id);
			service.buyMusic(vo, 3);
		}
		HttpUtil.forward(req, resp, "WEB-INF/jsp/market/buyMusicOutput.jsp");

	}

}
