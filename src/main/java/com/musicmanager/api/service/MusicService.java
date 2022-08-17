package com.musicmanager.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.musicmanager.api.model.entities.Artist;
import com.musicmanager.api.mapper.MusicMapper;
import com.musicmanager.api.mapper.UserMapper;
import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Music;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.repository.AlbumRepository;
import com.musicmanager.api.repository.ArtistRepository;
import com.musicmanager.api.repository.MusicRepository;
import com.musicmanager.api.repository.UserRepository;

@Service
public class MusicService {
	@Autowired
	private MusicRepository musicRepository;
	
	@Autowired
	private MusicMapper musicMapper;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public Map<String, Object> getMusics(int page,int size){
		PageRequest paging = PageRequest.of(page, size);
		Page<Music> pageMusics = musicRepository.findAll(paging);
		List<Music> musics = new ArrayList<Music>();
		HashMap<String, Object> response = new HashMap<>();
		musics = pageMusics.getContent();
		if(!musics.isEmpty()) {
			response.put("musics", musicMapper.mapToDtos(musics));
			response.put("currentPage", pageMusics.getNumber());
			response.put("totalItems", pageMusics.getTotalElements());
			response.put("totalPages", pageMusics.getTotalPages());
			return response;
		}
		else
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"the list of musics is empty");
	}
	public MusicDto getMusicById(long id) {
		Music music = musicRepository.findById(id).get();
		if(music != null)
			return musicMapper.mapToDto(music);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	public List<MusicDto> getFeaturingList(long artistId){
		List<Music> artistMusics = musicRepository.findMusicsByArtistsId(artistId);
		List<Artist> artists;
		List<Music> featuringList = new ArrayList<Music>();
		for(Music music:artistMusics) {
			artists = artistRepository.findArtistByMusicsId(music.getId());
			if(artists.size() >= 2) {
				featuringList.add(music);
			}
		}
		return musicMapper.mapToDtos(featuringList);
	}
	public Music save(long albumId,MusicDto musicDto) {
		Music music = musicMapper.mapToEntity(musicDto);
		albumRepository.findById(albumId).map(album -> {
			music.setAlbum(album);
			return musicRepository.save(music);
		});
		return musicRepository.save(music);
	}
	public User saveUser(long musicId,UserDto userDto) {
		musicRepository.findById(musicId).map(music -> {
			music.addUser(userRepository.findById(userMapper.mapToEntity(userDto).getId()).get());
			musicRepository.save(music);
			return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
		});
		return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
	}
	public Music update(long albumId,MusicDto musicDto) {
		Music music = musicMapper.mapToEntity(musicDto);
		albumRepository.findById(albumId).map(album -> {
			music.setAlbum(album);
			return musicRepository.save(music);
		});
		return musicRepository.save(music);
	}
	public void delete(long id) {
		Music music = musicRepository.findById(id).get();
		if(music != null)
			musicRepository.deleteById(id);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
