package jotech.springframwork.msscbeerservice.web.controller;

import jotech.springframwork.msscbeerservice.service.BeerService;
import jotech.springframwork.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
@RestController
public class BeerController {

    BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
         //todo the practical impl
     //   return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);

        return new ResponseEntity<>(beerService.findBeerById(beerId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto>  saveNewBeer(@Validated @RequestBody  BeerDto beerDto){

        //todo the practical impl
        beerService.saveBeer(beerDto);
        return new ResponseEntity<>(beerService.saveBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto>  updateBeerById(@PathVariable("beerId")UUID beerId,@Validated @RequestBody BeerDto beerDto){
        //todo the practical impl
        BeerDto beerToUpdate = beerService.updateBeer(beerId,beerDto);//.findBeerById(beerId);
      //  beerToUpdate.setBeerName(beerToUpdate.getBeerName());
      //  beerToUpdate.setPrice(beerToUpdate.getPrice());

    //   BeerDto savedBeer = beerService.saveBeer(beerToUpdate);
        return new ResponseEntity<>( beerToUpdate,HttpStatus.NO_CONTENT);
    }
}
