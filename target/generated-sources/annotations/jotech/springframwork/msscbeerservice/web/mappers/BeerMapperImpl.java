package jotech.springframwork.msscbeerservice.web.mappers;

import javax.annotation.processing.Generated;
import jotech.springframwork.msscbeerservice.domain.Beer;
import jotech.springframwork.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T10:28:25-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDto beerDto = new BeerDto();

        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        Beer beer = new Beer();

        return beer;
    }
}
