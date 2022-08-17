package com.musicmanager.api.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String  lastName;
	private Date birthDate;
	private String sex;
	
	@OneToMany(mappedBy = "artist",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Album> albums;
	
	@ManyToMany
	@JoinTable(name = "artist_music",joinColumns = @JoinColumn(name = "artist_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "music_id",referencedColumnName = "id"))
	@JsonIgnore
	private List<Music> musics;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "artist_followers", joinColumns = { @JoinColumn(name = "artist_id",referencedColumnName = "id") },
	inverseJoinColumns = { @JoinColumn(name = "follower_id",referencedColumnName = "id") } )
	@JsonIgnore
	private List<User> followers;
	
	public void addMusic(Music music) {
		this.musics.add(music);
		music.getArtists().add(this);
	}
	
	public void addFollower(User follower) {
		this.followers.add(follower);
		follower.getArtists().add(this);
	}
}
