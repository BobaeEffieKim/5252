package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.DiaryCommentVO;
import com.dev.vo.DiaryVO;

public class DiaryCommentDAO extends DAO {

	// 방금등록한거 바로 찾아오기
	public DiaryCommentVO getNewComment() {
		String sql = "select * from diary_comment where comm_id = (select max(comm_id) from diary_comment)";
		connect();
		DiaryCommentVO vo = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new DiaryCommentVO();
				vo.setCommId(rs.getInt("comm_id"));
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;

	}

	// 댓글 등록
	public void insetComment(DiaryCommentVO vo) {
		String sql = "insert into diary_comment values ((select nvl(max(comm_id),0)+1 from diary_comment), ?, ?, ?, sysdate)";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostId());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건의 댓글 등록");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 댓글 수정

	public void updateComment(DiaryCommentVO vo) {
		String sql = "update diary_comment set content=? where=post_id=?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setInt(2, vo.getPostId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 댓글 수정완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 댓글 삭제 코멘트
	public boolean deleteComment(int commId) {
		String sql = "delete from diary_comment where comm_id=?";
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

	public List<DiaryCommentVO> getList(String pidList) {

		String sql = "select * from diary_comment where post_id in (" + pidList + ") order by create_date desc";

		List<DiaryCommentVO> list = new ArrayList<>();
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryCommentVO vo = new DiaryCommentVO();
				vo.setCommId(rs.getInt("comm_id"));
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));

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
	// 위에꺼 대신해서 만듬. 혹 사용하는곳이 있나싶어서.

	public List<DiaryVO> getDiaryCntList(String pidList) {

		String sql = "select d.*, nvl(dc.cnt, 0) cnt "
				+ "   from diary  d, (select post_id, count(*) cnt from diary_comment  group by post_id) dc "
				+ "   where d.post_id = dc.post_id(+)  and d.post_id in (" + pidList + ") order by d.post_id desc";

		List<DiaryVO> list = new ArrayList<>();
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryVO vo = new DiaryVO();

				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setCnt(rs.getInt("cnt"));

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
	
	public List<DiaryCommentVO> getCommentList(int postId){
		String sql = "select * from diary_comment where post_id = ? order by comm_id desc";
		List<DiaryCommentVO> list = new ArrayList<>();
		connect();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				DiaryCommentVO vo = new DiaryCommentVO();
				vo.setCommId(rs.getInt("comm_id"));
				vo.setPostId(rs.getInt("post_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));

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

	// 댓글 리스트 갯수세기

	public List<DiaryCommentVO> commentCnt(String pidList) {

		
		String sql="SELECT post_id, COUNT(*) as cnt FROM diary_comment group by post_id having post_id in ("+pidList+")";

		List<DiaryCommentVO> list = new ArrayList<>();
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryCommentVO vo = new DiaryCommentVO();
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