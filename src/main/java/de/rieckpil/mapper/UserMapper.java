package de.rieckpil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import de.rieckpil.dtos.UserDTO;
import de.rieckpil.security.User;

@Mapper(componentModel="spring")
public interface UserMapper {
  
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
  
  User userDtoToUser(UserDTO userDto);

}
