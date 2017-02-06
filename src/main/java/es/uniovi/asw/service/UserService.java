package es.uniovi.asw.service;

import java.util.List;

import es.uniovi.asw.domain.User;

public interface UserService {

	List<User> findAll();
	User findById(long id);
	User findByLogin(String login);
	void save(User user);

}
