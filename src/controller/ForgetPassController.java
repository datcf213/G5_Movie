package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import utils.mailUntils;

@WebServlet({"/forgetpassword","/confirmcapcha","/confirmpassword"})
public class ForgetPassController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	User user = new User();
	UserDao userDao = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/quenmatkhau.jsp").forward(request, response);
	}

	String capChas="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if (url.contains("/forgetpassword")) {
			String email = request.getParameter("email");
			try {
				capChas="";
				user = userDao.findByEmai(email);
				for (int i = 0; i < 6; i++) {
		            double randomDouble = Math.random();
		            randomDouble = randomDouble * 9+1;
		            capChas += (int) randomDouble;
		        }
				mailUntils mail = new mailUntils(email, "Password retrieval", capChas);
				if(mail.guiEmail()) {
					System.out.println("gui");
				} else {
					System.out.println("chua");
				}
				request.getRequestDispatcher("/views/user/confirmcapcha.jsp").forward(request, response);	
			} catch (Exception e) {
				doGet(request, response);
			}
		} else if (url.contains("/confirmcapcha")) {
			String cap = request.getParameter("capcha");
			if (cap.equals(capChas)) {
				request.getRequestDispatcher("/views/user/confirmpassword.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/views/user/confirmcapcha.jsp").forward(request, response);	
			}
		} else if (url.contains("/confirmpassword")) {
			String pass = request.getParameter("pass");
			
			user.setPassword(pass);
			try {
				userDao.create(user);
				request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/views/user/confirmpassword.jsp").forward(request, response);
			}
		}
	}
}
