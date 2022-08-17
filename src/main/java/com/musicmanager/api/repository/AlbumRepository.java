package com.musicmanager.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musicmanager.api.model.entities.Album;


@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{
	List<Album> findByArtistId(long id);
}
