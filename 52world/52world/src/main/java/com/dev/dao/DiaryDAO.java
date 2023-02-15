package com.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.common.Criteria;
import com.dev.common.DAO;
import com.dev.vo.DiaryFolderVO;
import com.dev.vo.DiaryVO;

public class DiaryDAO extends DAO {

	public void writeDiary(DiaryVO vo) {

		String sql = "insert into diary "
				+ "   values ((select nvl(max(post_id),0)+1 from diary),?,?,?,?,default,default,default,default,default)";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getHomepeeId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getWriter());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 입력");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 리스트 출력

	public List<DiaryVO> getList(String homepeeId) {
		String sql = "select * from diary where homepee_id=? order by post_id desc";
		List<DiaryVO> list = new ArrayList<>();
		connect();

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DiaryVO vo = new DiaryVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(homepeeId);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setViews(rs.getInt("views"));
				vo.setLikes(rs.getInt("likes"));
				vo.setLike_list(rs.getString("like_list"));
				vo.setFolder(rs.getString("folder"));

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

	// 페이징 처리

	// 한페이지당 보여주고 싶은 갯수 amount, 페이지 수pageNum
		public List<DiaryVO> getListPaging(String homepeeId, Criteria cri) {
			List<DiaryVO> listPage = new ArrayList<>();
			String sql = "select  post_id, homepee_id, title, content, writer,create_date,views,likes,like_list, folder "
		            + "from (select rownum rn, post_id, homepee_id, title, content, writer,create_date,views,likes,like_list, folder "
		            + "from (select  post_id, homepee_id,title, content, writer, TO_CHAR(create_date,'YYYY.MM.DD DY HH24:MI') as create_date, views, likes, like_list, folder from diary where homepee_id=? order by post_id desc) "
		            + " where rownum <=?) " + " where rn>?";
			
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			pstmt.setInt(2, cri.getAmount() * cri.getPageNum()); // 10*2
			pstmt.setInt(3, cri.getAmount() * (cri.getPageNum() - 1)); // 10
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryVO diary = new DiaryVO();

				diary.setPostId(rs.getInt("post_id"));
				diary.setHomepeeId(rs.getString("homepee_id"));
				diary.setTitle(rs.getString("title"));
				diary.setContent(rs.getString("content"));
				diary.setWriter(rs.getString("writer"));
				diary.setCreateDate(rs.getString("create_date"));
				diary.setViews(rs.getInt("views"));
				diary.setLikes(rs.getInt("likes"));
				diary.setLike_list(rs.getString("like_list"));
				diary.setFolder(rs.getString("folder"));

				listPage.add(diary);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return listPage;

	}

		public boolean deleteDiary(int postId) {
			String sql ="delete from diary where post_id=?";
			
			connect() ;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postId);
				
				int r = pstmt.executeUpdate();
				System.out.println("다이어리"+r+"건 삭제");
				
				if(r>0) {
					return true;
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return false;
		}

		public void updateDiary(DiaryVO vo) {

			String sql = "update diary set title=?, content=? where post_id=?";
			connect();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getPostId());

				int r = pstmt.executeUpdate();
				System.out.println(r + "건 수정");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
		}
		
		// 폴더생성
		public void insertGalleryFolder(DiaryFolderVO vo) {
			String sql = "insert into diaryfolder values(?, ?, ?)";
			connect();

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getFolderName());
				pstmt.setInt(2, vo.getSecret());
				pstmt.setString(3, vo.getHomepeeId());
				int r = pstmt.executeUpdate();
				System.out.println(r + "건 입력");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}

		}

		

}