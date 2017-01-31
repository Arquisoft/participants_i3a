package es.uniovi.asw.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Users")
public class User {

	@Id
	private Long id;
	private String login;
	private String password;

	public User() {
		super();
	}

	public User(Long id, String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
}
