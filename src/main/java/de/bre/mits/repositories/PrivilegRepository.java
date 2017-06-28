package de.bre.mits.repositories;

import org.springframework.data.repository.CrudRepository;
import de.bre.mits.entities.Privileg;

public interface PrivilegRepository extends CrudRepository<Privileg, Long> {
	Privileg findByBezeichnung(String bezeichnung);
}
