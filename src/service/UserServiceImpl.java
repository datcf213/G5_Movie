package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UserServiceImpl implements UserService{
	
	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	
	@Override
	public User findById(int id) {

		return dao.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public User findByEmai(String email) {
		return dao.findByEmai(email);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findAllPaging(int pageNumber, int pageSize) {
		return dao.findAllPaging(pageNumber, pageSize);
	}

	@Override
	public User create(String username, String password, String email, String fullname) {
		User user = new User();
		User exisUser = findByUsername(username);
		if(exisUser!=null) {
			return exisUser;
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFullName(fullname);
		user.setActive(true);
		user.setAdmin(false);
		return dao.create(user);
	}

	@Override
	public User update(User entity) {
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		User user = findByUsername(username);
		user.setActive(false);
		return dao.update(user);
	}
	
}
