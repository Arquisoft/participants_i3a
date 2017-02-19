package es.uniovi.asw.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebNavigationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


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
		
		RequestBuilder requestBuilder = post("/login")
	            .param("u", "freije@example.com")
	            .param("p", "asdf");
		
		assertThat(restTemplate.postForObject("http://localhost:" + port + "/participants_i3a/login", requestBuilder, String.class).contains("freije"));
	}


}
