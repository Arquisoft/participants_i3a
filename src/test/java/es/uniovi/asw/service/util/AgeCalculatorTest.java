package es.uniovi.asw.service.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import es.uniovi.asw.util.AgeCalculator;

public class AgeCalculatorTest {

	@Test
	public void testCalculateAge_Success() throws ParseException {

		final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		final String input = "09-05-1996";
		final LocalDate localDate = LocalDate.parse(input, DATE_FORMAT);
		
		java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );
		
		int age = AgeCalculator.calculateAge(sqlDate);
		assertEquals(20, age);
	}

}
