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
import com.dev.vo.GalleryVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class GalleryUpdateFolderController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//폴더 변경 처리


		//폴더이름을 가져오고
		HttpSession session = req.getSession();
		MemberVO vo =(MemberVO) session.getAttribute("member");
		GalleryService service = GalleryService.getInstance();
		String id = vo.getId();
		List<GalleryFolderVO> list = service.showFolderByHId(id);
		req.setAttribute("folderList", list); 



		String folderName = req.getParameter("folder");
		System.out.println("폴더이름"+folderName);
		
		String editFolderName = req.getParameter("fname");
		System.out.println("변경할 이름"+editFolderName);
		
		int secret = Integer.parseInt(req.getParameter("secretOption"));
		System.out.println("공개상태"+secret);
		
		String homepeeId= req.getParameter("homepeeId");
		System.out.println("홈피아이디"+homepeeId);


	//	MiniHomepeeVO homepee = (MiniHomepeeVO)req.getSession().getAttribute("Homepee");
	//	String homepeeId = homepee.getHomepeeId();
		
		GalleryFolderVO gfvo = new GalleryFolderVO();
		
		gfvo.setFolderName(folderName);
		gfvo.setFolderName(editFolderName);
		gfvo.setSecret(secret);
		gfvo.setHomepeeId(homepeeId);

		
		/*
		if(secret == 0) {
			gfvo.setSecret(0);
			gfvo.setFolderName(folderName);
		} else if(secret == 1) {
			gfvo.setSecret(1);
			gfvo.setFolderName(folderName);
		} else {
			gfvo.setSecret(2);
			gfvo.setFolderName(folderName);
		}
		*/

		service.editGalleryFolder(folderName, editFolderName, secret, homepeeId);
		service.moveGalleryFolder(folderName, editFolderName, homepeeId);

		HttpUtil.forward(req, resp, "galleryList.do?id="+gfvo.getHomepeeId());
		//resp.sendRedirect("galleryList.do");

		
		
		
		/*이전
		MiniHomepeeVO homepee = (MiniHomepeeVO) req.getSession().getAttribute("Homepee");
		String homepeeId =  homepee.getHomepeeId();
		
		GalleryFolderVO vo = new GalleryFolderVO();
		vo.setFolderName(folderName);
		vo.setSecret(secret);
		vo.setHomepeeId(homepeeId);
		
		service.editGalleryFolder(vo);
		req.getRequestDispatcher("galleryList.do").forward(req, resp);
		*/


	}

}