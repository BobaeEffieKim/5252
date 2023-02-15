package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberPwCheckController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	      MemberService service = MemberService.getInstance();
	   
	      String id = req.getParameter("id"); //아이디
	      
	      String name = req.getParameter("name"); //이름

	      String firstPhone = req.getParameter("firstPhone"); //앞3자리
	      String secondPhone = req.getParameter("secondPhone"); //중간4자리
	      String lastPhone = req.getParameter("lastPhone"); //마지막4자리
	      String phone = firstPhone+"-"+secondPhone+"-"+lastPhone; //xxx-xxxx-xxxx 출력
	      System.out.println("찾기화면");
			System.out.println(id);
			System.out.println(name);
			System.out.println(phone);
	      MemberVO vo = new MemberVO();
	      
	      vo.setId(id);
	      vo.setName(name);
	      vo.setPhone(phone);
	      
	      MemberVO result = service.findPw(name, id, phone);

	      req.setAttribute("member", result);
	      
	      HttpUtil.forward(req, resp, "WEB-INF/jsp/login/pwFindSuccess.jsp");
	}

}
