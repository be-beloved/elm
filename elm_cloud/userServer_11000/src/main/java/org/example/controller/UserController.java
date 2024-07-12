package org.example.controller;

import example.org.entity.User;
import example.org.properties.JwtProperties;
import example.org.result.Result;
import example.org.utils.JwtUtil;
import example.org.vo.UserVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/UserController")
@Slf4j
public class UserController {
	
	@Resource
	private UserService userService;

	@Resource
	private JwtProperties jwtProperties;
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	@PostMapping("/getUserByIdByPass")
	public Result<UserVO> getUserByIdByPass(User user) {
		log.info("用户登陆：{}",user);
		User user1= userService.getUserByIdByPass(user);

		//登录成功后，生成jwt令牌
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user1.getUserId());
		String token = JwtUtil.createJWT(
				jwtProperties.getUserSecretKey(),
				jwtProperties.getUserTtl(),
				claims);

		UserVO userVO=new UserVO();
		BeanUtils.copyProperties(user1,userVO);
		userVO.setToken(token);

		return Result.success(userVO);
	}
	
	@GetMapping ("/getUserById")
	public int getUserById(User user) {
		return userService.getUserById(user.getUserId());
	}

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/saveUser")
	public Result saveUser(@RequestBody User user) {
		log.info("用户注册：{}",user);
		 userService.saveUser(user);
		return Result.success();
	}
}
