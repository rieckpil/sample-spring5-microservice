package de.rieckpil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import de.rieckpil.domain.DomainMessage;
import de.rieckpil.dtos.MessageDTO;

@Mapper(componentModel="spring")
public interface MessageMapper {
  
  MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

  DomainMessage messageDtoToMessage(MessageDTO messageDto);

}
