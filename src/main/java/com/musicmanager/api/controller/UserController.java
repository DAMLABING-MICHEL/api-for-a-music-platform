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
import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.User;
import com.musicmanager.api.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<?> getUsers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		}
	}
	
	@GetMapping("/{userId}/playLists")
	public ResponseEntity<?> getPlayListsByUserId(@PathVariable long userId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getPlayListsByUserId(userId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserDto userDto){
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
	}
	@PutMapping
	public ResponseEntity<User> update(@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id){
		try {
			userService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("the user deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
