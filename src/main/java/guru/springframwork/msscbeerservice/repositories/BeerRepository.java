package guru.springframwork.msscbeerservice.repositories;

import guru.springframwork.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository  extends PagingAndSortingRepository<Beer, UUID> {
}
