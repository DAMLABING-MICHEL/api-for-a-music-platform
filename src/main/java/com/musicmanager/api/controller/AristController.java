package com.musicmanager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musicmanager.api.model.dto.AlbumDto;
import com.musicmanager.api.model.dto.ArtistDto;
import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Artist;
import com.musicmanager.api.model.entities.Music;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.service.ArtistService;
import com.musicmanager.api.service.MusicService;


@Controller
@RequestMapping("artists")
public class AristController {
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private MusicService musicService;
	
	@GetMapping("")
	public ResponseEntity<List<ArtistDto>> getArtists(){
		return ResponseEntity.status(HttpStatus.OK).body(artistService.getArtists());
	}
	
	@GetMapping("/{artistId}/albums")
	public ResponseEntity<List<AlbumDto>> getAlbumsByArtistId(@PathVariable long artistId){
		return ResponseEntity.status(HttpStatus.OK).body(artistService.getAlbumsByArtistId(artistId));
	}
	
	@GetMapping("/{artistId}/musics")
	public ResponseEntity<List<MusicDto>> getMusicsByArtistId(@PathVariable long artistId){
		return ResponseEntity.status(HttpStatus.OK).body( artistService.getMusicsByArtistId(artistId));
	}
	@GetMapping("/{artistId}/followers")
	public ResponseEntity<List<UserDto>> getFollowersByArtistId(@PathVariable long artistId){
		return ResponseEntity.status(HttpStatus.OK).body( artistService.getFollowersByAristsId(artistId));
	}
	@GetMapping("/musics/{musicId}")
	public ResponseEntity<List<ArtistDto>> getArtistsByMusicsId(@PathVariable long musicId){
		return ResponseEntity.status(HttpStatus.OK).body(artistService.getArtistsByMusicsId(musicId));
	}
	@GetMapping("/{artistId}/featuring")
	public ResponseEntity<List<MusicDto>> getFeaturingList(@PathVariable long artistId){
		return ResponseEntity.status(HttpStatus.OK).body(musicService.getFeaturingList(artistId));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getArtistById(@PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(artistService.getArtistById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PostMapping
	public ResponseEntity<Artist> save(@RequestBody ArtistDto artistDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(artistService.save(artistDto));
	}
	@PostMapping("/{artistId}/musics")
	public ResponseEntity<Music> saveMusic(@PathVariable long artistId,@RequestBody MusicDto musicDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(artistService.saveMusic(artistId,musicDto));
	}
	@PostMapping("/{artistId}/followers")
	public ResponseEntity<User> saveFollower(@PathVariable long artistId,@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(artistService.saveFollower(artistId,userDto));
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ArtistDto artistDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(artistService.update(artistDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		artistService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("the artist deleted successfully");
	}
}
