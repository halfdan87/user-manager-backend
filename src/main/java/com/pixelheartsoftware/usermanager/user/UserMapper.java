package com.pixelheartsoftware.usermanager.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> employees);

    User toUser(UserDto userDto);
}
