package com.neighborsystem.util;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionUtils {
	private  ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	* 获取当前线程上的连接
	* */
	public Connection getThreadConnection(){

		try {
		//1.从Threadlocal上获取

		Connection conn = tl.get();
		//2判断当前线程上是否有连接
		if(conn == null){
			//3.从数据源中获取一个连接,并且存入ThreadLocal中
			conn=dataSource.getConnection();
			tl.set(conn);
		}
		//4.返回当前线程上的连接
			return conn;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
