package com.musicmanager.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.musicmanager.api.mapper.AlbumMapper;
import com.musicmanager.api.mapper.UserMapper;
import com.musicmanager.api.model.dto.AlbumDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Album;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.repository.AlbumRepository;
import com.musicmanager.api.repository.ArtistRepository;
import com.musicmanager.api.repository.UserRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired 
	private AlbumMapper albumMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<AlbumDto> getAlbums(){
		List<Album> albums = (List<Album>) albumRepository.findAll();
		return albumMapper.mapToDtos(albums);
	}
	public AlbumDto getAlbumById(long id) {
		Album album = albumRepository.findById(id).get();
		if(album != null)
			return albumMapper.mapToDto(album);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<UserDto> getLiskesByAlbumId(long albumId){
		List<User> likes = userRepository.findLikesByAlbumsId(albumId);
		return userMapper.mapToDtos(likes);
	}
	public Album save(long artistId,AlbumDto albumDto) {
		Album album = albumMapper.mapToEntity(albumDto);
		artistRepository.findById(artistId).map(artist -> {
			album.setArtist(artist);
			return albumRepository.save(album);
		});
		return albumRepository.save(album);
	}
	public User saveUser(long albumId,UserDto userDto) {
		albumRepository.findById(albumId).map(album -> {
			album.addLiked(userRepository.findById(userMapper.mapToEntity(userDto).getId()).get());
			albumRepository.save(album);
			return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
		});
		return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
	}
	
	public Album update(long artistId,AlbumDto albumDto) {
		Album album = albumMapper.mapToEntity(albumDto);
		artistRepository.findById(artistId).map(artist -> {
			album.setArtist(artist);
			return albumRepository.save(album);
		});
		return albumRepository.save(album);
	}
	
	public void delete(long id) {
		albumRepository.deleteById(id);
	}
	public void deleteLiked(long albumId,long userId) {
		Album album = albumRepository.findById(albumId).get();
		album.removeLiked(userId);
		albumRepository.save(album);
	}
}
