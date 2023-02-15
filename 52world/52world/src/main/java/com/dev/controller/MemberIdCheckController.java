
package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberIdCheckController implements Controller {

   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      
      MemberService service = MemberService.getInstance();
      String name = req.getParameter("name"); //이름
   
      String year = req.getParameter("year"); //연
      String month =req.getParameter("month"); //월
      String day = req.getParameter("day"); //일
      String birth = year+"-"+month+"-"+day;
            
      String firstPhone = req.getParameter("firstPhone"); //앞3자리
      String secondPhone = req.getParameter("secondPhone"); //중간4자리
      String lastPhone = req.getParameter("lastPhone"); //마지막4자리
      String phone = firstPhone+"-"+secondPhone+"-"+lastPhone; //xxx-xxxx-xxxx 출력
      
      MemberVO vo = new MemberVO();
   
      vo.setName(name);
      vo.setBirth(birth);
      vo.setPhone(phone);      
      
     MemberVO result  = service.findId(name, birth, phone);      

      req.setAttribute("member", result);

      HttpUtil.forward(req, resp, "WEB-INF/jsp/login/idFindSuccess.jsp");
      
		/*
		 * RequestDispatcher rd = req.getRequestDispatcher("idFindSuccess.jsp");
		 * 
		 * try { rd.forward(req, resp); } catch (ServletException e) {
		 * 
		 * e.printStackTrace(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 */

   }
}