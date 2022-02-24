package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet({"/login", "/register" , "/logout"})
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
			req.getRequestDispatcher("/views/user/login.jsp");
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
}
