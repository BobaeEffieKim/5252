package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.Criteria;
import com.dev.common.HttpUtil;
import com.dev.common.Page;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryVO;
import com.dev.vo.MemberVO;

public class DiaryListPagingControl implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");
		Criteria cri = new Criteria(1, 10);

		if (pageNum == null) {
			pageNum = "1";
		}
		if (amount == null) {
			amount = "10";
		}
		System.out.println(pageNum);
		System.out.println(amount);
		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));

		DiaryService service = DiaryService.getInstance();

		String homepeeId = req.getParameter("homepee_id");

		
		List<DiaryVO> pageList = service.getListPaging(homepeeId,cri);
		req.setAttribute("list", pageList);
		List<DiaryVO> totalList = service.boardList(homepeeId);
		int total = totalList.size();

		req.setAttribute("pageInfo", new Page(cri, total));

		HttpUtil.forward(req, resp, "diary/diaryListOutput.tiles");

	}
}