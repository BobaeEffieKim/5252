package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MarketService;
import com.dev.vo.ItemsVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniroomVO;

public class BuyMiniroomController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MarketService service = MarketService.getInstance();
		int id = Integer.parseInt(req.getParameter("rmSelect"));
		int price = id%100;
		id = id/100;
		System.out.println(id+" 가격 : "+price);
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");

		ItemsVO vo = new ItemsVO();
		vo.setBuyerId(member.getId());
		vo.setCategory("miniroom");
		vo.setItemId(id);

		service.buyMiniroom(vo, price);

		HttpUtil.forward(req, resp, "market.do");

	}

}
