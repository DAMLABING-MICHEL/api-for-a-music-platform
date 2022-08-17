package com.musicmanager.api.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.musicmanager.api.model.dto.AlbumDto;
import com.musicmanager.api.model.entities.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
	List<AlbumDto> mapToDtos(List<Album> albumList);
	Album mapToEntity(AlbumDto albumDto);
	AlbumDto mapToDto(Album album);
}
