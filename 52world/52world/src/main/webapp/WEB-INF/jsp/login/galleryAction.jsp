
<%@page import="org.springframework.web.multipart.MultipartRequest"%>
<!-- request대신 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%
/* (kbb) '사진추가' 버튼을 누르면 구현되어야할 액션을 처리하는 페이지 */
	
	DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
	MultipartRequest mr = new MultipartRequest(request, request.getRealPath("/image"), 
								1024*1024*1024, "utf-8", policy);
	System.out.println("저장되는 경로(실제 서버) : "+request.getRealPath("/image"));
	
	String fileName = mr.getFileSystemName("image");
	System.out.println("사진 이름 : "+fileName);
%> --%>