package jotech.springframwork.msscbeerservice.service;

import jotech.springframwork.msscbeerservice.domain.Beer;
import jotech.springframwork.msscbeerservice.repositories.BeerRepository;
import jotech.springframwork.msscbeerservice.web.mappers.BeerMapper;
import jotech.springframwork.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {


    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;


    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

//    @Autowired
//    public BeerServiceImpl(BeerMapper beerMapper) {
//        this.beerMapper = beerMapper;
//    }


    @Override
    public BeerDto findBeerById(UUID beerId) {

       Optional<Beer>  optionalBeer = beerRepository.findById(beerId);
        if(optionalBeer.isPresent() ){

            return  beerMapper.beerToBeerDto(optionalBeer.get());
        }

        return null;

    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {

        Beer beerDto1 = beerMapper.beerDtoToBeer(beerDto);
        return beerMapper.beerToBeerDto(beerRepository.save(beerDto1));
    }

    @Override
    public BeerDto updateBeer(UUID beeerId ,BeerDto beerDto) {

      //  if(b)
      Optional<Beer>  beerOptional = beerRepository.findById(beeerId);
      if(beerOptional.isPresent()){

          Beer existingBeer = beerOptional.get();
          existingBeer.setBeerName(beerDto.getBeerName());
          existingBeer.setBeerStyle(beerDto.getBeerStyle().name());
        ;
          return beerMapper.beerToBeerDto(  beerRepository.save(existingBeer));
      }else {
          return null;
      }





    }

}
