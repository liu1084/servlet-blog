package com.jim.service;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
public interface IAuth {
	boolean checkUserExist(String username);

	boolean checkPasswordValid(String username, String password);
}
