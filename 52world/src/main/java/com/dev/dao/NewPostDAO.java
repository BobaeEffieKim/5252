package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.NewPostVO;

public class NewPostDAO extends DAO {

	// 최근게시물 5개 불러오기
	public List<NewPostVO> getNew(String homepeeId) {
		List<NewPostVO> list = new ArrayList<NewPostVO>();
		connect();
		String sql = "select * from new_post where homepee_id= ? and ROWNUM<=5";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NewPostVO vo = new NewPostVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setCategory(rs.getString("category"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 다이어리 전체게시물 수
	public int dTotal(String homepeeId) {
		connect();
		String sql = "select count(*) as d_total from diary where homepee_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("d_total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 다이어리 오늘 게시물 수
	public int dToday(String homepeeId) {
		connect();
		String sql = "select count(*) as d_today from diary where homepee_id=? "
				+ " and to_char(create_date, 'yymmdd')=to_char(sysdate, 'yymmdd') ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("d_today");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 사진첩 전체게시물 수
	public int gTotal(String homepeeId) {
		connect();
		String sql = "select count(*) as g_total from gallery where homepee_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("g_total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 사진첩 오늘 게시물 수
	public int gToday(String homepeeId) {
		connect();
		String sql = "select count(*) as g_today from gallery where homepee_id=? "
				+ " and to_char(create_date, 'yymmdd')=to_char(sysdate, 'yymmdd') ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("g_today");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 방명록 전체게시물 수
	public int gbTotal(String homepeeId) {
		connect();
		String sql = "select count(*) as gb_total from guestbook where homepee_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("gb_total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 방명록 오늘 게시물 수
	public int gbToday(String homepeeId) {
		connect();
		String sql = "select count(*) as gb_today from guestbook where homepee_id=? "
				+ " and to_char(create_date, 'yymmdd')=to_char(sysdate, 'yymmdd') ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("gb_today");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}
}
