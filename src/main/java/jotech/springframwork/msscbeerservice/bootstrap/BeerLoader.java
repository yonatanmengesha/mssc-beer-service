package jotech.springframwork.msscbeerservice.bootstrap;

import jotech.springframwork.msscbeerservice.domain.Beer;
import jotech.springframwork.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class BeerLoader  implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        loadBeerObject();
    }

    private void loadBeerObject() {


        if(beerRepository.count()==0){

            beerRepository.save(Beer
                    .builder()
                    .beerName("Denzens Beer")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(1234567890L)
                    .price( new BigDecimal(12.95))
                    .build());


            beerRepository.save(Beer
                    .builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(1234567890996L)
                    .price( new BigDecimal(11.95))
                    .build());
        }

      //  System.out.println("Loaded beer count " + beerRepository.count());
    }
}
