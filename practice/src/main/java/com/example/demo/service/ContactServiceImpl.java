package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

//SpringBootに「Seiviceクラス」と伝える
@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	//Repository「ContactRepository」を使用する準備
	private ContactRepository contactRepository;
	
	//SpringBootが自動的にRepositoryの中身をセット
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
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

}
