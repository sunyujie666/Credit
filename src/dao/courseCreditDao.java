package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class courseCreditDao {

	private Connection conn = null;

	public void initConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hdu",
				"", "");
	}

	public void addCourseCredit(String sql) throws Exception {
		initConnection();
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		conn.close();
	}
}
