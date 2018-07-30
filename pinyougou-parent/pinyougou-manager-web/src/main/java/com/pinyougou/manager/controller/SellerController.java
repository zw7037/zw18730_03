package com.pinyougou.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.entity.PageResult;
import com.entity.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference  //从dubbox中获取的对象
	private SellerService  sellerService;
	
	
	@RequestMapping("/updateStatus")
	public Result updateStatus(String sellerId,String status) {
		try {
			sellerService.updateStatus(sellerId,status);
			return new Result(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "状态调整失败");
		}
		
	}
	
	@RequestMapping("/findAll")
	public List<TbSeller>  findAll(){
		return sellerService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int pageSize){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return sellerService.findPage(page,pageSize);
	}
	
	@RequestMapping("/search")
	public PageResult  search(int page,int pageSize,@RequestBody TbSeller seller){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return sellerService.search(page,pageSize,seller);
	}
	
	
	
//	根据id查询对象
	@RequestMapping("/findOne")
	public TbSeller  findOne(String id){
		return sellerService.findOne(id);
	}

	
	
}
