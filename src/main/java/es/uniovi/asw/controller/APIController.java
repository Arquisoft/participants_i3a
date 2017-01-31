package es.uniovi.asw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.service.UserService;

@RestController
public class APIController {

	@Autowired
	UserService userService;

	@RequestMapping("/users")
	public List<User> users() {
		return userService.findAll();
	}

}