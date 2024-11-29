package guru.springframwork.msscbeerservice.web.mappers;

import guru.springframwork.msscbeerservice.domain.Beer;
import guru.springframwork.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses={DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
