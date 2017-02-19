package es.uniovi.asw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.Application;
import es.uniovi.asw.domain.User;
import es.uniovi.asw.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class UserRepositoryTest {

	User user1;
	User user2;
	
	@Autowired
	UserRepository userService;
	
	@Before
	public void setUp() throws Exception {
		
		userService.deleteAll();

		int year = 1995;
		int month=1;
		int day = 14;
		String date1string = year + "/" + month + "/" + day;
		year = 1995;
		month=1;
		day = 14;
		String date2string = year + "/" + month + "/" + day;
		
		
		Date date1;
		Date date2;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    date1 = new Date(formatter.parse(date1string).getTime());
	    date2 = new Date(formatter.parse(date2string).getTime());
	    
		
		user1 = new User(434L, "lavida", "1234", "Lavida", "De la vida", "C/Lucio Villegas", "Spanish", "12345678Z",
				date1);
		user2 = new User(578L, "kingInNorth", "gh0st", "John", "Snow", "Winterfell", "Northerm", "01234567A",
				date2);
	}

	@Test
	public void testSaveAndFind() {

		userService.save(user1);
		User user1Example1 = userService.findById(434);
		User user1Example2 = userService.findByLogin("lavida");
		
		assertEquals(user1,user1Example1);
		assertEquals(user1,user1Example2);
		assertEquals(user1Example1,user1Example1);
		
		User user2null1 = userService.findById(578);
		User user1null2 = userService.findByLogin("kingInNorth");
		
		assertEquals(null,user2null1);
		assertEquals(null,user1null2);
		
		userService.save(user2);
		
		User user2Example1 = userService.findById(578);
		User user2Example2 = userService.findByLogin("kingInNorth");
		
		assertEquals(user2,user2Example1);
		assertEquals(user2,user2Example2);
		assertEquals(user2Example1,user2Example1);
	}
	
	@Test
	public void testDeleteAndFind() {

		userService.save(user1);
		userService.save(user2);
		User user1Example1 = userService.findById(434);
		User user1Example2 = userService.findByLogin("lavida");
		User user2Example1 = userService.findById(578);
		User user2Example2 = userService.findByLogin("kingInNorth");
		
		assertEquals(user1,user1Example1);
		assertEquals(user1,user1Example2);
		assertEquals(user1Example1,user1Example1);
		assertEquals(user2,user2Example1);
		assertEquals(user2,user2Example2);
		assertEquals(user2Example1,user2Example1);
		
		userService.delete(user1);
		
		user1Example1 = userService.findById(434);
		user1Example2 = userService.findByLogin("lavida");
		
		assertEquals(null,user1Example1);
		assertEquals(null,user1Example2);
		
		assertEquals(user2,user2Example1);
		assertEquals(user2,user2Example2);
		assertEquals(user2Example1,user2Example1);
		
		userService.delete(user2);
		
		user2Example1 = userService.findById(578);
		user2Example2 = userService.findByLogin("kingInNorth");
		
		assertEquals(null,user1Example1);
		assertEquals(null,user1Example2);		
		assertEquals(null,user2Example1);
		assertEquals(null,user2Example2);
		
		userService.save(user1);
		userService.save(user2);
		
		userService.deleteAll();
		
		assertEquals(null,user1Example1);
		assertEquals(null,user1Example2);		
		assertEquals(null,user2Example1);
		assertEquals(null,user2Example2);
	}
	
	@Test
	public void findAllTest(){
		userService.save(user1);
		userService.save(user2);
		
		assertTrue(userService.findAll().contains(user1));
		assertTrue(userService.findAll().contains(user2));
		
		userService.delete(user2);
		
		assertTrue(userService.findAll().contains(user1));
		assertFalse(userService.findAll().contains(user2));
		
		userService.save(user2);
		
		assertTrue(userService.findAll().contains(user1));
		assertTrue(userService.findAll().contains(user2));
		
		userService.deleteAll();
		
		assertFalse(userService.findAll().contains(user1));
		assertFalse(userService.findAll().contains(user2));
		assertTrue(userService.findAll().isEmpty());
	}
}

