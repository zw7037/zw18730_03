package com.pinyougou.shop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.entity.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	@RequestMapping("/register")
	public Result register(@RequestBody TbSeller seller){
		
		BCryptPasswordEncoder   passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(seller.getPassword());
		seller.setPassword(password);
		try {
			sellerService.save(seller);
			return new Result(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "");
		}
		
	}
	
}
