package project.service.impl;

import java.time.Instant;
import java.util.Optional;

import project.dao.UserDao;
import project.dao.entity.User;
import project.dao.impl.DefaultUserDao;
import project.dto.UserDto;
import project.service.UserService;

public class DefaultUserService implements UserService {
	private final UserDao userDao;
	
	public DefaultUserService() {
		this.userDao = new DefaultUserDao();
	}

	@Override
	public boolean isUserExists(String username) {
		return this.userDao.isUserExists(username);
	}

	@Override
	public Optional<UserDto> getAdmin(String username, String password) {
		return this.userDao.getAdmin(username, password)
				.map(this::mapToUserDto);
	}

	@Override
	public Optional<UserDto> getCustomer(String username, String password) {
		return this.userDao.getCustomer(username, password)
				.map(this::mapToUserDto);
	}

	@Override
	public boolean createUser(String username, String password, int userTypeId) {
		User newUser = new User(null, username, password, userTypeId, Instant.now(), null);
		
		return this.userDao.createUser(newUser);
	}
	
	private UserDto mapToUserDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getUserTypeId(), user.getCreatedAt(), user.getUpdatedAt());
	}

}
