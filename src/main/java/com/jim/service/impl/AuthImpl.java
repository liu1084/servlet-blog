package com.jim.service.impl;

import com.jim.db.DataSource;
import com.jim.service.IAuth;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
public class AuthImpl implements IAuth {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthImpl.class);

	@Override
	public boolean checkUserExist(String username) {
		boolean result = false;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM user t1 ");
			stringBuilder.append(" WHERE t1.username = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, username);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				result = true;
			}

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public boolean checkPasswordValid(String username, String password) {
		boolean result = false;
		String salt = getSaltByUsername(username);

		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM user t1 ");
			stringBuilder.append(" WHERE 1=1 ");
			stringBuilder.append(" AND t1.username = ? ");
			stringBuilder.append(" AND t1.password = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, username);
			statement.setString(2, DigestUtils.shaHex(password + salt));
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				result = true;
			}

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private String getSaltByUsername(String username) {
		String result = "";
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM `user` t1 ");
			stringBuilder.append(" WHERE t1.username = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getString("salt");
			}


		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
