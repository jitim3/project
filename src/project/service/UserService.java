package project.service;

import project.dao.UserDao;
import project.dao.entity.User;
import project.dto.CreateUserDto;
import project.dto.UserDto;

import java.time.Instant;
import java.util.Optional;

public class UserService {
	private final UserDao userDao;
	
	public UserService() {
		this.userDao = new UserDao();
	}

	public boolean isUserExists(String username) {
		return this.userDao.isUserExists(username);
	}

	public Optional<UserDto> getUserByUsernameAndPassword(String username, String password) {
		return this.userDao.getUserByUsernameAndPassword(username, password)
				.map(this::mapToUserDto);
	}

	public Optional<UserDto> getSuperAdmin(String username, String password) {
		return this.userDao.getSuperAdmin(username, password)
				.map(this::mapToUserDto);
	}

	public Optional<UserDto> getAdmin(String username, String password) {
		return this.userDao.getAdmin(username, password)
				.map(this::mapToUserDto);
	}

	public Optional<UserDto> getCustomer(String username, String password) {
		return this.userDao.getCustomer(username, password)
				.map(this::mapToUserDto);
	}

	public boolean createUser(String username, String password, int userTypeId) {
		CreateUserDto createUserDto = new CreateUserDto(username, password, userTypeId, Instant.now());
		
		return this.userDao.createUser(createUserDto);
	}
	
	private UserDto mapToUserDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getUserType(), user.getCreatedAt(), user.getUpdatedAt());
	}

}
