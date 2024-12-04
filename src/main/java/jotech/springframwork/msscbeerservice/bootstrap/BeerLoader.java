package jotech.springframwork.msscbeerservice.bootstrap;

import jotech.springframwork.msscbeerservice.domain.Beer;
import jotech.springframwork.msscbeerservice.repositories.BeerRepository;
import jotech.springframwork.msscbeerservice.service.BeerService;
import jotech.springframwork.msscbeerservice.web.mappers.BeerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class BeerLoader  implements CommandLineRunner {


    private final BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    public BeerLoader(BeerService beerService) {
        this.beerService = beerService;
    }


    @Override
    public void run(String... args) throws Exception {

        loadBeerObject();
    }

    private void loadBeerObject() {


        if(beerService.count()==0){

            beerService.saveBeer(  beerMapper.beerToBeerDto(Beer
                    .builder()
                    .beerName("Denzens Beer")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(1234567890L)
                    .price( new BigDecimal(12.95))
                    .build()));


            beerService.saveBeer( beerMapper.beerToBeerDto(Beer
                    .builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(1234567890996L)
                    .price( new BigDecimal(11.95))
                    .build()));
        }

      //  System.out.println("Loaded beer count " + beerRepository.count());
    }
}
