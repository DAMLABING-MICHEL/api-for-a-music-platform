package com.musicmanager.api.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.Music;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.service.MusicService;

@Controller
@RequestMapping("musics")
public class MusicController {
	@Autowired
	private MusicService musicService;
	
	@GetMapping("")
	public ResponseEntity<?> getMusics(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "2") int size){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(musicService.getMusics(page,size));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		}
	}
	
	@PostMapping("/albums/{albumId}")
	public ResponseEntity<Music> save(@PathVariable long albumId,@RequestBody MusicDto musicDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(musicService.save(albumId,musicDto));
	}
	@PostMapping("{musicId}/likes")
	public ResponseEntity<User> saveUser(@PathVariable long musicId,@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(musicService.saveUser(musicId, userDto));
	}
	@PutMapping("/albums/{albumId}")
	public ResponseEntity<Music> update(@PathVariable long albumId,@RequestBody MusicDto musicDto){
		return ResponseEntity.status(HttpStatus.OK).body(musicService.update(albumId, musicDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		musicService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("the music deleted successfully");
	}
}
