package com.pinyougou.sellergoods.service;

import java.util.List;

import com.entity.PageResult;
import com.pinyougou.pojo.TbSeller;

public interface SellerService {

	void save(TbSeller seller);

	List<TbSeller> findAll();

	PageResult search(int page, int pageSize, TbSeller seller);

	PageResult findPage(int page, int pageSize);

	TbSeller findOne(String id);

	void updateStatus(String sellerId, String status);

	TbSeller findByUsername(String username);

}
