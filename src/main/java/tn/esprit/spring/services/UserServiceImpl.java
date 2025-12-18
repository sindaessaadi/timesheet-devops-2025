package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<User> retrieveAllUsers() {
		l.info("Retrieving all users");
		List<User> users = userRepository.findAll();
		l.debug("Number of users retrieved: {}", users.size());
		return users;
	}

	@Override
	public User addUser(User u) {
		l.info("Adding a new user");
		l.debug("User details: {}", u);

		User utilisateur = null;

		try {
			utilisateur = userRepository.save(u);
			l.info("User added successfully with id {}", utilisateur.getId());
		} catch (Exception e) {
			l.error("Error in addUser()", e);
		}

		return utilisateur;
	}

	@Override
	public User updateUser(User u) {
		l.info("Updating user");
		l.debug("User details: {}", u);

		User userUpdated = null;

		try {
			userUpdated = userRepository.save(u);
			l.info("User updated successfully with id {}", userUpdated.getId());
		} catch (Exception e) {
			l.error("Error in updateUser()", e);
		}

		return userUpdated;
	}

	@Override
	public void deleteUser(String id) {
		l.info("Deleting user with id {}", id);

		try {
			userRepository.deleteById(Long.parseLong(id));
			l.info("User deleted successfully with id {}", id);
		} catch (Exception e) {
			l.error("Error in deleteUser()", e);
		}
	}

	@Override
	public User retrieveUser(String id) {
		l.info("Retrieving user with id {}", id);
		User u = null;

		try {
			u = userRepository.findById(Long.parseLong(id)).orElse(null);
			if (u == null) {
				l.warn("User not found with id {}", id);
			} else {
				l.debug("User found: {}", u);
			}
		} catch (Exception e) {
			l.error("Error in retrieveUser()", e);
		}

		return u;
	}
}
