package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.service.DiaryService;
import com.dev.service.MemberService;
import com.dev.vo.DiaryCommentVO;
import com.dev.vo.MemberVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DiaryJsonController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		resp.setContentType("text/json;charset=utf-8");
//		
//		DiaryService service=DiaryService.getInstance();
//		int postId=Integer.parseInt(req.getParameter("postId"));
//		List<DiaryCommentVO> diaryComment=service.getList(postId);
//		System.out.println(postId+"+json컨트롤러에서 확인하는 데이터");
//		
//		String json="[{\"postId\":2,\"content\":\"something\",\"writer\":\"somebody\"}, {\"postId\":3,\"content\":\"anything\",\"writer\":\"who\"}]";
//		
//		JsonArray jary= new JsonArray();
//		for(DiaryCommentVO vo : diaryComment) {
//			JsonObject jobj= new JsonObject();
//			
//			jobj.addProperty("postId", vo.getPostId()); // jobj.addProperty(문자열,문자열) 형태
//			jobj.addProperty("content", vo.getContent());
//			jobj.addProperty("writer", vo.getWriter());
//			
//			
//			//jary 추가
//			jary.add(jobj);
//		}
//		
//		try {
//			resp.getWriter().print(json);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	}

}


