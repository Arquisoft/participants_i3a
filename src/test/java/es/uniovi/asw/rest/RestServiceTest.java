package es.uniovi.asw.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.uniovi.asw.Application;
import es.uniovi.asw.service.Participants;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RestServiceTest {

  //Required to Generate JSON content from Java objects
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  //Required to delete the data added for tests.
  //Directly invoke the APIs interacting with the DB
  @Autowired
  private Participants participants;;

  //Test RestTemplate to invoke the APIs.
  private TestRestTemplate restTemplate = new TestRestTemplate();
  
  @Test
  public void testGetParticipantService() throws JsonProcessingException{
	//Building the Request body data
//	  Map<String, Object> requestBody = new HashMap<String, Object>();
//	  requestBody.put("login", "freije@example.com");
//	  requestBody.put("password", "asdf");
//	  HttpHeaders requestHeaders = new HttpHeaders();
//	  requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//	  HttpEntity<String> httpEntity =
//	      new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
//	  
//	  UserDto response = restTemplate.postForObject("http://localhost:8080/participants_i3a/user", httpEntity, UserDto.class);

	  
  }
  

}