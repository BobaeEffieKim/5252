package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.BffService;
import com.dev.vo.BffCommentVO;

public class BffCommentDeleteAjaxController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	// TODO 삭제처리 -> json 반환
		
		resp.setContentType("text/json;charset=utf-8");
		int commId= Integer.parseInt(req.getParameter("delId"));
		System.out.println(commId);
		BffService service=BffService.getInstance();
		boolean isDeleted = service.deleteComment(commId); //메소드, 함수 : 반환값, 매개변수
		
		//"{\"retCode\":\"Success\"}"
//		try {
//			if(isDeleted)
//				resp.getWriter().print("{\"retCode\":\"Success\"}");
//			else 
//				resp.getWriter().print("{\"retCode\":\"Fail\"}");
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}

	}
}

