package es.uniovi.asw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);

	User findByLoginAndPassword(String login, String password);

	User findById(long id);
}
