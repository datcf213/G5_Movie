package controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet({ "/index", "/favorite", "/history" })
public class IndexController extends HttpServlet {

	private VideoService videoService = new VideoServiceImpl();
	private FavoriteService favoriteService = new FavoriteServiceImpl();
	private int maxPageSize = 3;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		switch (path) {
		case "/index":
			doGetIndex(req, resp);
			break;
		case "/favorite":
			doGetFavorite(session, req, resp);
			break;
		case "/history":
			doGetHistory(session, req, resp);
			break;
		}
	}

	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo= videoService.findAll();
		int maxPage =  (int) Math.ceil(countVideo.size() / (double) maxPageSize);
		req.setAttribute("maxPage", maxPage);
		
		List<Video> videos;
		
		String pageNumber = req.getParameter("page");
		if(pageNumber == null) {
			videos = videoService.findAllPaging(Integer.valueOf(1), maxPageSize);
			req.setAttribute("currentPage", 1);
		}else {
			videos = videoService.findAllPaging(Integer.valueOf(pageNumber), maxPageSize);
			req.setAttribute("currentPage", pageNumber);
		}
		
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}
	
	private void doGetFavorite(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute("currentUser");
		List<Favorite> favorite = favoriteService.findByUserAndLike(user.getUsername());
		List<Video> videos = new ArrayList<>();
		favorite.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/favorite.jsp").forward(req, resp);
	}
	
	private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute("currentUser");
		List<Favorite> favorite = favoriteService.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		favorite.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);
	}
}
