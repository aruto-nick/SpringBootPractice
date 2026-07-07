package com.example.demo.service;

//Listを使えるようにする→データを取得するため
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

//「ContactServiceImplクラス」＝「データの加工担当」と明確化
@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	
	private ContactRepository contactRepository;
	
	public List<Contact>getAllContacts(){
		return contactRepository.findAll();
	}
	
	

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();
		
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());
 
        contactRepository.save(contact);

	}
	
	//インターフェースにて宣言したメソッドの説明をする合図
	@Override
	public Contact getContactById(Long id) {
		// Repositoryクラスからidデータを出力
		return contactRepository.findById(id)
				//もしくはデータが萎えればエラーを表示
				.orElseThrow(() -> new IllegalArgumentException("指定された顧客が見つかりません。ID: " + id));
	}
	
	//更新機能(12-11)
	@Override
	public void updateContact(Contact contact) {
		contactRepository.save(contact);
	}

}
