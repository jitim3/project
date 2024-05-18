package project.service;

import java.util.Optional;

import project.dto.UserDto;

public interface UserService {
	boolean isUserExists(String username);
	
	Optional<UserDto> getUserByUsernameAndPassword(String username, String password);
	
	Optional<UserDto> getAdmin(String username, String password);

	Optional<UserDto> getCustomer(String username, String password);
	
	boolean createUser(String username, String password, int userTypeId);
}
