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

public class GalleryAddFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		MemberVO vo =(MemberVO) session.getAttribute("member");
		GalleryService service = GalleryService.getInstance();
		String id = vo.getId();
		List<GalleryFolderVO> list = service.showFolderByHId(id);
		req.setAttribute("folderList", list);

		// 사진등록폼 페이지로 이동
		HttpUtil.forward(req, resp, "gallery/galleryAddForm.tiles");

	}

}