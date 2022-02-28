package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet({ "/admin/ove", "/admin/user-edit.jsp", "/admin/user-overview.jsp", "/admin/user-edit/create",
			  "/admin/user-edit/delete", "/admin/user-edit/update", "/admin/user-overview/edit/*" })
public class AdminController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDao uDao = new UserDaoImpl();
		UserService userService = new UserServiceImpl();
		User user = new User();
		List<User> list = uDao.findAll();
		String url;
		url = req.getRequestURI();
		if (url.contains("/admin/user-edit.jsp")) {
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/user-overview/edit/")) {
			String id = url.substring(url.lastIndexOf("/") + 1);
			user = uDao.findByUsername(id);
			req.setAttribute("form", user);
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/user-overview.jsp")) {
			req.setAttribute("items", uDao.findAll());
			req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
		} else if (url.contains("create")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			if (check(req, resp)) {
				try {
					String username = req.getParameter("username"); 
					String password = req.getParameter("password"); 
					String email = req.getParameter("email"); 
					String fullname =req.getParameter("fullname");
					userService.create(username, password, email, fullname);
					req.setAttribute("items", uDao.findAll());
					req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);

				} catch (Exception e) {
					req.setAttribute("msg", "Tạo không thành công.");
					e.printStackTrace();
				}
			}
		} else if (url.contains("update")) {
			if (check(req, resp)) {
				try {
					BeanUtils.populate(user, req.getParameterMap());
					uDao.update(user);
					req.setAttribute("items", uDao.findAll());
					req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
				} catch (Exception e) {
					req.setAttribute("msg", "Cập nhập không thành công.");
					e.printStackTrace();
				}
			}

		} else if (url.contains("delete")) {
			try {
				String id = req.getParameter("username");
				User removeUser = userService.findByUsername(id);
				uDao.delete(removeUser);
				req.setAttribute("items", uDao.findAll());
				req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "Delete fail.");
			}
		} else {
			req.setAttribute("items", uDao.findAll());
			req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
		}
	}

	private boolean check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uN = req.getParameter("username");
		String pW = req.getParameter("password");
		String fN = req.getParameter("fullname");
		String email = req.getParameter("email");
		UserDao uDao = new UserDaoImpl();
		User user = new User();
		user = uDao.findByUsername(uN);
		String url = req.getRequestURI();
		String rgx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (uN.equalsIgnoreCase("")) {
			req.setAttribute("msg", "Vui lòng nhập user name.");
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
			return false;
		} else if (pW.equalsIgnoreCase("")) {
			req.setAttribute("msg", "Vui lòng nhập pass word.");
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
			return false;
		} else if (fN.equalsIgnoreCase("")) {
			req.setAttribute("msg", "Vui lòng nhập pass word.");
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
			return false;
		} else if (pW.length() < 6) {
			req.setAttribute("msg", "Mật khẩu phải nhiều hơn 6 kí tự.");
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
			return false;
		} else if (email.matches(rgx) == false) {
			req.setAttribute("msg", "Email không đúng định dạng.");
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
			return false;
		}
		return true;
	}

}
