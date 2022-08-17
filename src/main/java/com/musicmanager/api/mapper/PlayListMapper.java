package com.musicmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musicmanager.api.model.dto.PlayListDto;
import com.musicmanager.api.model.entities.PlayList;

@Mapper(componentModel = "spring")
public interface PlayListMapper {
	List<PlayListDto> mapToDtos(List<PlayList> playLists);
	PlayListDto mapToDto(PlayList playList);
	PlayList mapToEntity(PlayListDto playListDto);
}
