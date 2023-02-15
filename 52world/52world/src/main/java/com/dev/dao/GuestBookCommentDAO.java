package com.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dev.vo.DiaryCommentVO;
import com.dev.vo.GuestBookCommentVO;

public class GuestBookCommentDAO {

	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public void connect() {
		try {

			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 댓글 쓰기
	public void insertComment(GuestBookCommentVO cvo) {
		String sql = "insert into guestbook_comment(comm_id,post_id,content,writer,create_date) values ((select nvl(max(comm_id),0)+1 from guestbook_comment),?,?,?,sysdate)";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cvo.getPostId());
			pstmt.setString(2, cvo.getContent());
			pstmt.setString(3, cvo.getWriter());

			int rs = pstmt.executeUpdate();
			System.out.println("댓글" + rs + "건 입력");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 댓글 수정
	public void updateGuestBookComment(String content,int commId) {
		String sql = "update from guestbook_comment set content=? where comm_id = ?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, commId);
			
			int r = pstmt.executeUpdate();
			System.out.println("댓글" + r + "건 변경");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 댓글삭제
	public boolean deleteGuestBookComment(int commId) {
		String sql = "delete from guestbook_comment where comm_id = ?";

		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commId);

			int r = pstmt.executeUpdate();
			System.out.println("방명록" + r + "건 삭제");

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	// post_id로 해당게시글 댓글 전체조회
	public List<GuestBookCommentVO> commentList(String pidList) {
		String sql = "select * from guestbook_comment where post_id in ("+pidList+") order by comm_Id desc";

		List<GuestBookCommentVO> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GuestBookCommentVO vo = new GuestBookCommentVO();
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
	
	

	// postId 조회
	public GuestBookCommentVO getCommPostId(GuestBookCommentVO vo) {
		connect();
		String sql = "select * from GuestBook_Comment where post_id =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setCommId(rs.getInt("comm_id"));
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("createDate"));

				return vo;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return null;

	}

	// commId 조회
	public GuestBookCommentVO getCommId(GuestBookCommentVO vo) {
		connect();
		String sql = "select * from GuestBook_Comment where comm_id =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCommId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setCommId(rs.getInt("comm_id"));
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("createDate"));

				return vo;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return null;

	}
	
	//방금등록한거 찾아오기
	public GuestBookCommentVO getNewComment() {
		String sql = "select * from guestbook_comment where comm_id = (select max(comm_id) from guestbook_comment)";
	connect();
	GuestBookCommentVO vo = null;
	try {
		pstmt=conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		if(rs.next()) {
			vo=new GuestBookCommentVO();
			vo.setCommId(rs.getInt("comm_id"));
			vo.setPostId(rs.getInt("post_id"));
			vo.setContent(rs.getString("content"));
			vo.setWriter(rs.getString("writer"));
			vo.setCreateDate(rs.getString("create_date"));
		}
				
	} catch (SQLException e) {
	
		e.printStackTrace();
	}finally {
		disconnect();
	}
	return vo;
	}

	
	//댓글 리스트 개수 세기
public List<GuestBookCommentVO> commentCnt(String pidList) {
		
		String sql="SELECT post_id, COUNT(*) as cnt FROM guestbook_comment group by post_id having post_id in ("+pidList+")";

		List<GuestBookCommentVO> list = new ArrayList<>();
		connect();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				GuestBookCommentVO vo = new GuestBookCommentVO();
				vo.setCommId(rs.getInt("cnt"));
				vo.setPostId(rs.getInt("post_id"));
				
				list.add(vo);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	
	}
}