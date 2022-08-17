package com.musicmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musicmanager.api.model.dto.ArtistDto;
import com.musicmanager.api.model.entities.Artist;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
	ArtistDto mapToDto(Artist user);
	List<ArtistDto> mapToDtos(List<Artist> artistList);
	Artist mapToEntity(ArtistDto artistDto);
}
