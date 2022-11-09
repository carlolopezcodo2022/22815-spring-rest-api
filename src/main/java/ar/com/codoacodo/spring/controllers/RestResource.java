package ar.com.codoacodo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.domain.User;

@RestController
public class RestResource {
	
	@Autowired
	private User user;
	
	@RequestMapping("/home")
	public void home() {
		this.user.getId();
		System.out.println("esuchando en /");
	}
	
	@RequestMapping("/user/show")
	public User showUser() {
		User u = new User();
		return u;
	}
}
