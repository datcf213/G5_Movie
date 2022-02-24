package dao;

import java.util.List;

import entity.Favorite;

public interface FavoriteDao {
	
	List<Favorite> findByUser(String username);
	List<Favorite> findByUserAndLike(String username); 
	Favorite findByUserIdAndVideoId(int userId, int videoId);
	Favorite create(Favorite entity);
	Favorite update(Favorite entity);
	
}
