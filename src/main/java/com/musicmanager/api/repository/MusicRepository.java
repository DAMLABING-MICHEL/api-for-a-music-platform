package com.musicmanager.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicmanager.api.model.entities.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long>{
	List<Music> findMusicsByArtistsId(long id);
}
