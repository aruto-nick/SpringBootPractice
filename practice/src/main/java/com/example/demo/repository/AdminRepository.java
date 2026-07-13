package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {
	
	//変数emailを元にDBからデータを取得
	Admin findByEmail(String email);

}
