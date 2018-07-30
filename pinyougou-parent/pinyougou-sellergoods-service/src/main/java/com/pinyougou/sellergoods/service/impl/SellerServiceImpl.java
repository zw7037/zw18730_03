package com.pinyougou.sellergoods.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.entity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.pojo.TbSellerExample.Criteria;
import com.pinyougou.sellergoods.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private TbSellerMapper tbSellerMapper;
	
	@Override
	public void save(TbSeller seller) {
		seller.setCreateTime(new Date());
		//未审核状态
		seller.setStatus("0");
		tbSellerMapper.insert(seller);
	}

	@Override
	public List<TbSeller> findAll() {
		return tbSellerMapper.selectByExample(null);
	}

	@Override
	public PageResult search(int pageNo, int pageSize, TbSeller seller) {
		TbSellerExample example = new TbSellerExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(seller.getStatus())) {
			criteria.andStatusEqualTo(seller.getStatus());
		}
		PageHelper.startPage(pageNo, pageSize);
		Page<TbSeller> page = (Page<TbSeller>) tbSellerMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public PageResult findPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<TbSeller> page = (Page<TbSeller>) tbSellerMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public TbSeller findOne(String id) {
		return tbSellerMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateStatus(String sellerId, String status) {
		
//		TbSeller seller = tbSellerMapper.selectByPrimaryKey(sellerId);
//		seller.setStatus(status);
//		tbSellerMapper.updateByPrimaryKey(seller);
		Map map = new HashMap<>();
		map.put("sellerId", sellerId);
		map.put("status", status);
		tbSellerMapper.updateStatus(map);
		
	}

	@Override
	public TbSeller findByUsername(String username) {
		TbSellerExample example = new TbSellerExample();
		Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(username);
		List<TbSeller> list = tbSellerMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
