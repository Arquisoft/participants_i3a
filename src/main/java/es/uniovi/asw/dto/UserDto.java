package es.uniovi.asw.dto;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.util.AgeCalculator;

public class UserDto {

	private String firstname;
	private String lastname;
	private int age;
	private Long id;
	private String email;

	public UserDto(String firstname, String lastname, int age, Long id, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.id = id;
		this.email = email;
	}

	/**
	 * Method that transform an user into an UserDto that is going to be sent to the client in JSON format.
	 * @param User user
	 */
	public static UserDto transform(User user) {
		return new UserDto(user.getFirstName(), user.getLastName(), AgeCalculator.calculateAge(user.getBirthday()),
		        user.getId(), user.getLogin());
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
