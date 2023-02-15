package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.common.Controller;
import com.dev.service.MarketService;
import com.dev.vo.MemberVO;
import com.dev.vo.MusicVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MusicSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");

		String search = req.getParameter("searchVal");
		MarketService service = MarketService.getInstance();
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		String id = member.getId() ;

		List<MusicVO> vo = service.searchMusic(search, id);

		JSONArray jArray = new JSONArray();
		for (int i = 0; i < vo.size(); i++) {
			JSONObject jObj = new JSONObject();
			jObj.put("id", vo.get(i).getId());
			jObj.put("title", vo.get(i).getTitle());
			jObj.put("singer", vo.get(i).getSinger());
			jObj.put("price", vo.get(i).getPrice());
			jObj.put("src", vo.get(i).getSrc());
			jArray.add(jObj);
		}

		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(jArray));

	}

}
