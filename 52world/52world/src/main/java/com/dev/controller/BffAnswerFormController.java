package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;

public class BffAnswerFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = req.getParameter("id");
		String reqId = req.getParameter("reqId");
		String reqName = req.getParameter("reqName");
		String applyId = req.getParameter("applyId");
		String applyName = req.getParameter("applyName");
		String msg = req.getParameter("msg");
		String realName= req.getParameter("realName");

		System.out.println(id);
		req.setAttribute("id", id);
		req.setAttribute("reqId", reqId);
		req.setAttribute("reqName", reqName);
		req.setAttribute("applyId", applyId);
		req.setAttribute("applyName", applyName);
		req.setAttribute("msg", msg);
		req.setAttribute("realName", realName);

		HttpUtil.forward(req, resp, "WEB-INF/jsp/home/bffAnswer.jsp");
	}

}