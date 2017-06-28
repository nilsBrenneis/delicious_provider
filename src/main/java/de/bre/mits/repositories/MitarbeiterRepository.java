package de.bre.mits.repositories;


import de.bre.mits.entities.Mitarbeiter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MitarbeiterRepository extends CrudRepository<Mitarbeiter, Long> {
	List<Mitarbeiter> findByVorname(String vorname);
	List<Mitarbeiter> findByName(String name);
	Mitarbeiter findByBenutzername(String benutzername);
}

