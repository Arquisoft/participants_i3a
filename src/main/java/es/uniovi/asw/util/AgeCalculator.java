package es.uniovi.asw.util;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

public class AgeCalculator {

	/**
	 * Method to calculate the age of an User
	 * @param birthDate of an User
	 * @return the years of an user
	 */
	public static int calculateAge(Date birthDate) {
		LocalDate birthLocalDate = birthDate.toLocalDate();
		LocalDate currentDate = LocalDate.now();
		if ((birthLocalDate != null) && (currentDate != null)) {
			return Period.between(birthLocalDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

}
