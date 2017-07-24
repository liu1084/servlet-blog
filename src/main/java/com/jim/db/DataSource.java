package com.jim.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by jim on 2017/7/16.
 * This class is ...
 */
public class DataSource {
	private static DataSource dataSource;
	private ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

	private DataSource() throws PropertyVetoException {
		ResourceBundle config = ResourceBundle.getBundle("database", Locale.CHINA);
		this.comboPooledDataSource.setAcquireIncrement(5);
		this.comboPooledDataSource.setJdbcUrl(config.getString("url"));
		this.comboPooledDataSource.setUser(config.getString("username"));
		this.comboPooledDataSource.setPassword(config.getString("password"));
		this.comboPooledDataSource.setDriverClass(config.getString("driverClass"));
	}

	public static DataSource getInstance() throws PropertyVetoException {
		if (dataSource == null) {
			dataSource = new DataSource();
		}
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		return this.comboPooledDataSource.getConnection();
	}

}
