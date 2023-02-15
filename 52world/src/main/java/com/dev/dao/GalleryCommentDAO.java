

package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.GalleryCommentVO;

public class GalleryCommentDAO extends DAO{

	  // 방금등록한거 바로 찾아오기
	   public GalleryCommentVO getNewComment() {
	      String sql="select * from gallery_comment where comm_id = (select max(comm_id) from gallery_comment)";
	      connect();
	      GalleryCommentVO vo=null;
	      try {
	         pstmt=conn.prepareStatement(sql);
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	            vo = new GalleryCommentVO();
	            vo.setCommId(rs.getInt("comm_id"));
	            vo.setPostId(rs.getInt("post_id"));
	            vo.setContent(rs.getString("content"));
	            vo.setWriter(rs.getString("writer"));
	            vo.setCreateDate(rs.getString("create_date"));
	         }
	      
	               
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }   finally {
	         disconnect();
	      }
	      return vo;
	   }

	
	   //댓글 등록
	   public void insetComment(GalleryCommentVO vo) {
	      String sql="insert into gallery_comment values ((select nvl(max(comm_id),0)+1 from gallery_comment), ?, ?, ?, sysdate)";
	      connect();
	      
	      try {
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, vo.getPostId());
	         pstmt.setString(2, vo.getContent());
	         pstmt.setString(3, vo.getWriter());
	         
	         int r=pstmt.executeUpdate();
	         System.out.println(r+"건의 댓글 등록");
	      
	               
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }   finally {
	         disconnect();
	      }
	   }
 
	   
	   //댓글 수정
	   
	   public void updateComment(GalleryCommentVO vo) {
	      String sql = "update gallery_comment set content=? where=post_id=?";
	      connect();
	      
	      try {
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getContent());
	         pstmt.setInt(2, vo.getPostId());
	         int r= pstmt.executeUpdate();
	         System.out.println(r+"건 댓글 수정완료");
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         disconnect();
	      }
	      
	   }

	   
	   // 댓글 삭제 코멘트
	   public boolean deleteComment(int commId) {
	      String sql = "delete from gallery_comment where comm_id=?";
	      connect();

	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, commId);
	         int r = pstmt.executeUpdate();
	         System.out.println(r + "건의 댓글을 삭제.");
	         if (r != 0) {
	            return true;
	         }
	      } catch (SQLException e) {

	         e.printStackTrace();
	      } finally {
	         disconnect();
	      }

	      return false;
	   }

	   // 댓글 리스트 출력
	   
	   public List<GalleryCommentVO> getList(String pidList) {

	      String sql="select * from gallery_comment where post_id in ("+pidList+") order by create_date desc";

	      List<GalleryCommentVO> list=new ArrayList<>();
	      connect();
	      
	      try {
	         pstmt=conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            GalleryCommentVO vo = new GalleryCommentVO();
	            vo.setCommId(rs.getInt("comm_id"));
	            vo.setPostId(rs.getInt("post_id"));
	            vo.setContent(rs.getString("content"));
	            vo.setWriter(rs.getString("writer"));
	            vo.setCreateDate(rs.getString("create_date"));
	            
	            list.add(vo);
	         }
	               
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         disconnect();
	      }
	      return list;
	   }
	   

	   
	   
	   
}

