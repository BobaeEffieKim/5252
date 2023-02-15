package com.dev.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class ProfileController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 계정 정보 가져오기
		MemberVO vo = (MemberVO) req.getSession().getAttribute("member");

		String realPath = req.getSession().getServletContext().getRealPath("/");
		realPath = realPath.substring(0, realPath.length() - 30);
		realPath += "\\src\\main\\webapp\\asset";
		System.out.println(realPath);
		File DirPath = new File(realPath);
		String millis = String.valueOf(System.currentTimeMillis());
		String fileName = vo.getId() + "_profile"+millis+".jpg";
		String intro = "";
		boolean isInput = false;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(DirPath);
		// 파일크기 5메가
		factory.setSizeThreshold(5 * 1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				
				
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString("utf-8"));
					intro = fileItem.getString("utf-8");
					req.setAttribute("introduce", fileItem.getString("utf-8"));
				} 
				
				else {
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						File uploadFile = new File(DirPath + "\\" + fileName);
						fileItem.write(uploadFile);
						isInput=true;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// db에 insert하는 코드필요
		MemberService service = MemberService.getInstance();
		fileName = "/52world/asset/"+fileName;
		if(isInput) {
			vo.setProfile(fileName);
		}
		vo.setIntroduce(intro);
		service.changeProfile(vo);

		// 이미지 src 넘겨주기
		req.setAttribute("src", fileName);
		HttpUtil.forward(req, resp, "minihomepee.do");
	}

}
