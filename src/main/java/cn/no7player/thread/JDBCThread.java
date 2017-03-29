package cn.no7player.thread;

import java.util.concurrent.CountDownLatch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class JDBCThread {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " is running...");
		int threadnum = 5;
		CountDownLatch cnt = new CountDownLatch(threadnum);
		for (int i = 0; i < threadnum; i++) {
			JDBCwork jt = new JDBCwork(cnt);
			jt.start();
		}
		try {
			cnt.await();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is done...");
	}

}

class JDBCwork extends Thread {
	private CountDownLatch cnt;
	// private final ReentrantLock lock = new ReentrantLock();
	private Connection conn;
	private Statement st;

	public JDBCwork() {
	}

	public JDBCwork(CountDownLatch cnt) {
		this.cnt = cnt;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running...");
		try {
			this.connectDB();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is done...");
		cnt.countDown();
	}

	public synchronized void connectDB() throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://112.74.36.159:3306/user";
		String usr = "root";
		String psw = "yasong";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			System.out.println("加载驱动失败.");
			e1.printStackTrace();
		}
		System.out.println("MySQL JDBC Driver Registered!");
		try {
			conn = DriverManager.getConnection(url, usr, psw);
		} catch (SQLException e) {

			System.out.println("connection failed .");
			e.printStackTrace();
		}
		System.out.println("connected!");
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select name from user where id =1";
		ResultSet rt = st.executeQuery(sql);
		System.out.println(Thread.currentThread().getName() + " is querying...");
		while (rt.next()) {
			String name = rt.getString("name");
			System.out.println("query result: " + name);
		}
		rt.close();
		conn.close();
	}

}