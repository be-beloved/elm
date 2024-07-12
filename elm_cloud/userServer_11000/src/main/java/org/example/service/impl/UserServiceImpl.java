package org.example.service.impl;

import example.org.entity.User;
import example.org.exception.ColumsNotEmptyException;
import example.org.exception.TelOrPasswordError;
import jakarta.annotation.Resource;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserByIdByPass(User user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

		User user1= userMapper.getUserByIdByPass(user);
		if (user1==null){
			throw new TelOrPasswordError("手机号或者密码错误");
		}
		return user1;
	}
	
	@Override
	public int getUserById(String userId) {
		return userMapper.getUserById(userId);
	}
	
	@Override
	public int saveUser(User user) {
		if (user.getUserId()==null||user.getPassword()==null||user.getUserSex()==null||user.getUserName()==null){
			throw new ColumsNotEmptyException("号码、密码、性别、用户名为空");
		}
		//加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

		return userMapper.saveUser(user);
	}
}
