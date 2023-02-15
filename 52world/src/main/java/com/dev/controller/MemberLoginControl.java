package com.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberLoginControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		MemberService service = MemberService.getInstance();
		HttpSession session = req.getSession();
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		MemberVO vo = service.searchMember(id);
		
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		
		if(vo == null) {
			//아이디가 없으면 존재하지않는 아이디라고 알려주기
			writer.println("<script type='text/javascript'>");
			writer.println("alert('존재하지 않는 아이디입니다. ')");
			//writer.println("history.back()");
			writer.println("location.href='memberLoginForm.do'");
			writer.println("</script>");
			
		} else if(!password.equals(vo.getPassword())) {
			//비번이 맞지 않을때 알려주기
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호가 맞지않습니다. ')");
			//writer.println("history.back()");
			writer.println("location.href='memberLoginForm.do'");
			writer.println("</script>");

		} else {
			//로그인 성공 시 미니홈피로 이동
			session.setAttribute("member", vo);
			
			writer.println("<script type='text/javascript'>");
			writer.println("alert('로그인 되었습니다. ')");
			writer.println("location.href='minihomepee.do'");
			/* HttpUtil.forward(req, resp, "minihomepee.do"); */
			writer.println("</script>");
		}
	}

}
