package es.uniovi.asw.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity(name = "Users")
public class User {

	@Id
	private Long id;
	private String login;
	private String surname;
	private String email;
	private String address;
	private String nationality;
	private String DNI;
    private Date birthday;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String toStringLogin() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", surname=" + surname + ", email=" + email + ", address="
				+ address + ", nationality=" + nationality + ", DNI=" + DNI + ", birthday=" + birthday + ", password="
				+ password + "]";
	}
	
	
}
