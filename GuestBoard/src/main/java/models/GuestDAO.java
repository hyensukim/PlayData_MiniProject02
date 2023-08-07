package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
	/**
	 * DAO
	 */
	
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jspCode";
	
	// 연결
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jspCode", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 연결
	 * 쿼리 실행 구문 객체 생성
	 * 쿼리문 내 변수 세팅
	 * executeUpdate() 실행
	 * @param guest
	 * @return
	 * @throws SQLException
	 */
	public long add(Guest guest) throws SQLException{
		String sql = "INSERT INTO guest(name, pass, title, content, date) "
				+ "VALUES(?,?,?,?,NOW())";
		
		Connection conn = open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			String name = guest.getName();
			String pass = guest.getPass();
			String title = guest.getTitle();
			String content = guest.getContent();
			Date date = guest.getDate();
			
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			return pstmt.executeLargeUpdate();
		}
	}
	
	/**
	 * 삭제
	 * @param gId
	 * @return
	 * @throws SQLException
	 */
	public long delete(int gId) throws SQLException{
		String sql = "DELETE guest WHERE gid = ?";
		
		Connection conn = open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try(conn; pstmt){
			pstmt.setInt(1, gId);
			return pstmt.executeUpdate();
		}
	}
	
	/**
	 * 개별 방명록 조회
	 * @param gId
	 * @return
	 * @throws SQLException
	 */
	public Guest getGuest(int gId) throws SQLException{
		String sql = "SELECT * FROM guest WHERE gId = ?";
		
		Guest guest = new Guest();
		
		Connection conn = open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, gId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		try(conn; pstmt; rs){
			guest.setgId(rs.getInt("gId"));
			guest.setName(rs.getString("name"));
			guest.setPass(rs.getString("pass"));
			guest.setTitle(rs.getString("title"));
			guest.setContent(rs.getString("content"));
			guest.setDate(rs.getDate("date"));
			return guest;
		}
	}
	
	/**
	 * 방명록 목록 조회
	 * @return
	 * @throws SQLException
	 */
	public List<Guest> getGuests() throws SQLException{
		String sql = "SELECT * FROM guest";
		List<Guest> list = new ArrayList<>();
		
		Connection conn = open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
				
		try(conn; pstmt; rs){
			
			while(rs.next()) {
				Guest guest = new Guest();
				guest.setgId(rs.getInt("gId"));
				guest.setName(rs.getString("name"));
				guest.setPass(rs.getString("pass"));
				guest.setTitle(rs.getString("title"));
				guest.setContent(rs.getString("content"));
				guest.setDate(rs.getDate("date"));
				
				list.add(guest);
			}
			
			return list;
		}
	}
}
