package it.sterz.reservationsystem.mapper;

import it.sterz.reservationsystem.dto.UserDto;
import it.sterz.reservationsystem.dto.response.UserResponseDto;
import it.sterz.reservationsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "madeReservations", ignore = true)
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDto);

    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toResponseDtoList(List<User> users);
}