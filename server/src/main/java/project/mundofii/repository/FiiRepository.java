package project.mundofii.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.mundofii.domain.Fii;

@Repository
public interface FiiRepository extends CrudRepository<Fii, String>, QuerydslPredicateExecutor<Fii> {
	
	public Fii findByName(String name);
	
}
