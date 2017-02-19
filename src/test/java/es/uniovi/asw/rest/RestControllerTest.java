package es.uniovi.asw.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import es.uniovi.asw.Application;
import es.uniovi.asw.dto.UserChangePassword;
import es.uniovi.asw.dto.UserLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class RestControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/participants_i3a");
		template = new TestRestTemplate();
	}

	@Test
	public void getParticpantInfoTest() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		UserLogin userLogin = new UserLogin();
		userLogin.setLogin("pablo@example.com");
		userLogin.setPassword("asdf");

		response = template.postForEntity(userURI, userLogin, String.class);
		assertThat(response.getBody(), equalTo(
		        "{\"firstname\":\"Pablo\",\"lastname\":\"Garcia\",\"age\":26,\"id\":2,\"email\":\"pablo@example.com\"}"));
		assertEquals(response.getStatusCode().value(), 200);

		userLogin.setLogin("pablo@example.com");
		userLogin.setPassword("1234");
		response = template.postForEntity(userURI, userLogin, String.class);
		assertEquals(response.getStatusCode().value(), 404);

	}

	@Test
	public void changeInfoTest() throws Exception {
		String userURI = base.toString() + "/user/password";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		UserChangePassword userChangePassword = new UserChangePassword();
		userChangePassword.setLogin("pablo@example.com");
		userChangePassword.setPassword("asdf");
		userChangePassword.setNewPassword("1234");

		response = template.postForEntity(userURI, userChangePassword, String.class);
		assertThat(response.getBody(), equalTo(
		        "{\"firstname\":\"Pablo\",\"lastname\":\"Garcia\",\"age\":26,\"id\":2,\"email\":\"pablo@example.com\"}"));
		assertEquals(response.getStatusCode().value(), 200);

		userChangePassword.setLogin("pablo@example.com");
		userChangePassword.setPassword("pepito");
		response = template.postForEntity(userURI, userChangePassword, String.class);
		assertEquals(response.getStatusCode().value(), 404);
	}

}