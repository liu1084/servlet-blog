package com.jim.service.impl;

import com.jim.db.DataSource;
import com.jim.entity.User;
import com.jim.service.IUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jim on 2017/7/27.
 * This class is ...
 */
public class UserImpl implements IUser {
	@Override
	public User add(User user) {
		User result = new User();
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" INSERT INTO user (username, password, email, salt) VALUES (?,?,?,?)");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, user.getUsername());
			String salt = UUID.randomUUID().toString();
			statement.setString(2, DigestUtils.shaHex(user.getPassword() + salt));
			statement.setString(3, user.getEmail());
			statement.setString(4, salt);

			if (statement.execute()) {
				result = user;
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

	@Override
	public List<User> delete(List<User> users) {
		int length = users.size();
		Boolean[] result = new Boolean[length];
		for (int i = 0; i < length; i++) {
			result[i] = deleteUser(users.get(i));
		}

		if (ArrayUtils.contains(result, false)) {
			return new ArrayList<>();
		}
		return users;
	}

	private Boolean deleteUser(User user) {
		Boolean result = false;
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" DELETE FROM user t1 WHERE t1.id = ? ");

			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, user.getId().toString());

			if (statement.execute()) {
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

	@Override
	public int update(User user) {
		int result = 0;
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE user t1 SET t1.username = ? , t1.email = ? WHERE 1 = 1 ");
			stringBuilder.append(" AND t1.id = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getId().toString());

			result = statement.executeUpdate();

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

	@Override
	public List<User> find() {
		List<User> result = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT t1.id, t1.username, t1.email FROM user t1 WHERE 1 = 1");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				result.add(user);
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

	@Override
	public User findById(String id) {
		User result = new User();
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM user t1 WHERE 1 = 1");
			stringBuilder.append(" AND t1.id = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.setId(resultSet.getInt(1));
				result.setUsername(resultSet.getString(2));
				result.setEmail(resultSet.getString(3));
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

	@Override
	public User findByEmail(String email) {
		User result = new User();
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM user t1 WHERE 1 = 1");
			stringBuilder.append(" AND t1.email = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.setId(resultSet.getInt(1));
				result.setUsername(resultSet.getString(2));
				result.setEmail(resultSet.getString(3));
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

	@Override
	public User findByUsername(String username) {
		User result = new User();
		Connection connection = null;
		try {
			connection = DataSource.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT * FROM user t1 WHERE 1 = 1");
			stringBuilder.append(" AND t1.username = ? ");
			PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.setId(resultSet.getInt(1));
				result.setUsername(resultSet.getString(2));
				result.setEmail(resultSet.getString(3));
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
