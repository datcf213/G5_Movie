package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.VideoDao;
import dao.VideoDaoImpl;
import entity.User;
import entity.Video;
import service.VideoService;
import service.VideoServiceImpl;

@WebServlet({ "/admin/video", "/admin/videoEdit.jsp", "/admin/videoOverview.jsp", "/admin/videoEdit/create",
			  "/admin/videoEdit/update", "/admin/videoEdit/delete", "/admin/videoOverview/edit/*" })
public class VideoController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		VideoService videoService = new VideoServiceImpl();
		Video video = new Video();
		VideoDao videoDao = new VideoDaoImpl();
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("currentUser");

		if (url.contains("/admin/videoEdit.jsp")) {
			req.getRequestDispatcher("/views/admin/videoEdit.jsp").forward(req, resp);
		} else if (url.contains("/admin/videoOverview/edit/")) {
			String href = url.substring(url.lastIndexOf("/") + 1);
			video = videoDao.findByHref(href);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/videoEdit.jsp").forward(req, resp);
		} else if (url.contains("/admin/videoOverview")) {
			List<Video> videos = videoService.findAll();
			req.setAttribute("videos", videos);
			req.getRequestDispatcher("/views/admin/videoOverview.jsp").forward(req, resp);
		} else if (url.contains("create")) {
			try {
				BeanUtils.populate(video, req.getParameterMap());
				videoDao.create(video);
				req.setAttribute("video", videoDao.findAll());
				req.getRequestDispatcher("/views/admin/videoOverview.jsp").forward(req, resp);

			} catch (Exception e) {
				req.setAttribute("msg", "Tạo không thành công.");
				e.printStackTrace();
			}
		} else if (url.contains("update")) {
			try {
				BeanUtils.populate(video, req.getParameterMap());
				videoDao.update(video);
				req.setAttribute("video", videoDao.findAll());
				req.getRequestDispatcher("/views/admin/videoOverview.jsp").forward(req, resp);
			} catch (Exception e) {
				req.setAttribute("msg", "Cập nhập không thành công.");
				e.printStackTrace();
			}
		} else if (url.contains("delete")) {
			try {
				String id = req.getParameter("href");
				videoService.delete(id);
				req.setAttribute("video", videoDao.findAll());
				req.getRequestDispatcher("/views/admin/videoOverview.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "Xóa không thành công.");
			}
		}else {
			List<Video> videos = videoService.findAll();
			req.setAttribute("videos", videos);
			req.getRequestDispatcher("/views/admin/videoOverview.jsp").forward(req, resp);
		}
	}
}
