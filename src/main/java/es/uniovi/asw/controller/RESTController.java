package es.uniovi.asw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.dto.UserChangePassword;
import es.uniovi.asw.dto.UserDto;
import es.uniovi.asw.dto.UserLogin;
import es.uniovi.asw.repository.UserRepository;
import es.uniovi.asw.service.Participants;

@RestController
public class RESTController {

	@Autowired
	Participants participants;

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/users")
	public List<User> users() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
	        MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> user(@RequestBody UserLogin userLogin) {
		User user = participants.GetParticipant(userLogin.getLogin(), userLogin.getPassword());

		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (!user.getPassword().equals(userLogin.getPassword()))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		UserDto userDto = UserDto.transform(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/password", method = RequestMethod.POST, consumes = {
	        MediaType.APPLICATION_JSON_VALUE,
	        MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updatePassword(@RequestBody UserChangePassword userChangePassword) {
		User user = participants.GetParticipant(userChangePassword.getLogin(), userChangePassword.getPassword());

		//If password does not match the old password of the user, error
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//Passwords can not be equal
		if (!user.getPassword().equals(userChangePassword.getPassword()))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		participants.UpdateInfo(userChangePassword.getLogin(), userChangePassword.getPassword(),
		        userChangePassword.getNewPassword());

		UserDto userDto = UserDto.transform(user);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

}