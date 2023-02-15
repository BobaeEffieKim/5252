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
import com.dev.service.GuestBookService;
import com.dev.vo.GuestBookCommentVO;
import com.dev.vo.GuestProfileVO;
import com.dev.vo.MiniHomepeeVO;

public class GuestbookController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 방명록 기능 만들어야 함

		HttpSession session = req.getSession();
		MiniHomepeeVO vo = (MiniHomepeeVO) session.getAttribute("Homepee");


		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");
		
		//1~5 페이지만 보이게 할거임
		Criteria cri = new Criteria(1, 5);

		if (pageNum == null) {
			pageNum = "1";
		}
		if (amount == null) {
			amount = "5";
		}

		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));
		
		
		//홈피주인 아이디
		String userId = vo.getHomepeeId();
		
		GuestBookService service = GuestBookService.getInstance();
		List<GuestProfileVO> pageList = service.getListPaging(userId, cri);
		
		System.out.println("페이징 : "+pageList.size());
		
		req.setAttribute("list", pageList);

		
		//전체리스트 출력
	
		List<GuestProfileVO> totalList = service.listGuestBook(userId);
		int total = totalList.size();
		
		System.out.println("전체글 수 : "+total);
			
		//페이징 정보
		req.setAttribute("pageInfo", new Page(cri, total));
		
		req.setAttribute("cri", cri);
		
		
		
		//댓글리스트 pidList에 담기
	
		//이렇게 가져오는게 아닌가...?
	
		String pidList="-1, ";
		for(GuestProfileVO pid : pageList) { 
			pidList += pid.getPostId() +", ";		
		}
		pidList = pidList.substring(0, pidList.length()-2);
		System.out.println(pidList);
		
	
		 List<GuestBookCommentVO> commentList = service.listGuestBookComment(pidList);
		
		 req.setAttribute("commentList", commentList);
		 
		 //댓글 리스트 숫자
		 List<GuestBookCommentVO> commentCnt = service.commentCnt(pidList);
		 req.setAttribute("commentCnt", commentCnt);
		 
		// 방명록 화면으로 이동
		HttpUtil.forward(req, resp, "guestbook/guestbook.tiles");
	}

}