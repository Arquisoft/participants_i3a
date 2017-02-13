package es.uniovi.asw.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserLoginTest {

	private UserLogin user1;
	private UserLogin user2;

	@Before
	public void setUp() throws Exception {

		user1 = new UserLogin();
		user2 = new UserLogin();
	}

	@Test
	public void test() {
		user1.setLogin("dgsama");
		user2.setLogin("john");

		user1.setPassword("1234");
		user2.setPassword("1234");

		assertEquals(user1.getPassword(), user2.getPassword());
		user2.setPassword("4321");
		assertNotEquals(user1.getPassword(), user2.getPassword());

		assertTrue(user1.getLogin().equals("dgsama"));
		assertFalse(user2.getLogin().equals("asdasdasdasdas"));
		assertTrue(user2.getLogin().equals("john"));

	}

}
