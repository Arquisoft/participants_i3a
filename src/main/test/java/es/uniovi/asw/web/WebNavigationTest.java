package es.uniovi.asw.web;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.Before;
import org.junit.Test;


public class WebNavigationTest {
	
	@Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/participants_i3a");
    }

    @Test
    public void normalNavigationTest() {
        beginAt("/"); 
        //assertTitleEquals("Citizen Participants Info Portal");
        clickLink("login");
        //assertTitleEquals("Participants Login");
        setTextField("u", "freije@example.com");
        setTextField("p", "asdf");
        submit();
        //assertTitleEquals("Participants Login");
    }

}
