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

public class GalleryUpdateFolderFormController implements Controller {

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
		
	
		String folderName = req.getParameter("folderName");
		System.out.println(folderName);

		String editFolderName = req.getParameter("editFolderName");
		System.out.println(folderName);
		
		String secret = req.getParameter("secret");
		System.out.println(secret);

		req.setAttribute("folderName", folderName);
		req.setAttribute("editFolderName", editFolderName);
		req.setAttribute("secret", secret);
		
		
		//폴더수정폼 페이지로 이동
		HttpUtil.forward(req, resp, "gallery/folderUpdateForm.tiles");

	}

}