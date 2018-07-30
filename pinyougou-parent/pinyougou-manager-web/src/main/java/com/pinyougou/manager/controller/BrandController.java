package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.entity.PageResult;
import com.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

@RestController// RestController = @Controller+@ResponseBody
@RequestMapping("/brand")
public class BrandController {
	
	@Reference  //从dubbox中获取的对象
	private BrandService  brandService;
	
	
	@RequestMapping("/findBrandList")
	public Map  findBrandList(){
//		{data:[{id:1,text:"aaaa"},{id:2,text:"bbbb"},{id:3,text:"cccc"}]};
		return brandService.findBrandList();
	}
	
	@RequestMapping("/findAll")
	public List<TbBrand>  findAll(){
		return brandService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int pageSize){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return brandService.findPage(page,pageSize);
	}
	
	@RequestMapping("/search")
	public PageResult  search(int page,int pageSize,@RequestBody TbBrand brand){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return brandService.search(page,pageSize,brand);
	}
	
//	新增方法
	@RequestMapping("/add")
	public Result  add(@RequestBody TbBrand brand){
//		{success:true|false,message:"保存成功"|"保存失败"}
		 try {
			brandService.add(brand);
			return new Result(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "保存失败");
		}
	}
	
//	修改方法
	@RequestMapping("/update")
	public Result  update(@RequestBody TbBrand brand){
		try {
			brandService.update(brand);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
//	删除方法
	@RequestMapping("/del")
	public Result  del(Long[] ids){
		try {
			brandService.del(ids);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
//	根据id查询对象
	@RequestMapping("/findOne")
	public TbBrand  findOne(Long id){
		return brandService.findOne(id);
	}

}
