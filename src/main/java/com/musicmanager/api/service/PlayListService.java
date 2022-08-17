package com.musicmanager.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.musicmanager.api.mapper.PlayListMapper;
import com.musicmanager.api.model.dto.PlayListDto;
import com.musicmanager.api.model.entities.PlayList;
import com.musicmanager.api.repository.PlayListRepository;
import com.musicmanager.api.repository.UserRepository;

@Service
public class PlayListService {
	@Autowired
	private PlayListRepository playListRepository;
	
	@Autowired
	private PlayListMapper playListMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<PlayListDto> getPlayLists(){
		List<PlayList> playLists = (List<PlayList>) playListRepository.findAll();
		return playListMapper.mapToDtos(playLists);
	}
	public PlayListDto getPlayListById(long id) {
		PlayList playList = playListRepository.findById(id).get();
		if(playList != null)
			return playListMapper.mapToDto(playList);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	public PlayList save(long userId,PlayListDto playListDto) {
		PlayList playList = playListMapper.mapToEntity(playListDto);
		userRepository.findById(userId).map(user -> {
			playList.setUser(user);
			return playListRepository.save(playList);
		});
		return playListRepository.save(playList);
	}
	public PlayList update(long userId,PlayListDto playListDto) {
		PlayList playList = playListMapper.mapToEntity(playListDto);
		userRepository.findById(userId).map(user -> {
			playList.setUser(user);
			return playListRepository.save(playList);
		});
		return playListRepository.save(playList);
	}
	public void delete(long id) {
		PlayList playList = playListRepository.findById(id).get();
		if(playList != null)
			playListRepository.deleteById(id);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
