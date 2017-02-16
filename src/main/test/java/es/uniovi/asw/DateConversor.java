package es.uniovi.asw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConversor {

	public static java.sql.Date createSqlDate(String date) {
		final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		final String input = date;
		final LocalDate localDate = LocalDate.parse(input, DATE_FORMAT);

		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		return sqlDate;
	}

}
