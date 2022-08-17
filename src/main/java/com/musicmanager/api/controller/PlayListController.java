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

import com.musicmanager.api.model.dto.PlayListDto;
import com.musicmanager.api.model.entities.PlayList;
import com.musicmanager.api.service.PlayListService;

@Controller
@RequestMapping("playLists")
public class PlayListController {
	@Autowired
	private PlayListService playListService;
	
	@GetMapping
	public ResponseEntity<List<PlayListDto>> getPlayLists(){
		return ResponseEntity.status(HttpStatus.OK).body(playListService.getPlayLists());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPlayListById(@PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(playListService.getPlayListById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PostMapping("/users/{userId}")
	public ResponseEntity<PlayList> save(@PathVariable long userId,@RequestBody PlayListDto playListDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(playListService.save(userId, playListDto));
	}
	@PutMapping("/users/{userId}")
	public ResponseEntity<?> update(@PathVariable long userId,@RequestBody PlayListDto playListDto){
			return ResponseEntity.status(HttpStatus.OK).body(playListService.update(userId, playListDto));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		try {
			playListService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("The playList deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
