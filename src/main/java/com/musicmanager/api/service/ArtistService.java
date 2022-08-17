package com.musicmanager.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicmanager.api.mapper.AlbumMapper;
import com.musicmanager.api.mapper.ArtistMapper;
import com.musicmanager.api.mapper.MusicMapper;
import com.musicmanager.api.mapper.UserMapper;
import com.musicmanager.api.model.dto.AlbumDto;
import com.musicmanager.api.model.dto.ArtistDto;
import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Album;
import com.musicmanager.api.model.entities.Artist;
import com.musicmanager.api.model.entities.Music;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.repository.AlbumRepository;
import com.musicmanager.api.repository.ArtistRepository;
import com.musicmanager.api.repository.MusicRepository;
import com.musicmanager.api.repository.UserRepository;

@Service
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired 
	private ArtistMapper artistMapper;
	
	@Autowired
	private MusicMapper musicMapper;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private AlbumMapper albumMapper;
	
	@Autowired
	private MusicRepository musicRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	public List<ArtistDto> getArtists(){
		List<Artist> artists = (List<Artist>) artistRepository.findAll();
		return artistMapper.mapToDtos(artists);
	}
	
	public List<AlbumDto> getAlbumsByArtistId(long id){
		List<Album> albums = albumRepository.findByArtistId(id);
		return albumMapper.mapToDtos(albums);
	}
	public List<MusicDto> getMusicsByArtistId(long artistId) {
		List<Music> musics = musicRepository.findMusicsByArtistsId(artistId);
		return musicMapper.mapToDtos(musics);
	}
	public List<ArtistDto> getArtistsByMusicsId(long musicId){
		return artistMapper.mapToDtos(artistRepository.findArtistByMusicsId(musicId));
	} 
	public List<UserDto> getFollowersByAristsId(long artistId){
		List<User> followers = userRepository.findFollowersByArtistsId(artistId);
		return userMapper.mapToDtos(followers);
	}
	public Artist save(ArtistDto artistDto) {
		return artistRepository.save(artistMapper.mapToEntity(artistDto));
	}
	public Music saveMusic(long artistId,MusicDto musicDto) {
		artistRepository.findById(artistId).map(artist -> {
			artist.addMusic(musicRepository.findById(musicMapper.mapToEntity(musicDto).getId()).get());
			artistRepository.save(artist);
			return musicRepository.findById(musicMapper.mapToEntity(musicDto).getId()).get();
		});
		return musicRepository.findById(musicMapper.mapToEntity(musicDto).getId()).get();
	}
	public User saveFollower(long artistId,UserDto userDto) {
		artistRepository.findById(artistId).map(artist -> {
			artist.addFollower(userRepository.findById(userDto.getId()).get());
			artistRepository.save(artist);
			return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
		});
		return userRepository.findById(userMapper.mapToEntity(userDto).getId()).get();
	}
	public void delete(long id) {
		artistRepository.deleteById(id);
	}
}
