package de.rieckpil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.rieckpil.api.v1.model.CityDTO;
import de.rieckpil.domain.City;

@Mapper
public interface CityMapper {

  CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

  CityDTO cityToCityDTO(City city);

}
