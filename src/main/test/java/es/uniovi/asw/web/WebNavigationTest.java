package es.uniovi.asw.web;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;


public class WebNavigationTest {
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/");
    }

    @Test
    public void normalNavigationTest() {
//        beginAt("participants_i3a/"); 
//        assertTitleEquals("Citizen Participants Info Portal");
//        clickLink("Get to the login page!");
//        assertTitleEquals("Participants Login");
//        setTextField("u", "freije@example.com");
//        setTextField("p", "asdf");
//        submit();
//        assertTitleEquals("Participants Information");
//        clickLink("Change your password");
//        assertTitleEquals("Change your password");
    }
    
    @Test
    public void errorNavigationTest() {
//        beginAt("/"); 
//        assertTitleEquals("Citizen Participants Info Portal");
//        clickLink("Get to the login page!");
//        assertTitleEquals("Participants Login");
//        setTextField("u", "foo");
//        setTextField("p", "bar");
//        submit();
//        assertTitleEquals("Error");
    }

}
