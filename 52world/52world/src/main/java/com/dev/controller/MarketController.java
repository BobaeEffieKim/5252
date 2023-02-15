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
import com.dev.common.HttpUtil;
import com.dev.service.MarketService;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniroomVO;
import com.dev.vo.MusicVO;

public class MarketController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 마켓 기능 만들어야 함 
		MarketService market = MarketService.getInstance();
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		String id = member.getId();
		MemberService service = MemberService.getInstance();
		member = service.getMember(id);
		session.setAttribute("member", member);
		
		// 음악
		List<MusicVO> myMusic =  market.myMusic(id);
		req.setAttribute("myMusic", myMusic);
		
		// 미니룸
		List<MiniroomVO> remainMiniroom =  market.remainMiniroom(id);
		req.setAttribute("remainMiniroom", remainMiniroom);
		List<MiniroomVO> myMiniroom =  market.myMiniroom(id);
		req.setAttribute("myMiniroom", myMiniroom);
		
		
		// 설정에서 바꾸자
		// 미니홈피 배경색 변경
		
		// 미니홈피 제목
		
		// 프로필 사진
		
		// 자기소개
		
		// 이메일
		
		// 전화번호
		
		// 남은 도토리
		
		
		// 마켓 화면으로 이동
		HttpUtil.forward(req, resp, "market/market.tiles");
	}

}
