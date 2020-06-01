package project.mundofii.repository;

import org.springframework.data.repository.CrudRepository;

import project.mundofii.domain.PriceHistory;

public interface PriceHistoryRepository extends CrudRepository<PriceHistory, String>{

}
