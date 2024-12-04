package jotech.springframwork.msscbeerservice.service;

import jotech.springframwork.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto findBeerById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId,BeerDto beerDto);

    Long count();
}
