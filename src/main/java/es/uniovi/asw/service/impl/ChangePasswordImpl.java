package es.uniovi.asw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.repository.UserRepository;
import es.uniovi.asw.service.ChangePassword;

@Service("changePassword")
public class ChangePasswordImpl implements ChangePassword {

	@Autowired
	UserRepository userRepository;

}
