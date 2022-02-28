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
	User create1(String username, String password, String email, String fullname, Boolean admin);
	User update1(Integer id,String username, String password, String email, String fullname, Boolean admin);
	User delete(String username);
	User update(User entity);
}
