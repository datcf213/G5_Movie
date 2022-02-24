package service;

import java.util.List;

import entity.Favorite;
import entity.User;
import entity.Video;

public interface FavoriteService {
	
	List<Favorite> findByUser(String username);
	List<Favorite> findByUserAndLike(String username); 
	Favorite findByUserIdAndVideoId(int userId, int videoId);
	Favorite create(User user, Video video);
	boolean updateLike(User user, String href);
	
	
}
