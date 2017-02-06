package es.uniovi.asw.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeCalculator {

	/**
	 * Method to calculate the age of an User
	 * @param birthDate of an User
	 * @return the years of an user
	 */
	public static int calculateAge(Date birthDate) {
		LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthLocalDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

}
