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

public class GalleryDeleteFolderController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//폴더이름을 가져오고
	      HttpSession session = req.getSession();
	      MemberVO vo =(MemberVO) session.getAttribute("member");
	      GalleryService service = GalleryService.getInstance();
	      String id = vo.getId();
	      List<GalleryFolderVO> list = service.showFolderByHId(id);
	      req.setAttribute("folderList", list); 
	      
	      
	      String folderName = req.getParameter("folder");
	      System.out.println("폴더삭제컨 폴더이름:"+folderName);
	      String homepeeId= req.getParameter("homepeeId");
	      System.out.println("폴더삭제컨 홈피id:"+homepeeId);
	      //
	      
	      GalleryFolderVO gfvo = new GalleryFolderVO();
	      gfvo.setFolderName(folderName);
	      gfvo.setHomepeeId(homepeeId);
	      
	      service.delGalleryFolder(gfvo);
	      //
	      HttpUtil.forward(req, resp, "galleryList.do?id="+gfvo.getHomepeeId());
	}
}
