package lhvote.dao.impl;

import java.sql.Connection;

public class DatabaseAccessObject {
	
	Connection connection;
	
	public DatabaseAccessObject(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}
}
