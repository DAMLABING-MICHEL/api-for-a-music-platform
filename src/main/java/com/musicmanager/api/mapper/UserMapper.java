package com.musicmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.musicmanager.api.model.dto.UserDto;
import com.musicmanager.api.model.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
		UserDto mapToDto(User user);
		List<UserDto> mapToDtos(List<User> userList);
		User mapToEntity(UserDto userDto);
}
