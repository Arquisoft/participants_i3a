package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uniovi.asw.domain.UserTest;
import es.uniovi.asw.dto.UserDtoTest;
import es.uniovi.asw.dto.UserLoginTest;
import es.uniovi.asw.service.UserServiceTest;
import es.uniovi.asw.util.AgeCalculatorTest;

@RunWith(Suite.class)
@SuiteClasses({ UserTest.class, UserServiceTest.class, UserDtoTest.class, UserLoginTest.class,
		AgeCalculatorTest.class })
public class AllTests {

}
