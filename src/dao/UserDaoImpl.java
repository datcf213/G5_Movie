package dao;

import java.util.List;

import entity.User;

public class UserDaoImpl extends GeneralDao<User> implements UserDao{

	@Override
	public User findById(int id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0 AND o.password = ?1";
		return super.findOne(User.class, sql, username, password);
	}

	@Override
	public User findByEmai(String email) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAllPaging(int pageNumber, int pageSize) {
		return super.findAllPaging(User.class, true, pageNumber, pageSize);
	}
}
