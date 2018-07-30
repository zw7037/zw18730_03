package com.pinyougou.sellergoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.entity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper brandMapper;
	
	
	@Override
	public List<TbBrand> findAll() {
		TbBrandExample  example = new TbBrandExample();
		/**
		 * criteria作为存放条件的对象  name  firstChar
		 */
		Criteria criteria = example.createCriteria();
//		criteria.andNameLike("%小%");
		
		return brandMapper.selectByExample(example);
	}


	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page page = (Page) brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
	}


	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}


	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}


	@Override
	public void del(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}


	@Override
	public PageResult search(int pageNum, int pageSize, TbBrand brand) {
		PageHelper.startPage(pageNum, pageSize) ;
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(brand.getName())) {
			criteria.andNameLike("%"+brand.getName()+"%");
		}
		if(StringUtils.isNotEmpty(brand.getFirstChar())) {
			criteria.andFirstCharEqualTo(brand.getFirstChar().toUpperCase());
		}
		Page page = (Page) brandMapper.selectByExample(example);
		
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public Map findBrandList() {
		Map  map = new HashMap<>();
//		{data:[{id:1,text:"aaaa"},{id:2,text:"bbbb"},{id:3,text:"cccc"}]};
		List<Map> list = brandMapper.findBrandList();
//		小Map  {id:1,text:"aaaa"}
		map.put("data", list);
		return map;
	}

}
