package de.bre.mits.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.bre.mits.entities.Mitarbeiter;
import de.bre.mits.repositories.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.bre.mits.entities.Bestand;
import de.bre.mits.entities.Zutat;
import de.bre.mits.factories.BestandFactory;
import de.bre.mits.repositories.BestandRepository;
import de.bre.mits.repositories.ZutatRepository;

@RestController
public class BestandController {

    private static final int FAILED = 0;
    private static final int OK = 1;
 
    @Autowired
    private ZutatRepository zutatRepository;
    @Autowired 
    private BestandRepository bestandRepository;
    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;
    
    /**
     * Liste des kompletten Besetands
     * @return
     */
    @RequestMapping(path = "/bestaende", method = RequestMethod.GET)
    public List<Zutat> getAllZutaten() {
		return (List<Zutat>) zutatRepository.findAll();
    }
	
    /**
     * Bestand einzelner Zutat einsehen
     * @return
     */
    @RequestMapping(path="/bestaende/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getZutatById(@PathVariable("id") Long id ) {
		Zutat z = zutatRepository.findOne(id);
		if (z == null) return ResponseEntity.notFound().build();
		else return ResponseEntity.ok().body(z);
	}

    /**
     * Meldet zurück, ob Nutzerdaten korrekt sind. Hier wird die Hashing-Funktion aufgerufen.
     * Ursprünglich für oauth2 gedacht
     * @return JSON array to evaulate in app
     */
	@RequestMapping(path="/confirmAuth", method = RequestMethod.GET)
    public ResponseEntity<?> confirmAuth(@RequestParam("username") String username, @RequestParam("password") String password) {
		Mitarbeiter m = mitarbeiterRepository.findByBenutzername(username);
		if (m != null) {
			boolean passwordCorrect = m.isPasswordOK(m, mitarbeiterRepository, username, password);
			if(passwordCorrect) {
				return ResponseEntity.ok().body("[{\"authenticated\":\"true\"}]");
			}
		}
		return ResponseEntity.ok().body("[{\"authenticated\":\"false\"}]");
    }

    /**
     * Zutat ändern
     * @return
     */
    @RequestMapping(path="/zutaten/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> setZutatById(@PathVariable("id") Long id, 
			@RequestParam("bezeichnung") String bezeichnung, @RequestParam("zutatenkategorie") String zutatenkategorie) {
		Zutat z = zutatRepository.findOne(id);
		if (z == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			z.setBezeichnung(bezeichnung);
			z.setZutatenkategorie(zutatenkategorie);
			zutatRepository.save(z);
			return ResponseEntity.ok().body(z);
		}
	}
    
    /**
     * Bestand einzelner Zutat ändern
     * @return
     */
    @RequestMapping(path="/bestaende/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> setZutatById(@PathVariable("id") Long id, 
			@RequestParam("menge") Double mengeNew, @RequestParam("mindestbestand") Double mindestbestandNew) {
		Zutat z = zutatRepository.findOne(id);
		if (z == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			Bestand b = z.getBestand();
			
			Double mengeOld 			= b.getMenge();
			Double mindestbestandOld 	= b.getMindestbestand();
			
			b.setMenge(mengeOld + mengeNew);
			b.setMindestbestand(mindestbestandOld + mindestbestandNew);
			bestandRepository.save(b);
			return ResponseEntity.ok().body(z);
		}
	}
    
    /**
     * Zutat löschen
     * @return
     */
    @RequestMapping(path="/zutaten/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteZutat(@PathVariable("id") Long id) {
		if (zutatRepository.exists(id)) {
			zutatRepository.delete(id);
		    return ResponseEntity.ok().build();
		} 
		else return ResponseEntity.notFound().build();
    }
    
    
    /**
     * Neue Zutat hinzufügen
     * @return
     */
    @RequestMapping(value = "/neuZutat", method = RequestMethod.PUT)
    public ResponseEntity <?> persistZutat(
    		@RequestParam("bezeichnung") String bezeichnung, @RequestParam("kategorie") String kategorie,
    		@RequestParam("menge") Double menge, @RequestParam("mindestbestand") Double mindestbestand,
    		@RequestParam("einheit") String einheit) 
    {
    	Zutat z = new Zutat(bezeichnung, kategorie);
    	Bestand b = new BestandFactory().createBestand(z, menge, mindestbestand, einheit);
    	z.setBestand(b);
    	zutatRepository.save(z);
    	
    	URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
    					.path("/{id}").buildAndExpand( z.getId() ).toUri();
		return ResponseEntity.created( location ).body( z );
    }
    
    
    /**
     * Zutaten mit kritischem Bestand zurückgeben
     * @return
     */
    @RequestMapping(path="/kritBestaende", method = RequestMethod.GET)
	public ResponseEntity<?> getKritBetaende() {
		Iterable<Zutat> zutatIterable = zutatRepository.findAll();
		List<Zutat> zutatListe = new ArrayList<Zutat>();
		
		for (Zutat zutat : zutatIterable) {
			Bestand b = zutat.getBestand();
			if (b.getMenge() < b.getMindestbestand()) {
				zutatListe.add(zutatRepository.findOne(b.getId()));
			}
		}
		
		if (zutatListe.isEmpty()) return ResponseEntity.notFound().build();
		else return ResponseEntity.ok().body(zutatListe);
	}
    
}
