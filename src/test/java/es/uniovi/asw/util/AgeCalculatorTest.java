package es.uniovi.asw.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import es.uniovi.asw.DateConversor;

public class AgeCalculatorTest {

	@Test
	public void testCalculateAge_Success() throws ParseException {

		java.sql.Date sqlDate = DateConversor.createSqlDate("09-05-1996");

		int age = AgeCalculator.calculateAge(sqlDate);
		assertEquals(20, age);

		age = AgeCalculator.calculateAge(null);
		assertEquals(0, age);
	}

}
