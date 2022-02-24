package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Favorite;
import entity.User;
import entity.Video;
import service.FavoriteService;
import service.FavoriteServiceImpl;
import service.VideoService;
import service.VideoServiceImpl;

@WebServlet("/detail")
public class DetailController extends HttpServlet{
	private VideoService videoService = new VideoServiceImpl();
	private FavoriteService favoriteService = new FavoriteServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		
		switch (action) {
			case "watch":
				doGetDetail(session, href, req, resp);
				break;
			case "like":
				doGetLike(session, href, req, resp);
				break;
		}
	}
	
	private void doGetDetail(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser!=null) {
			Favorite favorite = favoriteService.create(currentUser, video);
			req.setAttribute("flagLike", favorite.isLike());
		}
		
		req.getRequestDispatcher("/views/user/detail.jsp").forward(req, resp);
	}
	
	private void doGetLike(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		User currentUser = (User) session.getAttribute("currentUser");
		boolean result = favoriteService.updateLike(currentUser, href);
		if(result==true) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
}
