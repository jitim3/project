package project.dao;

import java.util.Optional;

import project.dao.entity.User;

public interface UserDao {
	boolean isUserExists(String username);

	Optional<User> getAdmin(String username, String password);

	Optional<User> getCustomer(String username, String password);
	
	boolean createUser(User user);

}
