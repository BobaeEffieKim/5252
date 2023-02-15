package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.DAO;
import com.dev.vo.BffCommentVO;
import com.dev.vo.DiaryCommentVO;

public class BffCommentDAO extends DAO {

	//등록
	//insert into bffcomment values (1, 'user1', '나 왔다간다~ 발도장 꾸욱!','5조조장','이기환',sysdate);

	public void insertComment(BffCommentVO vo) {
	String sql = "insert into bffcomment values ((select nvl(max(comment_id),0)+1 from bffcomment),?,?,?,?, sysdate)";
	connect();

	try {
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getHomepeeId());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getBffName());
		pstmt.setString(4, vo.getWriter());
		

		int r = pstmt.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}
}

	//조회 (리스트 타입) 

	//select content,bffname,writer from bffcomment;

	public List<BffCommentVO> getList() {

		String sql = "select * from bffcomment order by 4 desc where hompeeid=?";
		connect();
		List<BffCommentVO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				BffCommentVO comment = new BffCommentVO();

				comment.setCommentId(rs.getInt("comment_id"));
				comment.setHomepeeId(rs.getString("homepee_id"));
				comment.setContent(rs.getString("content"));
				comment.setBffName(rs.getString("bffname"));
				comment.setWriter(rs.getString("writer"));
				comment.setCreateDate(rs.getString("create_date"));

				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//조회 (리스트 타입) 홈피 아이디로 가져와야하나? 

	public List<BffCommentVO> getCommentList(String homepeeId) {
		
		// TO_CHAR(create_date,'YYYY-MM-DD') as credate 얘 칼럼이름을
		// create_date로 하니까 정렬이 제대로 안되길래 좀 고쳤어요~
		
		String sql = "select homepee_id, comment_id,content, bffname, writer,TO_CHAR(create_date,'YYYY-MM-DD') as credate from bffcomment where homepee_id=? order by create_date desc";
		connect();
		List<BffCommentVO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				BffCommentVO comment = new BffCommentVO();

				comment.setHomepeeId(homepeeId);
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setContent(rs.getString("content"));
				comment.setBffName(rs.getString("bffname"));
				comment.setWriter(rs.getString("writer"));
				comment.setCreateDate(rs.getString("credate"));
				list.add(comment);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	
	// 일촌평 수정
	
	public void updateComment(BffCommentVO vo) {
			String sql = "update bffcomment set content=? where=comm_id=?";
			connect();
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, vo.getContent());
				pstmt.setInt(2, vo.getCommentId());
				int r= pstmt.executeUpdate();
				System.out.println(r+"건 댓글 수정완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
		}
	
	
	// 일촌평 삭제

	public boolean deleteComment(int commId) {
		String sql = "delete from bffcomment where comment_id=?";
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
	}


