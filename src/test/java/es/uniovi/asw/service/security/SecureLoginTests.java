package es.uniovi.asw.service.security;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.containsString;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class SecureLoginTests {

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
	public void checkPreLoginAccess() throws Exception {
			String userURI = base.toString() + "/";  
			ResponseEntity<String> response = template.getForEntity(userURI, String.class);
			assertThat(response.getBody(), containsString("Welcome"));
			assertThat(response.getBody(), not(containsString("Login")));

			
			userURI = base.toString() + "/welcome";  
			response = template.getForEntity(userURI, String.class);
			assertThat(response.getBody(), containsString("Welcome"));
			assertThat(response.getBody(), not(containsString("Login")));
			
			userURI = base.toString() + "/login";  
			response = template.getForEntity(userURI, String.class);
			assertThat(response.getBody(), containsString("Login"));
			assertThat(response.getBody(), not(containsString("Welcome")));
			
			userURI = base.toString() + "/hello";  
			response = template.getForEntity(userURI, String.class);
			assertThat(response.getBody(), containsString("Login"));
			assertThat(response.getBody(), not(containsString("Hello")));
			
			userURI = base.toString() + "/info";  
			response = template.getForEntity(userURI, String.class);
			assertThat(response.getBody(), containsString("Login"));
			assertThat(response.getBody(), not(containsString("Information")));
	}

}