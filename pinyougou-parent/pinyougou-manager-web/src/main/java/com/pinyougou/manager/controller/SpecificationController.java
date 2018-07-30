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
import com.entityGroup.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;

@RestController// RestController = @Controller+@ResponseBody
@RequestMapping("/specification")
public class SpecificationController {
	
	@Reference  //从dubbox中获取的对象
	private SpecificationService  specificationService;
	
	
	@RequestMapping("/findSpecList")
	public Map  findSpecList(){
//		{data:[{id:1,text:"aaaa"},{id:2,text:"bbbb"},{id:3,text:"cccc"}]};
		return specificationService.findSpecList();
	}
	
	@RequestMapping("/findAll")
	public List<TbSpecification>  findAll(){
		return specificationService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int pageSize){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return specificationService.findPage(page,pageSize);
	}
	
	@RequestMapping("/search")
	public PageResult  search(int page,int pageSize,@RequestBody TbSpecification specification){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return specificationService.search(page,pageSize,specification);
	}
	
//	新增方法
	@RequestMapping("/add")
	public Result  add(@RequestBody Specification specification){
		 try {
			specificationService.add(specification);
			return new Result(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "保存失败");
		}
	}
	
//	修改方法
	@RequestMapping("/update")
	public Result  update(@RequestBody Specification specification){
		try {
			specificationService.update(specification);
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
			specificationService.del(ids);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
//	根据id查询对象
	@RequestMapping("/findOne")
	public Specification  findOne(Long id){
		return specificationService.findOne(id);
	}

}
