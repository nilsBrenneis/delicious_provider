package de.bre.mits.repositories;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import de.bre.mits.entities.Zutat;

public interface ZutatRepository extends CrudRepository<Zutat, Long>{
	List<Zutat> findByBezeichnung(String bezeichnung);
	Zutat findOneByBezeichnung(String bezeichnung);
}

