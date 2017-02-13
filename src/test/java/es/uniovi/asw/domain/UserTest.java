package es.uniovi.asw.domain;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.DateConversor;
import es.uniovi.asw.domain.User;

public class UserTest {

	private User user1;
	private User user2;

	@Before
	public void setUp() throws Exception {
		user1 = new User(1L, "dgsama", "1234", "David", "Garcia Gonzalez", "C/Lucio Villegas", "Spanish", "12345678Z",
				DateConversor.createSqlDate("14-01-1995"));
		user2 = new User(1L, "kingInNorth", "gh0st", "John", "Snow", "Winterfell", "Northerm", "01234567A",
				DateConversor.createSqlDate("04-09-1980"));
	}

	@Test
	public void gettersAndSettersTest() {

		assertEquals(user1.getBirthday(), DateConversor.createSqlDate("14-01-1995"));

		assertTrue(user1.getFirstName().toLowerCase().equals("david"));
		user1.setFirstName("Juan");
		assertFalse(user1.getFirstName().toLowerCase().equals("david"));

		assertTrue(user2.getPassword().equals("gh0st"));
		user2.setPassword("st4rk");
		assertEquals(user2.toStringLogin(), "User [kingInNorth,st4rk]");

		user2.setAddress("North of the wall");
		user2.setBirthday(DateConversor.createSqlDate("04-09-1800"));

	}

	@Test
	public void compareTest() {

		assertFalse(user1.equals(user2));

		user2.setId(1L);
		user2.setLogin("dgsama");
		user2.setPassword("1234");
		user2.setFirstName("David");
		user2.setLastName("Garcia Gonzalez");
		user2.setAddress("C/Lucio Villegas");
		user2.setNationality("Spanish");
		user2.setDNI("12345678Z");
		user2.setBirthday(DateConversor.createSqlDate("14-01-1995"));

		assertTrue(user1.equals(user2));
	}

}
