package service;

import java.sql.Timestamp;
import java.util.List;

import dao.FavoriteDao;
import dao.FavoriteDaoImple;
import entity.Favorite;
import entity.User;
import entity.Video;

public class FavoriteServiceImpl implements FavoriteService{
	
	private FavoriteDao dao;
	private VideoService videoService = new VideoServiceImpl();
	
	public FavoriteServiceImpl() {
		dao = new FavoriteDaoImple();
	}
	
	@Override
	public List<Favorite> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public List<Favorite> findByUserAndLike(String username) {
		return dao.findByUserAndLike(username);
	}

	@Override
	public Favorite findByUserIdAndVideoId(int userId, int videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public Favorite create(User user, Video video) {
		Favorite exisFavorite = findByUserIdAndVideoId(user.getUserId(), video.getId());
		if(exisFavorite == null) {
			exisFavorite = new Favorite();
			exisFavorite.setUser(user);
			exisFavorite.setVideo(video);
			exisFavorite.setViewDate(new Timestamp(System.currentTimeMillis()));
			exisFavorite.setLike(false);
			return dao.create(exisFavorite);
		}
		return exisFavorite;
	}

	@Override
	public boolean updateLike(User user, String href) {
		Video video = videoService.findByHref(href);
		Favorite exisFavorite = findByUserIdAndVideoId(user.getUserId(), video.getId());
		if(exisFavorite.isLike()==false) {
			exisFavorite.setLike(true);
			exisFavorite.setLikeDate(new Timestamp(System.currentTimeMillis()));
		}else {
			exisFavorite.setLike(false);
			exisFavorite.setLikeDate(null);
		}
		Favorite updateFavorite = dao.update(exisFavorite); 
		return updateFavorite != null ? true : false;
	}

}
