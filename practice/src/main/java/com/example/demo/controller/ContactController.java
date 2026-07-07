package com.example.demo.controller;

//Listを使えるようにする→データを取得するため
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//PathVariableを使えるようにする→
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	//お問い合わせ一覧画面（12-10）
	//Controllerとブラウザの接続
	@GetMapping("/admin/contacts")
	public String showContacts(Model model) {
		
		//Serviceクラスからデータを取得
		List<Contact>contacts = contactService.getAllContacts();
		
		//modelはControllerからブラウザにデータを専用の役割
		//Controllerに届いたデータcontactsに"contacts"と名付け
		model.addAttribute("contacts", contacts);
		
		return "../admin/contacts";
	}
	
	//Controllerとお問い合わせ詳細画面の接続（12-11）
	@GetMapping("/admin/contacts/:{id}")
	//URL内の("/contacts/:{id}")idを取得
	//Model型変数model取得。データをブラウザに運ぶ役割
	public String showContactsDetail (@PathVariable ("id") Long id, Model model) {
		
		
		  // 1. URLから受け取った「id」を使って、Serviceに届いたDBデータを１件取得
        Contact contact = contactService.getContactById(id);
        
        // 2. 取得したデータを「contact」という名前で詳細画面（HTML）に渡す
        model.addAttribute("contact", contact);
        
        // 3. 詳細画面のHTML（contact_detail.html）を呼び出す
        return "../admin/contact_detail";
    }
	
	//Controllerと編集画面を接続(12-11)
	@GetMapping("/admin/contacts/:{id}/edit")
		public String showEdit(@PathVariable("id") Long id, Model model) {
			
			Contact contact = contactService.getContactById(id);
			
			model.addAttribute("contact", contact);
			
			return "../admin/contact_edit";
		}
	
	//編集機能(12-11)
	// 更新を実行し、一覧画面にリダイレクトする処理
	@PutMapping("/admin/contacts/{id}/update")
	//HTMLのcontactという名のフォームから届いたデータをContactというEntityクラスの箱に入れる。箱の名前はcontact
	public String updateContact(@PathVariable("id") Long id, @ModelAttribute("contact") Contact contact) {
		
		// 1. 画面から送られてきた更新データ（contact）をService経由でデータベースに保存する
		contactService.updateContact(contact); 
		
		// 2. 更新が完了後、お問い合わせ一覧画面（/admin/contacts）に自動で戻る（リダイレクト）
		return "redirect:/admin/contacts";
	}
	
	//削除機能
	//HTML削除ボタンとControllerを接続
	@DeleteMapping("/admin/contacts/{id}/delete")
	
	//URLのid部分からデータを取得してLong型の変数名idとして扱う
	public String deleteContact(@PathVariable ("id") Long id) {
		
		//Serviceにデータ消去を指示
		contactService.deleteContactById(id);
		
		//データ消去指示の後、お問い合わせ一覧画面に戻る
		return "redirect:/admin/contacts";
	}

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());

        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult, HttpServletRequest request) {

        if (errorResult.hasErrors()) {
          return "contact";
        }

        HttpSession session = request.getSession();
        session.setAttribute("contactForm", contactForm);

        return "redirect:/contact/confirm";
    }

    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);
        return "confirmation";
    }


@PostMapping("/contact/register")
public String register(Model model, HttpServletRequest request) {
	HttpSession session = request.getSession();
	ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
	
	contactService.saveContact(contactForm);
	
	return "redirect:/contact/complete";
}
@GetMapping("/contact/complete")
public String complete(Model model, HttpServletRequest request) {
	if (request.getSession(false) == null) {
		return "redirect:/contact";
	}
	HttpSession session = request.getSession();
	ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
	model.addAttribute("contactForm", contactForm);
	session.invalidate();
	return "completion";
    }
}