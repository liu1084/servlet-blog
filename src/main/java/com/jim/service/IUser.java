package com.jim.service;

import com.jim.entity.User;

import java.util.List;

/**
 * Created by jim on 2017/7/26.
 * This class is ...
 */
public interface IUser {
	User add(User user);

	Boolean delete(List<User> users);

	Boolean deleteUser(User user);

	int update(User user);

	List<User> find();

	User findById(String id);

	User findByEmail(String email);

	User findByUsername(String username);
}
