package es.uniovi.asw.domain;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

public class UserInfo {

	// Log
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserInfo.class);

	private final String name;
	private final Integer age;

	public UserInfo(String name, Integer age) {
		LOG.info("Creating user " + name + ". age: " + age);
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
}