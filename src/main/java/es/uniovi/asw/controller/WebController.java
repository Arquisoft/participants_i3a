package es.uniovi.asw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.dto.UserChangePassword;
import es.uniovi.asw.dto.UserDto;
import es.uniovi.asw.dto.UserLogin;
import es.uniovi.asw.service.UserService;

@Controller
public class WebController {

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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("login");
		model.addObject("user", new UserLogin());
		return model;
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public ModelAndView loginInfo(@ModelAttribute("user") UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) {

		User user = userService.findByLogin(userLogin.getLogin());
		ModelAndView model= null;

		//If the user is not in the db
		if (user == null) {
			model = new ModelAndView("error");
			model.addObject("errorMessage", "That username is not in our database");
		} else if (!user.getPassword().equals(userLogin.getPassword())) {
		//If the password is not correct
			model = new ModelAndView("error");
			model.addObject("errorMessage", "Password is not correct");
		} else{
			model = new ModelAndView("info");
			UserDto userDto = UserDto.transform(user);
			model.addObject("userDto", userDto);	
		}
		return model;
	}
	
	@RequestMapping(path = "/changePassword/{id}", method = RequestMethod.GET)
	public ModelAndView changePassword(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		User user = userService.findById(id);
		UserChangePassword userUpdated = new UserChangePassword();
		userUpdated.setId(user.getId());
		
		ModelAndView model = new ModelAndView("changePassword");
		model.addObject("user", userUpdated);
		
		return model;
	}

	@RequestMapping(path = "/changePassword/{id}", method = RequestMethod.POST)
	public ModelAndView changePasswordPost(@PathVariable("id") long id, @ModelAttribute("user") UserChangePassword userUpdated, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.findById(id);
		
		ModelAndView model = null;
		//If password does not match the old password of the user, error
		if (!user.getPassword().equals(userUpdated.getPassword())) {
			model = new ModelAndView("error");
			model.addObject("errorMessage", "Password is not correct");
		} else
		//If old password and new passwords are the same, error
		if (userUpdated.getNewPassword().equals(userUpdated.getPassword())) {
			model = new ModelAndView("error");
			model.addObject("errorMessage", "New password is equal to old password");
		} else{
			//If it´s correct, set the newPassword to the user and save it in the db
			user.setPassword(userUpdated.getNewPassword());
			userService.save(user);

			UserDto userDto = UserDto.transform(user);
			model = new ModelAndView("info");
			model.addObject("userDto", userDto);
		}

		return model;
	}


}