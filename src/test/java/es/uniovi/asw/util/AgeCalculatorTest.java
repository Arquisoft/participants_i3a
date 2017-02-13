package es.uniovi.asw.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import es.uniovi.asw.DateConversor;
import es.uniovi.asw.util.AgeCalculator;

public class AgeCalculatorTest {

	@Test
	public void testCalculateAge_Success() throws ParseException {

		java.sql.Date sqlDate = DateConversor.createSqlDate("09-05-1996");

		int age = AgeCalculator.calculateAge(sqlDate);
		assertEquals(20, age);
	}

}
