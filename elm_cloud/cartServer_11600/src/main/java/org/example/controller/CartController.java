package org.example.controller;

import java.util.List;

import example.org.entity.Cart;
import example.org.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/CartController")
@Slf4j
public class CartController {

	@Resource
	private CartService cartService;

	/**
	 * 通过用户id、商家id获取购物车列表
	 * @param businessId
	 * @return
	 * @throws Exception
	 */

	@GetMapping("/getcartList")
	public Result<List<Cart>> listCart(@RequestParam("userId") Integer userId, @RequestParam(value = "businessId",required = false) Integer businessId) throws Exception{
		log.info("获取购物车列表：userId={}, businessId={}", userId, businessId);
		Cart cart = new Cart();
		cart.setUserId(String.valueOf(userId));
		cart.setBusinessId(businessId);
		List<Cart> cartList = cartService.listCart(cart);
		return Result.success(cartList);
	}

	/**
	 * 加入购物车
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/cart")
	public Result saveCart(@RequestBody Cart cart) throws Exception{
		log.info("加入购物车：{}",cart);
		 cartService.saveCart(cart);
		 return Result.success();
	}

	/**
	 * 更新购物车
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/cart")
	public Result updateCart(@RequestBody Cart cart) throws Exception{
		log.info("更新购物车：{}",cart);
		 cartService.updateCart(cart);
		return Result.success();
	}

	/**
	 * 移除购物车
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/cart")
	public Result removeCart(@RequestParam("userId") Integer userId, @RequestParam(value = "businessId",required = false) Integer businessId) throws Exception{
		log.info("删除购物车：userId={}, businessId={}", userId, businessId);
		Cart cart = new Cart();
		cart.setUserId(String.valueOf(userId));
		cart.setBusinessId(businessId);
		 cartService.removeCart(cart);
		return Result.success();
	}
}
