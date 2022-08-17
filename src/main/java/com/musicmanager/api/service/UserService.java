package com.musicmanager.api.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.musicmanager.api.mapper.MusicMapper;
import com.musicmanager.api.mapper.PlayListMapper;
import com.musicmanager.api.mapper.UserMapper;
import com.musicmanager.api.model.entities.*;
import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.dto.PlayListDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.repository.MusicRepository;
import com.musicmanager.api.repository.PlayListRepository;
import com.musicmanager.api.repository.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserMapper userMapper;
	
	@Autowired
	private PlayListRepository playListRepository;
	
	@Autowired
	private PlayListMapper playListMapper;
	
	public List<UserDto> getUsers(){
		List<User> users = (List<User>) userRepository.findAll();
		if(!users.isEmpty())
		return userMapper.mapToDtos(users);
		else
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"the list of users is empty");
	}
	
	public UserDto getUserById(long id) {
		User user = userRepository.findById(id).get();
		if(user != null)
			return userMapper.mapToDto(user);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public List<PlayListDto> getPlayListsByUserId(long userId){
		User user = userRepository.findById(userId).get();
		List<PlayList> playLists = playListRepository.findByUserId(userId);
		if(user == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else if(playLists.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		else
			return playListMapper.mapToDtos(playLists);
	}
	public User save(UserDto userDto) {
			return userRepository.save(userMapper.mapToEntity(userDto));
	}
	public User update(UserDto userDto) {
		return userRepository.save(userMapper.mapToEntity(userDto));
	}
	
	public void delete(long id) {
		User user = userRepository.findById(id).get();
		if(user.getId() == id)
			userRepository.deleteById(id);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
