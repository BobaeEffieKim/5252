package com.dev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.common.Controller;
import com.dev.common.Criteria;
import com.dev.common.HttpUtil;
import com.dev.common.Page;
import com.dev.service.GuestBookService;
import com.dev.vo.GuestBookVO;

public class GuestBookListPagingController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpUtil.forward(req, resp, "guestbook/guestbook.tiles");

	}

}