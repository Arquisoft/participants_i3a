package es.uniovi.asw.service;

import static org.junit.Assert.assertEquals;

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
public class ParticipantsTest {

	User user1;
	User user2;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Participants participants;

	@Before
	public void setUp() throws Exception {

		userRepository.deleteAll();

		int year = 1995;
		int month = 1;
		int day = 14;
		String date1string = year + "/" + month + "/" + day;
		year = 1995;
		month = 1;
		day = 14;
		String date2string = year + "/" + month + "/" + day;

		Date date1;
		Date date2;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		date1 = new Date(formatter.parse(date1string).getTime());
		date2 = new Date(formatter.parse(date2string).getTime());

		user1 = new User(434L, "lavida", "1234", "Lavida", "De la vida", "C/Lucio Villegas", "Spanish", "12345678Z",
		        date1);
		user2 = new User(578L, "kingInNorth", "gh0st", "John", "Snow", "Winterfell", "Northerm", "01234567A", date2);

		userRepository.save(user1);
		userRepository.save(user2);
	}

	@Test
	public void testUpdateInfo() {
		participants.UpdateInfo("lavida", "1234", "lavidaduradelestudiante");
		User userUpdated = userRepository.findById(434L);
		assertEquals(userUpdated.getPassword(), "lavidaduradelestudiante");
	}

	@Test
	public void tesGetParticipant() {
		User userGetInfo = participants.GetParticipant("lavida", "1234");
		assertEquals(userGetInfo.getDNI(), "12345678Z");
		assertEquals(userGetInfo.getNationality(), "Spanish");
	}

}
