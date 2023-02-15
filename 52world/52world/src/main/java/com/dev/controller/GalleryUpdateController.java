package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryVO;
import com.dev.vo.MiniHomepeeVO;

public class GalleryUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//사진수정 처리
		GalleryService service = GalleryService.getInstance();

		int postId = Integer.parseInt(req.getParameter("poId"));
		System.out.println(postId+"불러와지는가");
		//String homepeeId_ = req.getParameter("homepeeId");
		String title = req.getParameter("title");
		System.out.println(title);
		String content = req.getParameter("content");
		System.out.println(content);
		String folder = req.getParameter("folder");
		System.out.println(folder);
		//String img = req.getParameter("homepeeId");
		//String writer = req.getParameter("homepeeId");
		//String createDate = req.getParameter("homepeeId");
		//int views = Integer.parseInt(req.getParameter("postId"));
		//int likes = Integer.parseInt(req.getParameter("postId"));
		//String likeList = req.getParameter("homepeeId");


		MiniHomepeeVO homepee = (MiniHomepeeVO)req.getSession().getAttribute("Homepee");
		String homepeeId = homepee.getHomepeeId();
		GalleryVO vo = new GalleryVO();

		vo.setPostId(postId);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setFolder(folder);
		vo.setHomepeeId(homepeeId);

		//수정실행
		service.editGallery(vo);
		HttpUtil.forward(req, resp, "galleryList.do?id="+vo.getHomepeeId());
		//resp.sendRedirect("galleryList.do?id="+vo.getHomepeeId());


	}

}