package lhvote.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

	private static final int POOL_SIZE = 5;
	
	private final String driverClassName;
	private final String url;
	private final String userName;
	private final String password;
	
	private Queue<Connection> pool = new LinkedList<Connection>();
	
	public ConnectionPool(String driverClassName, String url, String userName, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.userName = userName;
		this.password = password;
		initialize();
	}
	
	private void initialize() {
		loadDriverClass();
		makeConnectionPool();
	}

	private void loadDriverClass() {
		try {
			Class.forName(this.driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void makeConnectionPool() {
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				pool.add(DriverManager.getConnection(this.url, this.userName, this.password));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized Connection getConnection() {
		while (pool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return pool.poll();
	}
	
	public synchronized void releaseConnection(Connection connection) {
		pool.offer(connection);
		notifyAll();
	}
}
