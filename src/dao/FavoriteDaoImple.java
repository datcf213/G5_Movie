package dao;

import java.util.List;

import entity.Favorite;
import entity.Video;

public class FavoriteDaoImple extends GeneralDao<Favorite> implements FavoriteDao{

	@Override
	public List<Favorite> findByUser(String username) {
		String sql = "SELECT o FROM Favorite o WHERE o.user.username = ?0 AND o.video.active = 1 ORDER BY o.viewDate DESC";
		return super.findMany(Favorite.class, sql, username);
	}

	@Override
	public List<Favorite> findByUserAndLike(String username) {
		String sql = "SELECT o FROM Favorite o WHERE o.user.username = ?0 AND o.isLike = 1 AND o.video.active = 1 ORDER BY o.viewDate DESC";
		return super.findMany(Favorite.class, sql, username);
	}

	@Override
	public Favorite findByUserIdAndVideoId(int userId, int videoId) {
		String sql = "SELECT o FROM Favorite o WHERE o.user.userId = ?0 AND o.video.id = ?1 AND o.video.active = 1";
		return super.findOne(Favorite.class, sql, userId, videoId);
	}

}
