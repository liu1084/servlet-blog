package com.jim.service;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
public interface IAuth {
	Boolean checkUserExist(String username);

	Boolean checkPasswordValid(String username, String password);
}
