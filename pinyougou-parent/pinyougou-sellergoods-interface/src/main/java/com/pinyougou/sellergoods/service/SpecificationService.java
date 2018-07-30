package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.entity.PageResult;
import com.entityGroup.Specification;
import com.pinyougou.pojo.TbSpecification;

public interface SpecificationService {
	
	public List<TbSpecification> findAll();

	public PageResult findPage(int page, int pageSize);

	public void add(Specification specification);

	public Specification findOne(Long id);

	public void update(Specification specification);

	public void del(Long[] ids);

	public PageResult search(int page, int pageSize, TbSpecification specification);

	public Map findSpecList();

}
