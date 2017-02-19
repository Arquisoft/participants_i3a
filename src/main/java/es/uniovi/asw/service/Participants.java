package es.uniovi.asw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.domain.User;
import es.uniovi.asw.repository.UserRepository;

@Service
public class Participants implements GetParticipantInfo, ChangeInfo {

	@Autowired
	UserRepository userRepository;

	/**
	 * Update the info of a participant having its credentials and new password.
	 * @param login - login of the participant to be updated
	 * @param password - password of the participant to be updated
	 * @param newPassword - new password of the participant to update
	 */
	@Override
	public void UpdateInfo(String login, String password, String newPassword) {
		User user = userRepository.findByLoginAndPassword(login, password);
		user.setPassword(newPassword);
		userRepository.save(user);
	}

	/**
	 * Get the info of a participant passing as parameters its credentials
	 * @param login - login of the participant to retrieve
	 * @param password - password of the participant to retrieve
	 */
	@Override
	public User GetParticipant(String login, String password) {
		User user = userRepository.findByLoginAndPassword(login, password);

		return user;
	}

}
