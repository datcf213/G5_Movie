package dao;

import java.util.List;

import entity.Video;

public class VideoDaoImpl extends GeneralDao<Video> implements VideoDao{

	@Override
	public Video findById(int id) {
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String sql = "SELECT o FROM Video o WHERE href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAllPaging(int pageNumber, int pageSize) {
		return super.findAllPaging(Video.class, true, pageNumber, pageSize);
	}
}
