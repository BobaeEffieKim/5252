package com.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.MiniHomepeeVO;

public class GalleryMakeFolderController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 폴더생성 처리

		  GalleryService service = GalleryService.getInstance();

		  String folderName = req.getParameter("fname");
		  int secret = Integer.parseInt(req.getParameter("secretOption"));
		  MiniHomepeeVO homepee = (MiniHomepeeVO) req.getSession().getAttribute("Homepee");
		  String homepeeId =  homepee.getHomepeeId();
		  GalleryFolderVO vo = new GalleryFolderVO();

			/*
			 * System.out.println(folderName); System.out.println(secret);
			 */

		/*	 resp.setContentType("text/html;charset=utf-8");
			 resp.setCharacterEncoding("UTF-8"); PrintWriter writer = resp.getWriter();
		
			 GalleryFolderVO fvo = service.searchFolderName(folderName);
			 
			 if(folderName.equals(vo.getFolderName())) {
			 writer.println("<script type='text/javascript'>");
			 writer.println("alert('이미 존재하는 폴더명입니다. 다시 입력해주세요.')");
			 writer.println("history.back()"); writer.println("</script>"); 
			 } else {
			 service.makeGalleryFolder(vo);
			 req.getRequestDispatcher("galleryList.do").forward(req, resp);
			 }
		*/	 

		  vo.setFolderName(folderName);
		  vo.setSecret(secret);
		  vo.setHomepeeId(homepeeId);

		  service.makeGalleryFolder(vo);
		  HttpUtil.forward(req, resp, "galleryList.do?id="+vo.getHomepeeId());
		  
		  //req.setAttribute("GalleryFolder", vo);
		  //req.getRequestDispatcher("galleryList.do").forward(req, resp);

			/*
			 * List<GalleryFolderVO> gflist = service.showGalleryFolder();
			 * 
			 * req.setAttribute("GFlist", gflist);
			 * req.getRequestDispatcher("gallery/folderMakeForm.tiles").forward(req, resp);
			 */


	}

}