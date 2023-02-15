package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MarketService;
import com.dev.vo.MusicVO;

public class BuyMusicController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MusicVO> mList = new ArrayList<>();
		MarketService service = MarketService.getInstance();

		String list = req.getParameter("id");
		StringTokenizer st = new StringTokenizer(list, "_");
		while (st.hasMoreTokens()) {
			MusicVO vo = service.getOneMusic(Integer.parseInt(st.nextToken()));
			mList.add(vo);
		}

		req.setAttribute("mList", mList);
		HttpUtil.forward(req, resp, "WEB-INF/jsp/market/buyMusicResult.jsp");

	}

}
