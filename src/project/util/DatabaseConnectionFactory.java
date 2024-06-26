package project.util;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
	private static final Logger LOGGER = System.getLogger(DatabaseConnectionFactory.class.getName());
	private static Connection connection = null;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String DATABASE = "resort_db";
	private static final String OPTIONS = "?useSSL=false&serverTimezone=Asia/Manila";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private DatabaseConnectionFactory() {
	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				LOGGER.log(Level.INFO, "JDBC connection is null or closed, trying to connect");
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL + "/" + DATABASE + OPTIONS, USER, PASSWORD);

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
