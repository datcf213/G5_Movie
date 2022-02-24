package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	User fidnById(int id);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findByEmai(String email);
	List<User> findAll();
	List<User> findAllPaging(int pageNumber, int pageSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
}
