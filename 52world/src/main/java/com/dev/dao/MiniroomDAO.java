package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.MiniroomVO;

public class MiniroomDAO extends DAO {
	// 전체조회
	public List<MiniroomVO> getAllMiniroom() {
		List<MiniroomVO> list = new ArrayList<>();
		try {
			String sql = "select * from miniroom";
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MiniroomVO vo = new MiniroomVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
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
	public MiniroomVO searchOne(int id) {

		try {
			String sql = "select * from miniroom where id = ?";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			if (rs.next()) {
				MiniroomVO vo = new MiniroomVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
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

	// 내가 안산 미니룸
	public List<MiniroomVO> remainMiniroom(String id) {
		List<MiniroomVO> list = new ArrayList<>();
		try {
			String sql = "select * from miniroom where id not in "
					+ "(select item_id from items where BUYER_ID = ? and CATEGORY = 'miniroom')";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MiniroomVO vo = new MiniroomVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
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
	
	// 내가 산 미니룸
	public List<MiniroomVO> myMiniroom(String id) {
		List<MiniroomVO> list = new ArrayList<>();
		try {
			String sql = "select * from miniroom where id in "
					+ "(select item_id from items where BUYER_ID = ? and CATEGORY = 'miniroom')";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MiniroomVO vo = new MiniroomVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
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
