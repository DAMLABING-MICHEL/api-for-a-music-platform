package com.musicmanager.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musicmanager.api.model.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findFollowersByArtistsId(long id);
	List<User> findLikesByAlbumsId(long id);
}
