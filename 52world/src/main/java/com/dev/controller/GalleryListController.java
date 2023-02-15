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
import com.dev.common.PageParam;
import com.dev.service.GalleryService;
import com.dev.vo.DiaryCommentVO;
import com.dev.vo.GalleryCommentVO;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.GalleryVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class GalleryListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션
		HttpSession session = req.getSession();
		MiniHomepeeVO vo = (MiniHomepeeVO) session.getAttribute("Homepee");
		MemberVO member = (MemberVO) session.getAttribute("member");

		System.out.println(vo + "홈피주인정보가져와야해");
		String likeList = req.getParameter("likeList");

		String login = member.getId();
		System.out.println("login : "+login);

		String fNm = req.getParameter("fId");
		System.out.println("fNm:"+fNm);

		String homepeeId = vo.getHomepeeId();
		System.out.println("homepeeId:"+homepeeId);

		// String userId = vo.getHomepeeId();
		PageParam param = new PageParam();
		param.setFolder(fNm);
		param.setHomepeeId(homepeeId);
		param.setLoginId(login);

		// 페이징
		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");

		// 1~5 페이지만 보이게 할거임
		Criteria cri = new Criteria();

		if (pageNum == null||pageNum=="") {
			pageNum = "1";
		}
		if (amount == null||amount=="") {
			amount = "5";
		}
		System.out.println(pageNum);
		System.out.println(amount);
		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));

		// 홈피주인 아이디

		GalleryService service = GalleryService.getInstance();
		List<GalleryVO> pageList = service.getListPaging(homepeeId, cri, fNm); // 5
		System.out.println(homepeeId + cri);
		// List<GalleryVO> pageList = service.getListPaging(userId, cri);

		System.out.println(pageList.size());

		// 총페이지리스트
		req.setAttribute("list", pageList);

		List<GalleryVO> totalList = service.showGallery(homepeeId, fNm);
		int total = totalList.size();
		System.out.println(total);

		// 페이징 정보
		req.setAttribute("pageInfo", new Page(cri, total));
		req.setAttribute("pageParam", param);

		/////////////////////////////////////////////////////////////

		// 사진첩 피드
		// 해당유저의 아이디만 보여주게 파라미터 넘기기
		// int postId = Integer.parseInt(req.getParameter("postId"));

//		List<GalleryVO> glist = service.showGallery(hId, fNm); // 6

//		req.setAttribute("list", glist);

		// 홈피주인아이디로 만든 폴더 리스트만 보이기

		List<GalleryFolderVO> gflist = service.showFolderByHId(homepeeId);

		req.setAttribute("GFlist", gflist);
		// req.getRequestDispatcher("gallery/folderMakeForm.tiles").forward(req, resp);


		//댓글
		// 페이지에 게시물 번호를 pidList에 담기
	      // select comment에 where post_id in (pidList)하면 될듯함
	      String pidList="-1, ";
	      for(GalleryVO pid : pageList) {
	         pidList+= pid.getPostId()+", ";
	      }
	      pidList = pidList.substring(0, pidList.length()-2);
	      System.out.println(pidList);

	      // 페이징된 post들의 댓글리스트만 가져옴
	      List<GalleryCommentVO> commentList = service.getList(pidList);
	      req.setAttribute("commentList", commentList);


		// 사진첩 화면으로 이동
		HttpUtil.forward(req, resp, "gallery/galleryList.tiles");
		// HttpUtil.forward(req, resp, "gallery/addGalleryForm.tiles");

	}
}