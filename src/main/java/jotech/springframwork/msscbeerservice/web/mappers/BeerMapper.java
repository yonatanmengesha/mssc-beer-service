package jotech.springframwork.msscbeerservice.web.mappers;

import jotech.springframwork.msscbeerservice.domain.Beer;
import jotech.springframwork.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
