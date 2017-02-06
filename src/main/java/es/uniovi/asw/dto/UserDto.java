package es.uniovi.asw.dto;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.util.AgeCalculator;

public class UserDto {

	private String firstName;
	private String lastName;
	private int age;
	private Long id;
	private String email;

	public UserDto(String firstName, String lastName, int age, Long id, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
