package com.musicmanager.api.model.entities;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "album_id",referencedColumnName = "id")
	@JsonIgnore
	private Album album;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "music_likes",joinColumns = @JoinColumn(name = "music_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
	@JsonIgnore
	private List<User> users = new ArrayList<User>();
	
	@ManyToMany(mappedBy = "musics")
	@JsonIgnore
	private List<Artist> artists;
	
	public void addUser(User user) {
		this.users.add(user);
		user.getMusics().add(this);
	}
}
