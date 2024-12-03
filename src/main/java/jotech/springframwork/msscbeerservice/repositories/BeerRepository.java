package jotech.springframwork.msscbeerservice.repositories;

import jotech.springframwork.msscbeerservice.domain.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository  extends PagingAndSortingRepository<Beer, UUID> , CrudRepository<Beer,UUID> {
}
