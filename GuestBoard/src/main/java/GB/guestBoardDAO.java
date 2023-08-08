package GB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class guestBoardDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

	Connection connection = null;
	PreparedStatement preparedStatement;

	// DB연결
	public Connection open() {
		try {
			Class.forName(JDBC_DRIVER);
			// DB 연결 (DB url, user, pw)
			connection = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	} // open()

	// DB종료
	public void close() {
		try {
			connection.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // close()

	// 방명록 등록
	public void insert(guestBoard GB) {
		open();
		String sql = "INSERT INTO guestboard(nickname, content, date, password) values(?, ?, CURRENT_TIMESTAMP(), ?)";
		

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, GB.getNickname());
			preparedStatement.setString(2, GB.getContent());
			preparedStatement.setString(3, GB.getPassword());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	} // insert()

	// 방명록 리스트
	public List<guestBoard> getAll() {
		open();
		List<guestBoard> guestBoards = new ArrayList<>();

		String sql = "select aid, nickname, content, FORMATDATETIME(CAST(date AS TIMESTAMP), 'yyyy-MM-dd hh:mm:ss') as cdate, password from guestboard";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				guestBoard GB = new guestBoard();
				GB.setAid(resultSet.getInt("aid"));
				GB.setNickname(resultSet.getString("nickname"));
				GB.setContent(resultSet.getString("content"));
				GB.setDate(resultSet.getString("cdate"));
				GB.setPassword(resultSet.getString("password"));

				guestBoards.add(GB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return guestBoards;
	} // getAll()

	// 방명록 삭제
	public void delete(int aid) throws SQLException {
		open();
		String sql = "DELETE FROM guestboard WHERE aid = ?";
		preparedStatement = connection.prepareStatement(sql);

		try {
			preparedStatement.setInt(1, aid);
			// 삭제된 뉴스가 없을 경우 처리
			if (preparedStatement.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // delete()

}
