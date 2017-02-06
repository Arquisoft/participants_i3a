package es.uniovi.asw.service.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import es.uniovi.asw.util.AgeCalculator;

public class AgeCalculatorTest {

	@Test
	public void testCalculateAge_Success() throws ParseException {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fmt.parse("1996-05-09");

		int actual = AgeCalculator.calculateAge(date);
		assertEquals(20, actual);
	}

}
