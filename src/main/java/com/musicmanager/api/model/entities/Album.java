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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "artist_id",referencedColumnName = "id")
	@JsonIgnore
	private Artist artist;
	
	@OneToMany(mappedBy = "album",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Music> musics;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "album_likes",joinColumns = @JoinColumn(name = "album_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
	@JsonIgnore
	private List<User> likes;
	
	public void addLiked(User user) {
		this.likes.add(user);
		user.getAlbums().add(this);
	}
	public void removeLiked(long userId) {
		User user = this.likes.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
		if(user!=null) {
			this.likes.remove(user);
			user.getAlbums().remove(this);
		}
	}
}
