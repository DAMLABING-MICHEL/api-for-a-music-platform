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
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Album;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.service.AlbumService;

@Controller
@RequestMapping("albums")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	
	@GetMapping
	public ResponseEntity<List<AlbumDto>> getAlbums(){
		return ResponseEntity.status(HttpStatus.OK).body(albumService.getAlbums());
	}
	
	@GetMapping("/{albumId}")
	public ResponseEntity<AlbumDto> getAlbumById(@PathVariable long albumId){
		return ResponseEntity.status(HttpStatus.OK).body(albumService.getAlbumById(albumId));
	}
	@GetMapping("/{albumId}/likes")
	public ResponseEntity<List<UserDto>> getLikesByAlbumId(@PathVariable long albumId){
		return ResponseEntity.status(HttpStatus.OK).body(albumService.getLiskesByAlbumId(albumId));
	}
	@PostMapping("/artists/{artistId}")
	public ResponseEntity<Album> save(@PathVariable(value = "artistId" )long artistId,@RequestBody AlbumDto albumDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.save(artistId, albumDto));
	}
	
	@PostMapping("/{albumId}/likes")
	public ResponseEntity<User> saveUser(@PathVariable long albumId,@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.saveUser(albumId, userDto));
	}
	@PutMapping("/artists/{artistId}")
	public ResponseEntity<Album> update(@PathVariable long artistId,@RequestBody AlbumDto albumDto){
		return ResponseEntity.status(HttpStatus.OK).body(albumService.update(artistId, albumDto));
	}
	
	@DeleteMapping("/{albumId}")
	public ResponseEntity<?> delete(@PathVariable int albumId){
		albumService.delete(albumId);
		return ResponseEntity.status(HttpStatus.OK).body("the album deleted successfully");
	}
	@DeleteMapping("/{albumId}/likes/{userId}")
	public ResponseEntity<?> deleteLikedFromAlbum(@PathVariable long albumId,@PathVariable long userId){
		albumService.deleteLiked(albumId, userId);
		return ResponseEntity.status(HttpStatus.OK).body("the liked deleted successfully from album");
	}
}
