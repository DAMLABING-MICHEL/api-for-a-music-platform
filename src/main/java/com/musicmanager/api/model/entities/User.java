package com.musicmanager.api.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String sex;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<PlayList> playLists;
	
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private List<Music> musics = new ArrayList<Music>();
	
	@ManyToMany(mappedBy = "followers")
	private List<Artist> artists;
	
	@ManyToMany
	@JsonIgnore
	private List<Album> albums = new ArrayList<Album>();
	
	@ManyToMany(mappedBy = "users")
	private List<PlayList> likesPlayLists;
}