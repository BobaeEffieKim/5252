package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.HttpUtil;
import com.dev.service.GalleryService;
import com.dev.vo.GalleryFolderVO;

public class GalleryMakeFolderFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//폴더등록폼 페이지로 이동
		HttpUtil.forward(req, resp, "gallery/folderMakeForm.tiles");


	}

}