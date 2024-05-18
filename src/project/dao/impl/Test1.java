package project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project.util.DatabaseConnectionFactory;

public class Test1 {
	public static void main(String[] args) {
		try (PreparedStatement statement = DatabaseConnectionFactory.getConnection()
				.prepareStatement("insert into table1(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setString(i++, "Test2");
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating room failed, no rows affected.");
			}
			
			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			        System.out.println(rs.getInt(1));
			    } else {
			    	System.out.println("No id generated");
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
