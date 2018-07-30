package com.pinyougou.shop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {

	private SellerService  sellerService;
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TbSeller seller =  sellerService.findByUsername(username);
		
		if(seller!=null&&seller.getStatus().equals("1")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
			authorities.add(grantedAuthority);
			return new User(username, seller.getPassword(), authorities);
		}
		
		return null;
	}

}
