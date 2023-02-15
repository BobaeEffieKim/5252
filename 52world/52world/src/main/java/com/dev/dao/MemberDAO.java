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

import com.dev.vo.MemberVO;

public class MemberDAO {

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

	///////////////////////////////////////////////////////////// lgh

	// 프로필 수정
	public void updateProfile(MemberVO vo) {
		String sql = "update member set profile=?, introduce=? where id=?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getProfile());
			pstmt.setString(2, vo.getIntroduce());
			pstmt.setString(3, vo.getId());

			int r = pstmt.executeUpdate();

			System.out.println(r + "건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

///////////////////////////////////////////////////////////////kbb

	// 회원검색
	public MemberVO searchMember(String id) {
		String sql = "select * from member where id = '" + id + "'";
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setProfile(rs.getString("profile"));
				vo.setIntroduce(rs.getString("introduce"));
				vo.setDotori(rs.getInt("dotori"));
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	//////////////////////////////////////////////////////////////

	// 이름, 생일, 전화번호로 아이디 검색
	public MemberVO lookUpId(String name, String birth, String phone) {
		String sql = "select id from member where name = ?  " + "and birth=? "
				+ "                    and phone=? ";

		connect();
		System.out.println(name+birth+phone);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				
				return vo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	// 이름, id, 전화번호로 비밀번호 검색
	public MemberVO lookUpPassword(String name, String id, String phone) {
		String sql = "select * from member where name = ? " + "                    and id = ? "
				+ "                    and phone = ? ";

		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setProfile(rs.getString("profile"));
				vo.setIntroduce(rs.getString("introduce"));
				vo.setDotori(rs.getInt("dotori")); 
				return vo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	//////////////////////////////

	// 로그인 체크하는 메서드
	public int loginCheck(String id, String password) {

		String dbPw = ""; // db에서 꺼낸 비번 담을 변수
		int x = -1;

		// 입력된 아이디로 db에서 비번을 조회
		String sql = "SELECT password FROM member WHERE id = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 만약 입력된 아이디에 해당하는 비번이 있을경우
			if (rs.next()) {
				// 비번을 변수에 넣음
				dbPw = rs.getString("password");

				if (dbPw.equals(password)) {
					x = 1; // 넘겨받은 비번 = db비번 => 인증성공
				} else {
					x = 0; // 입력비번 != db비번 => 인증실패
				}

			} else {
				x = -1; // 해당 아이디가 없을 경우
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return x;

	}

	////////////////////////////////////////// nhj
	// 입력
	public void insertMember(MemberVO vo) {
		String sql = "insert into member(id,password,name,birth,gender,email,phone, profile) values(?,?,?,?,?,?,?,?)";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getPhone());
			pstmt.setString(8, "/52world/asset/default_profile.jpg");

			int r = pstmt.executeUpdate();

			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 아이디 조회
	public MemberVO selectId(String id) {
		String sql = "select * from member where id = '" + id + "'";
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));

				return vo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}
	

	//폰번호 조회 
	public MemberVO selectPhone(String phone) {
		String sql = "select * from member where phone = '" + phone + "'";
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));

				return vo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}


	// 아이디 중복체크
	public boolean checkId(String id) {

		String sql = "select count(*) from member where id= ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int r = pstmt.executeUpdate();
			if (r > 0) {
				// 동일 아이디가 있는 경우 1이 반환
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// 동일아이디가 없는 경우 0을 반환
		return false;
	}

	// 리스트
	public List<MemberVO> getList() {
		String sql = "select * from member order by 1";
		List<MemberVO> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));

				list.add(vo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 삭제-탈퇴
	public boolean DeleteMember(String id) {
		String sql = "delete member where id=?";
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 삭제");

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

//////////////////////////////////////////sek
// 이름으로 검색(친구찾기) 
//회원검색

	public MemberVO searchName(String name) {
		String sql = "select * from member where name = '" + name + "'";


		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setProfile(rs.getString("profile"));
				vo.setIntroduce(rs.getString("introduce"));
				vo.setDotori(rs.getInt("dotori"));
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

// 친구찾기 (리스트 형태로)
	public List<MemberVO> searchNameList(String name) {
		String sql = "SELECT id,password,name,to_char(BIRTH,'YYYY-MM-DD') AS birth, gender, email, phone, profile, INTRODUCE, DOTORI from member where name like '%" + name + "%'";
		List<MemberVO> list = new ArrayList<>();
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setProfile(rs.getString("profile"));
				vo.setIntroduce(rs.getString("introduce"));
				vo.setDotori(rs.getInt("dotori"));

				list.add(vo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 도토리 충전, 소비
	public void updateDotori(int price, String id) {
		try {
			connect();
			String sql = "update member set dotori=dotori-? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, id);
			int r = pstmt.executeUpdate();
			System.out.println(r+"건 수정");
		
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 정보변경
	public void updateInfo(MemberVO vo) {
		try {
			connect();
			String sql = "update member set password=?, name=?, birth=?, "
					+ " gender=?, email=?, phone=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getPassword());
			pstmt.setString(5, vo.getPassword());
			pstmt.setString(6, vo.getPassword());
			pstmt.setString(7, vo.getPassword());
			
			int r = pstmt.executeUpdate();
			System.out.println(r+"건 수정");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	

	// kbb) 비밀번호 재설정
	public void updatePw(MemberVO vo) {
		connect();
		String sql = "update member set password=? where id=? and name=? and phone=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			
			int r = pstmt.executeUpdate();
			System.out.println(r+"건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
}
		
	