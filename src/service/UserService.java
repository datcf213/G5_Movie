package service;

import java.util.List;

import entity.User;

public interface UserService {
	User findById(int id);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findByEmai(String email);
	List<User> findAll();
	List<User> findAllPaging(int pageNumber, int pageSize);
	User create(String username, String password, String email, String fullname);
	User update(User entity);
	User delete(String username);
}
