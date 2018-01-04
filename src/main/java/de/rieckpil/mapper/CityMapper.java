package de.rieckpil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.rieckpil.domain.City;
import de.rieckpil.dtos.CityDTO;

@Mapper(componentModel = "spring")
public interface CityMapper {

  CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

  CityDTO cityToCityDTO(City city);

}
