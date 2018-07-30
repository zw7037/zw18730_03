package com.pinyougou.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.entity.PageResult;
import com.entity.Result;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;

@RestController// RestController = @Controller+@ResponseBody
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	
	@Reference  //从dubbox中获取的对象
	private TypeTemplateService  typeTemplateService;
	
	
	
	@RequestMapping("/findSpecList")
	public List<Map>  findSpecList(Long id){
		
		return typeTemplateService.findSpecList(id);
		
	}
	
	
	
	@RequestMapping("/findAll")
	public List<TbTypeTemplate>  findAll(){
		return typeTemplateService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int pageSize){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return typeTemplateService.findPage(page,pageSize);
	}
	
	@RequestMapping("/search")
	public PageResult  search(int page,int pageSize,@RequestBody TbTypeTemplate typeTemplate){
//		 此方法返回 1、总条数 long  2、当前页的数据list
		return typeTemplateService.search(page,pageSize,typeTemplate);
	}
	
//	新增方法
	@RequestMapping("/add")
	public Result  add(@RequestBody TbTypeTemplate typeTemplate){
//		{success:true|false,message:"保存成功"|"保存失败"}
		 try {
			typeTemplateService.add(typeTemplate);
			return new Result(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "保存失败");
		}
	}
	
//	修改方法
	@RequestMapping("/update")
	public Result  update(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
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
			typeTemplateService.del(ids);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
//	根据id查询对象
	@RequestMapping("/findOne")
	public TbTypeTemplate  findOne(Long id){
		return typeTemplateService.findOne(id);
	}

}
