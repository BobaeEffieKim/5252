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

import com.dev.common.Criteria;
import com.dev.vo.GuestBookVO;
import com.dev.vo.GuestProfileVO;
import com.dev.vo.MemberVO;

public class GuestBookDAO {

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

	// 방명록 쓰기.
	public void writeGuestBook(GuestBookVO vo) {

		String sql = "insert into guestbook(post_id,homepee_id,content,writer,create_date,secret) values ((select nvl(max(post_id),0)+1 from guestbook),?,?,?,sysdate,?)";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getHomepeeId());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getSecret());

			int rs = pstmt.executeUpdate();
			System.out.println("방명록" + rs + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 방명록 수정
	public void updateGuestBook(String content,int postId) {
		String sql = "update guestbook set content =? where post_id = ?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, postId);

			int r = pstmt.executeUpdate();
			System.out.println("방명록" + r + "건 변경");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 방명록 삭제
	public boolean deleteGuestBook(int postId) {
		String sql = "delete from guestbook where post_id = ?";

		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);

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

	// 방명록 전체 출력
	public List<GuestProfileVO> getList(String homepeeId) {
		String sql = "select g.*, m.profile\r\n" + "from guestbook g join member m\r\n" + "on g.writer = m.id \r\n"
				+ "where g.homepee_id =?\r\n" + "order by post_id desc";

		List<GuestProfileVO> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GuestProfileVO vo = new GuestProfileVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setSecret(rs.getInt("secret"));
				vo.setProfile(rs.getString("profile"));

				list.add(vo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 페이징
	public List<GuestProfileVO> getGuestBookPaging(String homepeeId, Criteria cri) {
		List<GuestProfileVO> list = new ArrayList<>();
		String sql = "select b.* "//
				+ "from ( "//
				+ "    select rownum rn, a.*  "//
				+ "    from (select g.*, m.profile "//
				+ "            from guestbook g  join member m "//
				+ "            on g.writer = m.id "//
				+ "            where g.homepee_id = ? "//
				+ "            order by g.post_id desc) a "//
				+ "    where rownum <= ?) b "//
				+ "where rn > ?";

		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			pstmt.setInt(2, cri.getAmount() * cri.getPageNum());
			pstmt.setInt(3, cri.getAmount() * (cri.getPageNum() - 1));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestProfileVO vo = new GuestProfileVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setSecret(rs.getInt("secret"));
				vo.setProfile(rs.getString("profile"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// post_id 조회
	public GuestBookVO getBoard(GuestBookVO vo) {
		connect();
		String sql = "select * from guestBook where post_id =?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostId());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setPostId(rs.getInt("post_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("createDate"));
				vo.setSecret(rs.getInt("secret"));

				
				return vo;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return null;

	}
	
	
	//비밀로하기(secret 수정)
		public void secretGuestBook(int secret,int postId) {
			String sql = "update guestbook set secret = ? where post_id = ?";
			connect();

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, secret);
				pstmt.setInt(2, postId);

				int r = pstmt.executeUpdate();
				System.out.println("방명록" + r + "건 비밀");

			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				disconnect();
			}
		
		}

}