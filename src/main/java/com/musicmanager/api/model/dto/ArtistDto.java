package com.musicmanager.api.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
	private long id;
	private String firstName;
	private String  lastName;
	private Date birthDate;
	private String sex;
}
