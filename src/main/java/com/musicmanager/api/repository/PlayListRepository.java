package com.musicmanager.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.musicmanager.api.model.entities.PlayList;

@Repository
public interface PlayListRepository extends CrudRepository<PlayList,Long>{
	List<PlayList> findByUserId(long id);
}
