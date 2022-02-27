package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet({"/login", "/register" , "/logout", "/changePassword"})
public class UserController extends HttpServlet{
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		
		switch (path) {
			case "/login":
				doGetLogin(req, resp);
				break;
			case "/register":
				doGetRegister(req, resp);
				break;
			case "/logout":
				doGetLogout(session, req, resp);
				break;
			case "/changePassword":
				doGetChangePassword(req, resp);
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		switch (path) {
			case "/login":
				doPostLogin(session, req, resp);
				break;
			case "/register":
				doPostRegister(session, req, resp);
				break;
			case "/changePassword":
				doPostChangePassword(req, resp);
				break;
		}
	}
	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}
	
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = userService.findByUsernameAndPassword(username, password);
		if(user != null) {
			session.setAttribute("currentUser", user);
			resp.sendRedirect("index");
		}else {
			req.setAttribute("message", "Invalid username or password");
			req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);;
		}
	}
	
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	}
	
	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		
		if(password.equals(confirmPassword)) {
			User user = userService.create(username, password, email, fullname);
			if(user != null) {
				session.setAttribute("currentUser", user);
				resp.sendRedirect("index");
			}else {
				req.setAttribute("message", "An unknown error");
				req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);;
			}
		}else {
			req.setAttribute("message", "Password and confirmpassword is not match");
			req.getRequestDispatcher("views/user/register.jsp").forward(req, resp);
		}
	}
	
	private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute("currentUser");
		resp.sendRedirect("index");
	}
	
	private void doGetChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/user/changePassword.jsp").forward(req, resp);
		
	}
	
	private void doPostChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String currentPW = req.getParameter("curent_password");
		String newPW1 = req.getParameter("new_password1");
		String newPW2 = req.getParameter("new_password2");
		UserDao uDao = new UserDaoImpl();
		User user = new User();
		user = uDao.findByUsername(userName);
		String method = req.getMethod();
		if(method.equals("POST")) {
			if(check(req, resp) ) {
				user.setPassword(newPW1);
				uDao.update(user);
				req.setAttribute("msg", "Change password successfully");	
			}
		}
		req.getRequestDispatcher("views/user/changePassword.jsp").forward(req, resp);
	}
	
	private boolean check(HttpServletRequest req, HttpServletResponse resp)   throws ServletException, IOException{
		String userName = req.getParameter("username");
		String currentPW = req.getParameter("curent_password");
		String newPW1 = req.getParameter("new_password1");
		String newPW2 = req.getParameter("new_password2");
		UserDao uDao = new UserDaoImpl();
		User user = new User();
		user = uDao.findByUsername(userName);
		if(user==null) {
			req.setAttribute("msg", "Incorrect username");
			return false;
		}else if(!currentPW.contains(user.getPassword())) {
			req.setAttribute("msg", "Incorrect password");
			return false;
		}else if(Integer.parseInt(newPW1)<6) {
			req.setAttribute("msg", "Password must be more than 6 characters");
			return false;
		}else if(!newPW1.equals(newPW2)) {
			req.setAttribute("msg", "Password and confirm password do not match");
			return false;
		}
		return true;
	}
}
