package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

//ブラウザからのリクエスト（URLアクセス）を受け付ける窓口
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//ブラウザでURLがアクセスされたときに、下記メソッドを動かす
	@GetMapping("/admin/signup")
	public String showRegisterForm(Model model) {
		
		//Java側のデータを運ぶnew Admin()にHTML用の名付け"admin"
		model.addAttribute("admin", new Admin());
		
		//登録画面に戻る
		return "../admin/signup";
	}
	
	
	@PostMapping("/admin/signup")
								//ブラウザで入力したデータ"admin"をAdminに入れて引数とする
	public String registerAdmin(@ModelAttribute("admin") Admin admin) {
		
		//入力したデータをServiceが登録
		adminService.register(admin);
		
		//一覧画面に戻る
		return "redirect:/admin/contacts";
	}

}
