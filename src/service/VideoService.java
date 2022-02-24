package service;

import java.util.List;

import entity.Video;

public interface VideoService {
	Video findById(int id);
	Video findByHref(String href);
	List<Video> findAll();
	List<Video> findAllPaging(int pageNumber, int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(String href);
}
