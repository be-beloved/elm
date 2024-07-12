package org.example.service;


import example.org.entity.User;

public interface UserService {

	public User getUserByIdByPass(User user);
	public int getUserById(String userId);

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
}
