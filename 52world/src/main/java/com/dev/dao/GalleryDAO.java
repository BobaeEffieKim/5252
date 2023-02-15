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
import com.dev.vo.GalleryFolderVO;
import com.dev.vo.GalleryVO;
import com.dev.vo.GuestProfileVO;
import com.dev.vo.MemberVO;

// kbb) 사집첩 관련 DAO

public class GalleryDAO {

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
///////////////////////////////////////////////////////

	// 사진 올리기
	public void insertGallery(GalleryVO vo) {

		String sql = "insert into gallery(post_id, homepee_id, title, content, "
				+ "img, writer, create_date, views, likes, like_list, folder) "
				+ "values((select nvl(max(post_id),0)+1 from gallery), ?, ?, ?, ?, 'user1', sysdate, 0, ?, ?, ? ) ";

		connect();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getHomepeeId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getImg());
			pstmt.setInt(5, vo.getLikes());
			pstmt.setString(6, vo.getLikeList());
			pstmt.setString(7, vo.getFolder());

			// pstmt.setString(5, vo.getWriter());
			// pstmt.setString(6, vo.getLikeList());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 입력");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	/*
	 * // 사진 올리기 public void insertGallery(GalleryVO vo) {
	 * 
	 * String sql = "insert into gallery(post_id, homepee_id, title, content, " +
	 * "img, writer, create_date, views, likes, like_list, folder) " +
	 * "values((select nvl(max(post_id),0)+1 from gallery), ?, ?, ?, ?, 'user1', sysdate, 0, 0, '좋아요', ? ) "
	 * ;
	 * 
	 * connect();
	 * 
	 * try { pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, vo.getHomepeeId()); pstmt.setString(2, vo.getTitle());
	 * pstmt.setString(3, vo.getContent()); pstmt.setString(4, vo.getImg());
	 * pstmt.setString(5, vo.getFolder()); // pstmt.setString(5, vo.getWriter()); //
	 * pstmt.setString(6, vo.getLikeList());
	 * 
	 * int r = pstmt.executeUpdate(); System.out.println(r + "건 입력");
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { disconnect(); }
	 * 
	 * }
	 */

	// 사진 정보 변경
	public void updateGallery(GalleryVO vo) {

		String sql = "update gallery " + "set title = ? " + ", content = ? " + ", folder = ? " + "where post_id = ? ";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFolder());
			pstmt.setInt(4, vo.getPostId());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 변경");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	/*
	 * // 사진 삭제 public void deleteGallery(GalleryVO vo) {
	 * 
	 * String sql = "DELETE FROM gallery WHERE post_id = ?"; connect(); try { pstmt
	 * = conn.prepareStatement(sql); pstmt.setInt(1, vo.getPostId());
	 * 
	 * int r = pstmt.executeUpdate(); System.out.println(r + "건 삭제"); } catch
	 * (SQLException e) { e.printStackTrace(); } finally { disconnect(); }
	 * 
	 * }
	 */
	public boolean deleteGallery(int postId) {
		String sql = "delete from gallery where post_id = ?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);

			int r = pstmt.executeUpdate();
			System.out.println("사진" + r + "건 삭제");

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

	// 갤러리 전체 보기 리스트

	public List<GalleryVO> galleryList(String homepeeId, String folder) {
		String sql = "Select g.* from gallery g, galleryfolder f " + "where 1=1  " + "and g.homepee_id = f.homepee_id "
				+ "and g.folder = f.folder_name " + "and g.homepee_id = ? "
				+ "and f.folder_name = nvl(?, f.folder_name) " + "order by g.post_id desc ";
		List<GalleryVO> list = new ArrayList<>();
		connect();

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			pstmt.setString(2, folder);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				GalleryVO vo = new GalleryVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImg(rs.getString("img"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setViews(rs.getInt("views"));
				vo.setLikes(rs.getInt("likes"));
				vo.setLikeList(rs.getString("like_list"));
				vo.setFolder(rs.getString("folder"));

				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	/*
	 * public List<GalleryVO> galleryList() { String sql =
	 * "select * from gallery where homepee_id  order by create_date desc";
	 * List<GalleryVO> list = new ArrayList<>(); connect();
	 * 
	 * try { stmt = conn.createStatement(); rs = stmt.executeQuery(sql);
	 * 
	 * while (rs.next()) { GalleryVO vo = new GalleryVO();
	 * vo.setPostId(rs.getInt("post_id"));
	 * vo.setHomepeeId(rs.getString("homepee_id"));
	 * vo.setTitle(rs.getString("title")); vo.setContent(rs.getString("content"));
	 * vo.setImg(rs.getString("img")); vo.setWriter(rs.getString("writer"));
	 * vo.setCreateDate(rs.getString("create_date"));
	 * vo.setViews(rs.getInt("views")); vo.setLikes(rs.getInt("likes"));
	 * vo.setLikeList(rs.getString("like_list"));
	 * vo.setFolder(rs.getString("folder"));
	 * 
	 * list.add(vo);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } finally { disconnect(); }
	 * return list; }
	 */

	///////////////////////////////////////////////////////

	// 폴더생성
	public void insertGalleryFolder(GalleryFolderVO vo) {
		String sql = "insert into galleryfolder values(?, ?, ?)";
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

	// 폴더명 검색
	public GalleryFolderVO searchFName(String folderName) {
		String sql = "select * from galleryfolder where folder_name = '" + folderName + "'";
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				GalleryFolderVO gvo = new GalleryFolderVO();
				gvo.setFolderName(rs.getString("folderName"));

				return gvo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	// 게시번호 검색
	public GalleryVO searchById(int postId) {
		String sql = "select * from gallery where post_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				GalleryVO vo = new GalleryVO();
				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImg(rs.getString("img"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setViews(rs.getInt("views"));
				vo.setLikes(rs.getInt("likes"));
				vo.setLikeList(rs.getString("like_list"));
				vo.setFolder(rs.getString("folder"));

				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	/*
	 * stmt = conn.createStatement(); rs = stmt.executeQuery(sql); if (rs.next()) {
	 * GalleryVO vo = new GalleryVO(); vo.setPostId(rs.getInt("postId"));
	 * 
	 * return vo; } } catch (SQLException e) { e.printStackTrace(); } finally {
	 * disconnect(); } return null;
	 */

	// 폴더리스트 전체보기
	public List<GalleryFolderVO> galleryFolderList() {
		String sql = "select * from galleryfolder";
		List<GalleryFolderVO> list = new ArrayList<>();
		connect();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GalleryFolderVO vo = new GalleryFolderVO();

				vo.setFolderName(rs.getString("folder_name"));
				vo.setSecret(rs.getInt("secret"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 사용자별 폴더리스트보기
	public List<GalleryFolderVO> galleryFListByHId(String homepeeId) {
		String sql = "select * from galleryfolder " + "where homepee_id = ?";
		List<GalleryFolderVO> gflist = new ArrayList<>();
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GalleryFolderVO vo = new GalleryFolderVO();

				vo.setFolderName(rs.getString("folder_name"));
				vo.setSecret(rs.getInt("secret"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				gflist.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return gflist;
	}

	// 폴더 정보 수정 (이름, 공개여부)
	public void updateGalleryFolder(String folderName, String editFolderName, int secret, String homepeeId) {

		System.out.println(homepeeId + "다오에서찍은거");
		String sql = "update galleryfolder " + "set folder_name = ? " + ", secret = ? "
				+ "where folder_name=? and homepee_id= ? ";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, editFolderName);
			pstmt.setInt(2, secret);
			pstmt.setString(3, folderName);
			pstmt.setString(4, homepeeId);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 변경");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 폴더 삭제
	public void deleteGalleryFolder(GalleryFolderVO vo) {

		String sql = "DELETE FROM galleryfolder WHERE folder_name = ? and homepee_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getFolderName());
			pstmt.setString(2, vo.getHomepeeId());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 폴더 이동
	public void moveGalleryFolder(String folderName, String editFolderName, String homepeeId) {

		System.out.println(homepeeId + "다오에서찍은거");
		String sql = "update gallery set folder=? where folder=? and homepee_id=?";

		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, editFolderName);
			pstmt.setString(2, folderName);
			pstmt.setString(3, homepeeId);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 이동완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////

	// 좋아요 수 올리기
	public void likes(GalleryVO vo) {

		String sql = "UPDATE gallery SET likes=? , like_list=? where post_id= ? ";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getLikes());
			pstmt.setString(2, vo.getLikeList());
			pstmt.setInt(3, vo.getPostId());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 반영");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public void likesCopy(String likeList, int postId) {

		String sql = "UPDATE gallery SET likes= (select nvl(max(likes),0)+1 from gallery), like_list=? where post_id= ? ";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, likeList);
			pstmt.setInt(2, postId);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 반영");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	///////////////////////////////////////////////////
	// 페이징
	public List<GalleryVO> GalleryPaging(String homepeeId, Criteria cri, String fNm) {
		List<GalleryVO> list = new ArrayList<>();
		String sql = "select  post_id, homepee_id, title, content, img, writer,create_date,views,likes,like_list, folder  "
				+ "from (select rownum rn, post_id, homepee_id, title, content, img, writer,create_date,views,likes,like_list, folder   "
				+ "from (select  post_id, homepee_id, title, content, img, writer, TO_CHAR(create_date,'YYYY.MM.DD DY HH24:MI') as create_date, views, likes, like_list, folder from gallery where homepee_id=?  and folder = nvl(?, folder) order by post_id desc)  "
				+ "where rownum <=?) where rn>?";

		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homepeeId);
			pstmt.setString(2, fNm);
			pstmt.setInt(3, cri.getAmount() * cri.getPageNum());
			pstmt.setInt(4, cri.getAmount() * (cri.getPageNum() - 1));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GalleryVO vo = new GalleryVO();

				vo.setPostId(rs.getInt("post_id"));
				vo.setHomepeeId(rs.getString("homepee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImg(rs.getString("img"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreateDate(rs.getString("create_date"));
				vo.setViews(rs.getInt("views"));
				vo.setLikes(rs.getInt("likes"));
				vo.setLikeList(rs.getString("like_list"));
				vo.setFolder(rs.getString("folder"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	   public void deleteGallery(GalleryFolderVO vo) {
		      String sql = "DELETE FROM gallery WHERE folder = ? and homepee_id = ?";
		      connect();
		      try {
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, vo.getFolderName());
		         pstmt.setString(2, vo.getHomepeeId());

		         int r = pstmt.executeUpdate();
		         System.out.println(r + "건 삭제");
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         disconnect();
		      }
		      
		   }

}