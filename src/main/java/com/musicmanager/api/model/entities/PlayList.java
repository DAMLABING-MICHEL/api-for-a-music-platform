package com.musicmanager.api.model.entities;

import java.time.LocalDateTime;
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
public class PlayList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_playList",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "playList_id",referencedColumnName = "id"))
	@JsonIgnore
	private List<User> users;
}
