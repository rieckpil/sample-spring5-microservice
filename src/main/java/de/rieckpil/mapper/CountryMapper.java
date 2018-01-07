package de.rieckpil.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import de.rieckpil.domain.Country;
import de.rieckpil.dtos.CountryDTO;

@Mapper(componentModel = "spring")
public interface CountryMapper {

  CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

  CountryDTO countryToCountryDTO(Country country);

  Country countryDTOToCountry(CountryDTO country);

}
