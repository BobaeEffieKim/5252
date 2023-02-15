package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.BffService;
import com.dev.vo.BffVO;

public class BffAnswerController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		BffService service=BffService.getInstance();
		
		int id= Integer.parseInt(req.getParameter("id"));
		String reqId = req.getParameter("reqId");
		String reqName=req.getParameter("reqName");
		String applyId=req.getParameter("applyId");
		String applyName=req.getParameter("applyName");
		
	
		
		BffVO vo = new BffVO();
		
		vo.setId(id);
		vo.setReqId(reqId);
		vo.setReqName(reqName);
		vo.setApplyId(applyId);
		vo.setApplyName(applyName);
		
		service.requestBff(vo);
		
		//요청 처리결과 뷰페이지 전송
		req.setAttribute("bff", vo);
		HttpUtil.forward(req, resp, "WEB-INF/jsp/home/bffAnswerOutput.jsp");
	}
	}


