package com.dev.dao;

import java.sql.SQLException;

import com.dev.common.DAO;
import com.dev.vo.ItemsVO;

public class ItemsDAO extends DAO {

	// 배경음악 구매
	public void buyMusic(ItemsVO vo) {
		try {
			String sql = "insert into items values((select nvl(max(buy_id),0)+1 from items)  , ?, ?, ?)";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getItemId());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getBuyerId());
			int r = pstmt.executeUpdate();

			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 미니룸 구매
	public void buyMiniroom(ItemsVO vo) {
		try {
			String sql = "insert into items values((select nvl(max(buy_id),0)+1 from items)  , ?, ?, ?)";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getItemId());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getBuyerId());
			int r = pstmt.executeUpdate();

			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 미니룸 구매
	public void defaultMiniroom(String id) {
		try {
			String sql = "insert into items values((select nvl(max(buy_id),0)+1 from items)  , ?, ?, ?)";
			connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, "miniroom");
			pstmt.setString(3, id);
			int r = pstmt.executeUpdate();

			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
