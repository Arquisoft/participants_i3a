package es.uniovi.asw.service.util;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.domain.User;

public class UserTest {

	private User user1;
	private User user2;

	@Before
	public void setUp() throws Exception {
		user1 = new User(1L, "dgsama", "1234", "David", "Garcia Gonzalez", "C/Lucio Villegas", "Spanish",
				"12345678Z", new Date(1995, 1, 14));
		user2 = new User(1L, "kingInNorth", "gh0st", "John", "Snow", "Winterfell", "Northerm", "01234567A",
				new Date(1980, 4, 9));
	}

	
	@Test
	public void gettersAndSettersTest() {

		assertEquals(user1.getBirthday(), new Date(1995, 1, 14));

		assertTrue(user1.getFirstName().toLowerCase().equals("david"));
		user1.setFirstName("Juan");
		assertFalse(user1.getFirstName().toLowerCase().equals("david"));

		assertTrue(user2.getPassword().equals("gh0st"));
		user2.setPassword("st4rk");
		assertEquals(user2.toStringLogin(), "User [kingInNorth,st4rk]");
		
		user2.setAddress("North of the wall");
		user2.setBirthday(new Date(1800,12,31));

	}

	@Test
	public void compareTest() {
	}

}
