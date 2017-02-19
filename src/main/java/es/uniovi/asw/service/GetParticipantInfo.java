package es.uniovi.asw.service;

import es.uniovi.asw.domain.User;

public interface GetParticipantInfo {

	User GetParticipant(String login, String password);

}
