package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.Criteria;
import com.dev.common.HttpUtil;
import com.dev.common.Page;
import com.dev.dao.DiaryCommentDAO;
import com.dev.service.DiaryService;
import com.dev.vo.DiaryCommentListVO;
import com.dev.vo.DiaryVO;
import com.dev.vo.MiniHomepeeVO;

public class DiaryDetailsControl implements Controller {

	// 다이어리 상세화면 해보는중..
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");
		Criteria cri = new Criteria(1, 5);

		if (pageNum == null) {
			pageNum = "1";
		}
		if (amount == null) {
			amount = "5";
		}

		System.out.println(pageNum);
		System.out.println(amount);

		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));

		DiaryService service = DiaryService.getInstance();

		// 홈피주인 세션을 가져와서 담기
		HttpSession session = req.getSession();
		MiniHomepeeVO vo = (MiniHomepeeVO) session.getAttribute("Homepee");

		String homepeeId = vo.getHomepeeId();

		List<DiaryVO> totalList = service.boardList(homepeeId);

		List<DiaryVO> pageList = service.getListPaging(homepeeId, cri);
		req.setAttribute("list", pageList);
		int total = totalList.size();
		req.setAttribute("pageInfo", new Page(cri, total));

		// 페이지에 게시물 번호를 pidList에 담기
		// select comment에 where post_id in (pidList)하면 될듯함
		String pidList = "-1, ";
		for (DiaryVO pid : pageList) {
			pidList += pid.getPostId() + ", ";
		}

		pidList = pidList.substring(0, pidList.length() - 2);

		System.out.println(pidList);

		// 페이징된 post들의 댓글리스트만 가져옴
		DiaryCommentDAO dao = new DiaryCommentDAO();
		List<DiaryVO> commentList = service.getDiaryCntList(pidList);
		List<DiaryCommentListVO> diaryCommentList = new ArrayList<>();

		for (DiaryVO d : commentList) {
			DiaryCommentListVO diaryCommentVO = new DiaryCommentListVO();
			
			// post_id매개값으로 commentVo.
			diaryCommentVO.setPostId(d.getPostId());
			diaryCommentVO.setHomepeeId(d.getHomepeeId());
			diaryCommentVO.setTitle(d.getTitle());
			diaryCommentVO.setContent(d.getContent());
			diaryCommentVO.setWriter(d.getWriter());
			diaryCommentVO.setCreateDate(d.getCreateDate());
			diaryCommentVO.setCnt(d.getCnt());

			diaryCommentVO.setCommentList(dao.getCommentList(d.getPostId()));

			
			
			diaryCommentList.add(diaryCommentVO);
		}
		req.setAttribute("commentList", diaryCommentList);

		HttpUtil.forward(req, resp, "diary/diaryDetails.tiles");

	}

}