package service;

import java.util.List;

import dao.VideoDao;
import dao.VideoDaoImpl;
import entity.Video;

public class VideoServiceImpl implements VideoService{
	
	private VideoDao dao;
	
	public VideoServiceImpl() {
		dao = new VideoDaoImpl();
	}
	
	@Override
	public Video findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Video> findAllPaging(int pageNumber, int pageSize) {
		return dao.findAllPaging(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		entity.setViews(0);
		entity.setActive(true);
		return dao.create(entity);
	}

	@Override
	public Video update(Video entity) {
		return dao.update(entity);
	}

	@Override
	public Video delete(String href) {
		Video video = findByHref(href);
		video.setActive(false);
		return dao.update(video);
	}

}
