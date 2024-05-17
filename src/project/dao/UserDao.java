package project.dao;

import java.util.Optional;

import project.dao.entity.User;
import project.dto.CreateUserDto;

public interface UserDao {
	boolean isUserExists(String username);
	
	Optional<User> getUserByUsernameAndPassword(String username, String password);

	Optional<User> getAdmin(String username, String password);

	Optional<User> getCustomer(String username, String password);
	
	boolean createUser(CreateUserDto createUserDto);

}
