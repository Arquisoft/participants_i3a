package es.uniovi.asw.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebNavigationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Autowired
	private UserService userService;
	
	private User testUser;
	
	
	@Before
	public void setUp(){
		
		
		testUser = new User();
		testUser.setId(99999L);
		testUser.setLogin("example@example.com");
		testUser.setPassword("asdf");
		testUser.setFirstName("example");
		testUser.setLastName("testUser");
		Date date = new java.sql.Date(new java.util.Date().getTime());
		testUser.setBirthday(date);
		userService.save(testUser);
	}


	@Test
	public void IndexMappingTest() {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/participants_i3a", String.class)
				.contains("Welcome"));
		
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/participants_i3a/welcome", String.class)
				.contains("Welcome"));
	}

	@Test
	public void LoginMappingTest() {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/participants_i3a/login", String.class)
				.contains("Login"));
	}
	
	
	@Test
	public void LoginInfoTest() {
		
		RequestBuilder requestLogin = post("/login")
	            .param("u", testUser.getLogin())
	            .param("p", testUser.getPassword());
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestLogin, String.class).contains(testUser.getFirstName()));
		
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(), String.class).contains("Change your password"));           
		
	}
	
	@Test
	public void ChangePasswordTest() {
		
		String newPassword = "nuevaContraseña";
		
		RequestBuilder requestChange = post("/changePassword/"+testUser.getId())
	            .param("op", testUser.getPassword())
	            .param("p", newPassword);
		
		RequestBuilder requestChangeFail = post("/changePassword/"+testUser.getId())
	            .param("op", "hola")
	            .param("p", newPassword);
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(),requestChangeFail,String.class).contains("Error"));
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(),requestChange,String.class).contains("Participants Info"));
		
		RequestBuilder requestLoginFail = post("/login")
	            .param("u", testUser.getLogin())
	            .param("p", testUser.getPassword());
		
		RequestBuilder requestLogin = post("/login")
	            .param("u", testUser.getLogin())
	            .param("p", newPassword);
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestLoginFail, String.class).contains("Error"));
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestLogin, String.class).contains(testUser.getFirstName()));
		
			
	}
	
	
	//This test was created due a to a bug we found in the code
	@Test
	public void ChangePasswordTwoTimesTest() {
		
		String newPassword = "nuevaContraseña";
		
		RequestBuilder requestChange = post("/changePassword/"+testUser.getId())
	            .param("op", testUser.getPassword())
	            .param("p", newPassword);
		
		RequestBuilder requestChange2 = post("/changePassword/"+testUser.getId())
	            .param("op", testUser.getPassword())
	            .param("p", testUser.getPassword());
		
		RequestBuilder requestChangeFail = post("/changePassword/"+testUser.getId())
	            .param("op", "hola")
	            .param("p", newPassword);
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(),requestChangeFail,String.class).contains("Error"));
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(),requestChange,String.class).contains("Participants Info"));
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/changePassword/"+testUser.getId(),requestChange2,String.class).contains("Participants Info"));
		
		RequestBuilder requestLogin = post("/login")
	            .param("u", testUser.getLogin())
	            .param("p", testUser.getPassword());
		
		RequestBuilder requestLoginFail = post("/login")
	            .param("u", testUser.getLogin())
	            .param("p", newPassword);
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestLoginFail, String.class).contains("Info"));
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestLogin, String.class).contains(testUser.getFirstName()));
		
			
	}
	
	
	@After
	public void close(){
		userService.delete(testUser);
	}


}
