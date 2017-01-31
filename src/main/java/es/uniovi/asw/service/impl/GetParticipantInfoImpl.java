package es.uniovi.asw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.repository.UserRepository;
import es.uniovi.asw.service.GetParticipantInfo;

@Service("getParticipant")
public class GetParticipantInfoImpl implements GetParticipantInfo {

	@Autowired
	UserRepository userRepository;
}
