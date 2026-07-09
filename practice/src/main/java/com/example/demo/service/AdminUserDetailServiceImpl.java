package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
//Spring Securityの規格（UserDetailsService）に則って、管理者の詳細データをDBから取ってくる専門のクラス
public class AdminUserDetailServiceImpl  implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Admin admin = adminRepository.findByEmail(username);
		
		if (admin == null) {
			throw new UsernameNotFoundException("管理者が見つかりません:" + username);
		}
		
		return new User(
				admin.getEmail(),
				admin.getPassword(),
				new ArrayList<>()
		);
	}
}
