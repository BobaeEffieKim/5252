package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.dao.BffDAO;
import com.dev.service.BffService;

public class BffAnswerOutputController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String Answer = req.getParameter("answer");
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(Answer + "값이넘어오는지");
		System.out.println(id + "값이넘어오는지");

		BffService service = BffService.getInstance();

		if (Answer.equals("Y")) {
			service.yesBff(id);
			resp.getWriter().print("Y");
		} else if (Answer.equals("N")) {
			service.noBff(id);
			resp.getWriter().print("N");
		} else {
			System.out.println("에러발생");

		}
		
		// HttpUtil.forward(req, resp, "minihomepee.do");

	}

}