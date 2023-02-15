package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.BffVO;

public class BffDAO extends DAO {

	/*
	 * Connection conn; PreparedStatement pstmt; ResultSet rs;
	 * 
	 * public void connect() { try { InitialContext ic= new InitialContext();
	 * DataSource ds=(DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
	 * conn=ds.getConnection();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } public void disconnect() {
	 * try { if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close(); if(conn !=null)
	 * conn.close();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); //catch구문은 비워두면 안됨. } }
	 */

	// 입력(일촌신청)

	public void requestBff(BffVO vo) {
		String sql = "insert into BFF values((select nvl(max(id),0)+1 from BFF),?,?,?,?,NULL,?)";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getReqId());
			pstmt.setString(2, vo.getReqName());
			pstmt.setString(3, vo.getApplyId());
			pstmt.setString(4, vo.getApplyName());
			pstmt.setString(5, vo.getMsg());

			int r = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	// 일촌 신청 목록

	public List<BffVO> requestBffList(String applyId) {
		String sql = "select * from bff where apply_id=? and answer is null";
		connect();

		List<BffVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BffVO vo = new BffVO();
				vo.setId(rs.getInt("id"));
				vo.setApplyId(applyId);
				vo.setReqId(rs.getString("req_id"));
				vo.setReqName(rs.getString("req_name"));
				vo.setApplyName(rs.getString("apply_name"));
				vo.setMsg(rs.getString("msg"));

				list.add(vo);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	// 일촌 수락
	public void updateBff(int id) {
		String sql = "update bff set answer='Y' where id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int r = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 일촌 거절
	public void deleteBff(int id) {
		String sql = "delete from bff where id = ?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int r = pstmt.executeUpdate();
			System.out.println(r+"건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 일촌리스트 받아오기
	public List<BffVO> getBffList(String id) {
		List<BffVO> list = new ArrayList<>();
		String sql = "select req_id as id, apply_name as myname, req_name as bffname from bff where apply_id=? and answer='Y' union "
				+ "select apply_id as id, req_name as myname, apply_name as bffname from bff where req_id=? and answer='Y'";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BffVO vo = new BffVO();
				vo.setId(0);
				vo.setReqId(rs.getString("id"));
				vo.setReqName(rs.getString("myname"));
				vo.setApplyId(rs.getString(1));
				vo.setApplyName(rs.getString("bffname"));
				vo.setMsg("");
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 일촌 목록 보기 + 이름까지 포함해서 조인문... 할수 있을까???????????

	public List<BffVO> getBffReqList(String applyId) {

		String sql = "select b.id, m.name as realName, b.req_id, b.req_name, b.apply_id, b.apply_name, b.answer, b.msg from bff b, member m"
				+ " where b.req_id = m.id and apply_id=? and answer is null";
		connect();

		List<BffVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BffVO vo = new BffVO();
				vo.setId(rs.getInt("id"));
				vo.setApplyId(applyId);
				vo.setReqId(rs.getString("req_id"));
				vo.setReqName(rs.getString("req_name"));
				vo.setApplyName(rs.getString("apply_name"));
				vo.setMsg(rs.getString("msg"));
				vo.setRealName(rs.getString("realName"));

				list.add(vo);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

}
