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

public class GalleryDeleteFolderFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//홈피주인아이디로 삭제할 폴더 드롭다운에 폴더목록 보여주기
		HttpSession session = req.getSession();
		MemberVO vo =(MemberVO) session.getAttribute("member");
		GalleryService service = GalleryService.getInstance();
		String id = vo.getId();
		List<GalleryFolderVO> list = service.showFolderByHId(id);
		req.setAttribute("folderList", list);
	
		
		//
		String folderName = req.getParameter("folder");
		System.out.println(folderName);
		
		String homepeeId = req.getParameter("homepeeId");
		System.out.println(homepeeId);
		
		req.setAttribute("folder", folderName);
		req.setAttribute("homepeeId", homepeeId);
		
		//폴더삭제폼 페이지로 이동
		HttpUtil.forward(req, resp, "gallery/folderDeleteForm.tiles");

	}

}
