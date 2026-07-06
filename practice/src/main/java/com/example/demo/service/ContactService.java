package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
	
	void saveContact(ContactForm contactForm);
	
	List<Contact> getAllContacts();
	
	
	//実装クラスContactServiceImplにて使用するメソッドを宣言
	Contact getContactById(Long id);

}
