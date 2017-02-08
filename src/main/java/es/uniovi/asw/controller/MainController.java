package es.uniovi.asw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.dto.UserChangePassword;
import es.uniovi.asw.dto.UserDto;
import es.uniovi.asw.dto.UserLogin;
import es.uniovi.asw.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String landing(Model model) {
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome(Model model) {
		return "index";
	}

	@RequestMapping("/hello")
	public String hola(Model model) {
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new UserLogin());
		return "login";
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String loginInfo(@ModelAttribute UserLogin userLogin, Model model) {

		User user = userService.findByLogin(userLogin.getLogin());
		
		//If the user is not in the db
		if (user == null) {
			return "error";
		}
		//If the password is not correct
		if (!user.getPassword().equals(userLogin.getPassword())) {
			return "error";
		}

		UserDto userDto = UserDto.transform(user);
		model.addAttribute("user", user);

		return "info";
	}
	
	@RequestMapping(path = "/changePassword/{id}", method = RequestMethod.GET)
	public String changePassword(@PathVariable long id, Model model) {
		
		User user = userService.findById(id);
		UserChangePassword userUpdated = new UserChangePassword();
		userUpdated.setLogin(user.getLogin());
		
		model.addAttribute("userUpdated", userUpdated);
		
		return "changePassword";
	}

	@RequestMapping(path = "/changedPassword", method = RequestMethod.POST)
	public String changePasswordPost(@ModelAttribute UserChangePassword userUpdated, Model model) {
		User user = userService.findByLogin(userUpdated.getLogin());
		
		//If password does not match the old password of the user, error
		if (!user.getPassword().equals(userUpdated.getPassword())) {
			return "error";
		}
		//If old password and new passwords are the same, error
		if (userUpdated.getNewPassword().equals(userUpdated.getPassword())) {
			return "error";
		}
		//If it´s correct, set the newPassword to the user and save it in the db
		user.setPassword(userUpdated.getNewPassword());
		userService.save(user);

		UserDto userDto = UserDto.transform(user);
		model.addAttribute("user", userDto);

		return "info";
	}


}