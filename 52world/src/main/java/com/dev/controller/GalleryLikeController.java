
package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryVO;
import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class GalleryLikeController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MiniHomepeeVO homepee = (MiniHomepeeVO)req.getSession().getAttribute("Homepee");
		/*
		 * String homepeeId = homepee.getHomepeeId();
		 * 
		 * vo.setHomepeeId(homepeeId);
		 */

		HttpSession session = req.getSession();
		MemberVO login = (MemberVO) session.getAttribute("member");
		
		
		
		GalleryService service = GalleryService.getInstance();
		
		//해당사진 게시번호 가져오기
		int postId = Integer.parseInt(req.getParameter("postId"));
		System.out.println("좋아요 적용되는 게시물 : "+postId);
		String likeList = req.getParameter("likeMember");
		System.out.println("좋아요한 사람들 : "+likeList);
		// 갤러리서비스에서 postId로 갤러리vo객체 가져오기
		GalleryVO vo = service.searchById(postId);
		
		//user1  user3
		//
		if(vo.getLikes() >=0 && vo.getLikeList()==null) {
			
			vo.setLikes(1);
			
			vo.setLikeList(login.getId());
			System.out.println("좋아요리스트가 빈값이면 넣어주기:"+login.getId());
		}
		else if (!vo.getLikeList().contains(login.getId())) {
			vo.setLikes(vo.getLikes()+1);
			
			vo.setLikeList(vo.getLikeList()+" "+login.getId());
			System.out.println("좋아요+1"+login.getId());
			
		} else {
			vo.setLikes(vo.getLikes()-1);
			String list = vo.getLikeList().replaceAll(login.getId(),"");
			vo.setLikeList(list);
			System.out.println("좋아요해제 : "+login.getId());
		}
		
		//좋아요 올리기 실행
		//service.likeGallery(vo.getLikeList(), postId);
		service.likeGallery(vo);
		
		//보내기
		//resp.sendRedirect("galleryList.do?id="+vo.getHomepeeId());
		HttpUtil.forward(req, resp, "galleryList.do?id="+login.getId()+"&homepee_id="+homepee.getHomepeeId());
		//HttpUtil.forward(req, resp, "/galleryList.do");
		
		
		
	}

}

