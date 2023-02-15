package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.ItemsVO;
import com.dev.vo.MusicVO;

public class MusicDAO extends DAO {
	// 전체조회
	public List<MusicVO> getAllMusic() {
		List<MusicVO> list = new ArrayList<>();
		try {
			String sql = "select * from music";
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setSinger(rs.getString("singer"));
				vo.setPrice(rs.getInt("price"));
				vo.setSrc(rs.getString("src"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}

	// 검색
	public List<MusicVO> searchMusic(String search, String id) {
		List<MusicVO> list = new ArrayList<>();
		try {
			String sql = "select * from music where (title like '%" + search + "%' or singer like '%" + search + "%') and id not in "
					+ "(select item_id from items where BUYER_ID = ? and CATEGORY = 'music')";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setSinger(rs.getString("singer"));
				vo.setPrice(rs.getInt("price"));
				vo.setSrc(rs.getString("src"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}

	// 단건조회
	public MusicVO searchOne(int id) {

		try {
			String sql = "select * from music where id = ?";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setSinger(rs.getString("singer"));
				vo.setPrice(rs.getInt("price"));
				vo.setSrc(rs.getString("src"));
				return vo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return null;

	}

	// 내가 안산 브금
	public List<MusicVO> remainMusic(String id) {
		List<MusicVO> list = new ArrayList<>();
		try {
			String sql = "select * from music where id not in "
					+ "(select item_id from items where BUYER_ID = ? and CATEGORY = 'music')";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(id+5555555);
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setSinger(rs.getString("singer"));
				vo.setPrice(rs.getInt("price"));
				vo.setSrc(rs.getString("src"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}

	// 내가 산 브금
	public List<MusicVO> myMusic(String id) {
		List<MusicVO> list = new ArrayList<>();
		try {
			String sql = "select * from music where id in "
					+ "(select item_id from items where BUYER_ID = ? and CATEGORY = 'music')";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setSinger(rs.getString("singer"));
				vo.setPrice(rs.getInt("price"));
				vo.setSrc(rs.getString("src"));
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
