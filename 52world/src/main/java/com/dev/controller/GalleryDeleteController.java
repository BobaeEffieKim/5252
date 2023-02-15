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

public class GalleryDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GalleryService service = GalleryService.getInstance();

		//사진의 포스트 아이디 가져오기
		int postId = Integer.parseInt(req.getParameter("postId"));
		System.out.println("삭제하는 사진"+postId);

		//삭제 실행
		boolean isDeleted = service.delGallery(postId);
		//resp.sendRedirect("galleryList.do");
		
		MiniHomepeeVO homepee = (MiniHomepeeVO)req.getSession().getAttribute("Homepee");
		String homepeeId = homepee.getHomepeeId();
		GalleryVO vo = new GalleryVO();
		vo.setHomepeeId(homepeeId);
		
		//resp.sendRedirect("galleryList.do?id="+vo.getHomepeeId());
		HttpUtil.forward(req, resp, "galleryList.do?id="+vo.getHomepeeId());
	}

}