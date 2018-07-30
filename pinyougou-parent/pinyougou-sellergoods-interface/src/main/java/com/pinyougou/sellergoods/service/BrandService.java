package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

public interface BrandService {
	
	public List<TbBrand> findAll();

	public PageResult findPage(int page, int pageSize);

	public void add(TbBrand brand);

	public TbBrand findOne(Long id);

	public void update(TbBrand brand);

	public void del(Long[] ids);

	public PageResult search(int page, int pageSize, TbBrand brand);

	public Map findBrandList();

}
