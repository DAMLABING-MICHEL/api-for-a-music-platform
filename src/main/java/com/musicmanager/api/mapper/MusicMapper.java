package com.musicmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musicmanager.api.model.dto.MusicDto;
import com.musicmanager.api.model.entities.Music;

@Mapper(componentModel = "spring")
public interface MusicMapper {
	MusicDto mapToDto(Music music);
	List<MusicDto> mapToDtos(List<Music> musicList);
	Music mapToEntity(MusicDto musicDto);
}
