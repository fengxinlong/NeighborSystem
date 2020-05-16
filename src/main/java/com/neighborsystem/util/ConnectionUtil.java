package com.neighborsystem.util;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionUtil {
	private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection(){

		try{

		Connection conn = tl.get();

		//判断是否有链接
		if(conn==null){
			conn=dataSource.getConnection();
			tl.set(conn);
		}
		//返回
		return conn;
		}catch (Exception e){
			throw new RuntimeException();
		}

	}
	public void removeConnection(){
			tl.remove();
	}
}
