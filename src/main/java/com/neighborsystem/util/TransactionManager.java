package com.neighborsystem.util;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/*
* 事务管理工具类
*
* 开启事务 提交事务 回滚事务 释放连接
*
* */
@Component("txManager")
@Aspect
public class TransactionManager {
	@Autowired
	private ConnectionUtil connectionUtil;



	public void beginTransaction(){
		try {
			connectionUtil.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commit(){
		try {
			connectionUtil.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void rollback(){
		try {
			connectionUtil.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void close(){
		try {
			connectionUtil.getConnection().close();  //还回池中
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void release(){
		try {
			//还回池中
			connectionUtil.getConnection().close();
			connectionUtil.removeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
