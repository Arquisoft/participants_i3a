package es.uniovi.asw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.repository.UserRepository;
import es.uniovi.asw.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	// IMPLEMENT HERE CRUD METHODS \\
}
