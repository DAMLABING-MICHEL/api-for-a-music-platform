package com.musicmanager.api.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musicmanager.api.model.entities.Artist;
import com.musicmanager.api.model.entities.Music;


@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long>{
	List<Artist> findArtistByMusicsId(long id);
}
