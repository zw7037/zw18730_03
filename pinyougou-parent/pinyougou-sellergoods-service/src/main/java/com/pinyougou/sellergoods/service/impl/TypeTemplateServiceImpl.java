package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.entity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.sellergoods.service.TypeTemplateService;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	
	@Autowired
	private TbSpecificationOptionMapper  specificationOptionMapper;
	
	
	@Override
	public List<TbTypeTemplate> findAll() {
		TbTypeTemplateExample  example = new TbTypeTemplateExample();
		/**
		 * criteria作为存放条件的对象  name  firstChar
		 */
		Criteria criteria = example.createCriteria();
//		criteria.andNameLike("%小%");
		
		return typeTemplateMapper.selectByExample(example);
	}


	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page page = (Page) typeTemplateMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public void add(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.insert(typeTemplate);
	}


	@Override
	public TbTypeTemplate findOne(Long id) {
		return typeTemplateMapper.selectByPrimaryKey(id);
	}


	@Override
	public void update(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}


	@Override
	public void del(Long[] ids) {
		for (Long id : ids) {
			typeTemplateMapper.deleteByPrimaryKey(id);
		}
	}


	@Override
	public PageResult search(int pageNum, int pageSize, TbTypeTemplate typeTemplate) {
		PageHelper.startPage(pageNum, pageSize) ;
		TbTypeTemplateExample example = new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(typeTemplate.getName())) {
			criteria.andNameLike("%"+typeTemplate.getName()+"%");
		}
		Page page = (Page) typeTemplateMapper.selectByExample(example);
		
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public List<Map> findSpecList(Long id) {
		TbTypeTemplate typeTemplate = findOne(id);
//		{"brandIds":"[{"id":1,"text":"联想"},{"id":3,"text":"三星"},{"id":2,"text":"华为"},{"id":5,"text":"OPPO"},{"id":4,"text":"小米"},{"id":9,"text":"苹果"},{"id":8,"text":"魅族"},{"id":6,"text":"360"},{"id":10,"text":"VIVO"},{"id":11,"text":"诺基亚"},{"id":12,"text":"锤子"}]",
//			"customAttributeItems":" [{"text":"配送方式"},{"text":"是否支持验机"}]",
//			"id":35,"name":"手机",
//			"specIds":"[{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]"}
		
		
		String specIds = typeTemplate.getSpecIds();
//		"[{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]"
//		由字符串转成集合
		List<Map> specMap = JSON.parseArray(specIds, Map.class);
		
		for (Map map : specMap) {
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(Long.parseLong(map.get("id")+""));
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			map.put("options", options);
		}
		
//		数据格式
//		[{"id":27,"text":"网络",options:[{id:1,optionName:"联通3G"},{id:2,optionName:"联通4G"},{id:3,optionName:"联通2G"}]},
//		{"id":32,"text":"机身内存",options:[{id:4,optionName:"16G"},{id:5,optionName:"32G"},{id:6,optionName:"64G"}]}]
		
		return specMap;
	}

}
