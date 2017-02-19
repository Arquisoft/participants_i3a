package es.uniovi.asw.dto;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.DateConversor;
import es.uniovi.asw.domain.User;

public class UserDtoTest {

	private User user1;
	private UserDto userdto1;
	private UserDto userdto2;

	@Before
	public void setUp() throws Exception {

		user1 = new User(1L, "dgsama", "1234", "David", "Garcia Gonzalez", "C/Lucio Villegas", "Spanish", "12345678Z",
				DateConversor.createSqlDate("14-01-1995"));
		userdto1 = new UserDto("David", "Garcia Gonzalez", 22, 1L, "prueba@uniovi.es");
	}

	@Test
	public void test() {
		assertTrue(22 == userdto1.getAge());
		assertTrue(userdto1.getFirstname().toLowerCase().equals("david"));
		assertTrue(userdto1.getLastname().toLowerCase().equals("garcia gonzalez"));
		assertTrue(userdto1.getEmail().toLowerCase().equals("prueba@uniovi.es"));

		userdto2 = UserDto.transform(user1);
		userdto2.setEmail("prueba@uniovi.es");

		assertTrue(userdto1.getAge() == userdto2.getAge());
		assertTrue(userdto1.getFirstname().equals(userdto2.getFirstname()));
		assertTrue(userdto1.getLastname().equals(userdto2.getLastname()));
		assertTrue(userdto1.getEmail().equals(userdto2.getEmail()));
		assertTrue(userdto1.getId().equals(userdto2.getId()));
	}
}
