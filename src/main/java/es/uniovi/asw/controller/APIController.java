package es.uniovi.asw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.dto.UserChangePassword;
import es.uniovi.asw.dto.UserDto;
import es.uniovi.asw.dto.UserLogin;
import es.uniovi.asw.service.UserService;

@RestController
public class APIController {

	@Autowired
	UserService userService;

	@RequestMapping("/users")
	public List<User> users() {
		return userService.findAll();
	}

	@RequestMapping("/user")
	public ResponseEntity<UserDto> user(@RequestBody UserLogin userLogin) {
		User user = userService.findByLogin(userLogin.getLogin());

		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (!user.getPassword().equals(userLogin.getPassword()))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		UserDto userDto = UserDto.transform(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@RequestMapping("/user/changePassword")
	public ResponseEntity<User> updatePassword(@RequestBody UserChangePassword userChangePassword) {
		User user = userService.findByLogin(userChangePassword.getLogin());

		//Passwords can not be equal
		if (!user.getPassword().equals(userChangePassword.getPassword()))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		user.setPassword(userChangePassword.getNewPassword());
		userService.save(user);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}