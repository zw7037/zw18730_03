package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.entity.PageResult;
import com.pinyougou.pojo.TbTypeTemplate;

public interface TypeTemplateService {
	
	public List<TbTypeTemplate> findAll();

	public PageResult findPage(int page, int pageSize);

	public void add(TbTypeTemplate typeTemplate);

	public TbTypeTemplate findOne(Long id);

	public void update(TbTypeTemplate typeTemplate);

	public void del(Long[] ids);

	public PageResult search(int page, int pageSize, TbTypeTemplate typeTemplate);

	public List<Map> findSpecList(Long id);

}
