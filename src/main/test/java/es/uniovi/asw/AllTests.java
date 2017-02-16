package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uniovi.asw.dto.*;
import es.uniovi.asw.service.*;
import es.uniovi.asw.util.*;
import es.uniovi.asw.domain.*;

@RunWith(Suite.class)
@SuiteClasses({ UserTest.class, UserServiceTest.class, UserDtoTest.class, UserLoginTest.class,
		AgeCalculatorTest.class })
public class AllTests {

}
