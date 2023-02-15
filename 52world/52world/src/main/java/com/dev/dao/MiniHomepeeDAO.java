package com.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dev.vo.MemberVO;
import com.dev.vo.MiniHomepeeVO;

public class MiniHomepeeDAO {
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

	/****************************************************************/

	// 단건조회
	public MiniHomepeeVO selectHomepee(String id) {
		String sql = "select * from minihomepee where homepee_id = ?";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				MiniHomepeeVO vo = new MiniHomepeeVO();
				vo.setHomepeeId(id);
				vo.setName(rs.getString("name"));
				vo.setBackgroundColor(rs.getString("background_color"));
				vo.setBgm(rs.getString("bgm"));
				vo.setMiniroom(rs.getString("miniroom"));
				vo.setVisitToday(rs.getInt("visit_today"));
				vo.setVisitTotal(rs.getInt("visit_total"));
				vo.setTabColor(rs.getString("tab_color"));
				vo.setVisiter(rs.getString("visiter"));
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	// 회원가입시 홈피정보입력
	public void insertHomepee(MiniHomepeeVO mvo) {
		String sql = "insert into minihomepee (homepee_id, BACKGROUND_COLOR, MINIROOM, name, tab_color, visiter) "
				+ " values(?, ?, ?, ?, ?, ?)";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getHomepeeId());
			pstmt.setString(2, mvo.getBackgroundColor());
			pstmt.setString(3, mvo.getMiniroom());
			pstmt.setString(4, mvo.getName());
			pstmt.setString(5, "#3e9fbe");
			pstmt.setString(6, mvo.getHomepeeId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "개 홈피생성");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 배경음악변경
	public void setMinihomeBgm(String src, String id) {
		String sql = "update minihomepee set bgm = ? where homepee_id=?";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, src);
			pstmt.setString(2, id);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 미니룸 변경
	public void setMinihomeMiniroom(String src, String id) {
		String sql = "update minihomepee set miniroom = ? where homepee_id=?";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, src);
			pstmt.setString(2, id);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 홈피정보 수정
	public void setMinihome(MiniHomepeeVO vo) {
		String sql = "update minihomepee set name = ?, background_color=?, tab_color=? where homepee_id=?";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getBackgroundColor());
			pstmt.setString(3, vo.getTabColor());
			pstmt.setString(4, vo.getHomepeeId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 오늘 방문자 등록
	public void visitMinihomepee(MiniHomepeeVO home) {
		String sql = "update minihomepee set visiter = ?, visit_total=?, visit_today=? where homepee_id=?";
		try {
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, home.getVisiter());
			pstmt.setInt(2, home.getVisitTotal());
			pstmt.setInt(3, home.getVisitToday());
			pstmt.setString(4, home.getHomepeeId());
			
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}
	
	

}
