package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.MemberVO;

public class GalleryUpdateFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//홈피주인아이디로 수정할 폴더 드롭다운에 폴더목록 보여주기
		HttpSession session = req.getSession();
		MemberVO vo =(MemberVO) session.getAttribute("member");
		GalleryService service = GalleryService.getInstance();
		String id = vo.getId();
		List<GalleryFolderVO> list = service.showFolderByHId(id);
		req.setAttribute("folderList", list);
		
		//
		int postId = Integer.parseInt(req.getParameter("postId"));
		System.out.println(postId);
		String title = req.getParameter("title");
		System.out.println(title);

		String content = req.getParameter("content");
		System.out.println(content);

		String folderName = req.getParameter("folderName");
		System.out.println(folderName);

		 req.setAttribute("postId", postId); 
		 req.setAttribute("title", title);
		 req.setAttribute("content", content); 
		 req.setAttribute("folderName", folderName); 
		
		// 사진수정 화면으로 이동
		HttpUtil.forward(req, resp, "gallery/galleryUpdateForm.tiles");

	}

}