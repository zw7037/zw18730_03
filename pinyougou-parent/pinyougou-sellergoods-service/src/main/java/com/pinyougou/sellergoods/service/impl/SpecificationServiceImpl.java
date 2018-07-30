package com.pinyougou.sellergoods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.entity.PageResult;
import com.entityGroup.Specification;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.sellergoods.service.SpecificationService;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	
	@Override
	public List<TbSpecification> findAll() {
		TbSpecificationExample  example = new TbSpecificationExample();
		/**
		 * criteria作为存放条件的对象  name  firstChar
		 */
		Criteria criteria = example.createCriteria();
//		criteria.andNameLike("%小%");
		
		return specificationMapper.selectByExample(example);
	}


	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page page = (Page) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification = specification.getTbSpecification();
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		
//		注意：数据插入后需要获取id，做法：修改mapper中的insert方法 在其中添加selectKey
		specificationMapper.insert(tbSpecification);
		
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(tbSpecificationOption);
		}
		
	}


	@Override
	public Specification findOne(Long id) {
//		需要获取一个组合类 ，得查询两次
		
		Specification specification = new Specification();
		
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(id);
		
//		根据规格id查询规格小项
		List<TbSpecificationOption> tbSpecificationOptionList = specificationOptionMapper.selectByExample(example);
		
		specification.setSpecificationOptionList(tbSpecificationOptionList);
		specification.setTbSpecification(tbSpecification);
		
		return specification;
	}


	@Override
	public void update(Specification specification) {
		
		TbSpecification tbSpecification = specification.getTbSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		
		
//		规格小项
//		需要先把原来的规格小项删除
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(tbSpecification.getId());
		specificationOptionMapper.deleteByExample(example);
		
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		
//		再重新添加新的规格小项
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(tbSpecificationOption);
		}
		
//		specificationMapper.updateByPrimaryKey(specification);
	}


	@Override
	public void del(Long[] ids) {
		for (Long id : ids) {
			specificationMapper.deleteByPrimaryKey(id);
		}
	}


	@Override
	public PageResult search(int pageNum, int pageSize, TbSpecification specification) {
		PageHelper.startPage(pageNum, pageSize) ;
		TbSpecificationExample example = new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(specification.getSpecName())) {
			criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
		}
		
		Page page = (Page) specificationMapper.selectByExample(example);
		
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public Map findSpecList() {
		List<Map> list = specificationMapper.findSpecList();
		
		Map map = new HashMap<>();
		map.put("data", list);
		return map;
	}

}
