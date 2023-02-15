package com.dev.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
import com.dev.service.GalleryService;
import com.dev.vo.GalleryVO;

public class GalleryAddController implements Controller {

   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      GalleryService service = GalleryService.getInstance();

      //게시물번호 / 주인아이디 / 게시날짜 / 제목 / 내용 / 폴더선택
      String[] arr = new String[4];
      GalleryVO vo = new GalleryVO();
      int index=0;

      //갤러리 이름 정보 가져오기
      String realPath = req.getSession().getServletContext().getRealPath("/");
      realPath = realPath.substring(0, realPath.length() - 30);
      realPath += "\\src\\main\\webapp\\asset";
      System.out.println(realPath);
      File DirPath = new File(realPath);
      //사진명 중복되지 않게 밀리세컨 붙이기
      String dValue = String.valueOf(System.currentTimeMillis());
      String fileName = vo.getImg() +"_"+dValue+".jpg";

      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setRepository(DirPath);
      // 파일크기 5메가로 설정
      factory.setSizeThreshold(5 * 1024 * 1024);
      ServletFileUpload upload = new ServletFileUpload(factory);
      //사진업로드
      try {
         List<FileItem> items = upload.parseRequest(req);
         for (int i = 0; i < items.size(); i++) {
            FileItem fileItem = (FileItem) items.get(i);
            if (fileItem.isFormField()) {
               arr[index++]=fileItem.getString("utf-8");
               System.out.println(fileItem.getFieldName() + "=>>" + fileItem.getString("utf-8"));
               req.setAttribute("introduce", fileItem.getString("utf-8"));
            } else {
               if (fileItem.getSize() > 0) {
                  int idx = fileItem.getName().lastIndexOf("\\");
                  if (idx == -1) {
                     idx = fileItem.getName().lastIndexOf("/");
                  }
                  File uploadFile = new File(DirPath + "\\" + fileName);
                  fileItem.write(uploadFile);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      // db에 insert하는 코드
      System.out.println(Arrays.toString(arr));
      fileName = "/52world/asset/"+fileName;
      vo.setTitle(arr[0]);
      vo.setContent(arr[1]);
      vo.setImg(fileName);
      vo.setFolder(arr[2]);
      vo.setHomepeeId(arr[3]);
 
      // 이미지 src 넘겨주기
      service.insertGallery(vo);

      resp.sendRedirect("galleryList.do?id="+vo.getHomepeeId());



   }

}