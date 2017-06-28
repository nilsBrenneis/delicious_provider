package de.bre.mits;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.bre.mits.entities.*;
import de.bre.mits.factories.*;
import de.bre.mits.repositories.*;

@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {  
    
	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;
	@Autowired
    private ZutatRepository zutatRepository;
	@Autowired
	private PrivilegRepository privilegRepository;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

    	createData();
    }
    public SampleData() {
	
    }
    
    public void createData(){
    	Privileg bestaendeAendern = new Privileg("Bestaende veraendern");
    	Privileg mbSetzen = new Privileg("MB setzen");
    	Privileg mbEinsehen = new Privileg("MB einsehen");
    	privilegRepository.save(bestaendeAendern);
    	privilegRepository.save(mbSetzen);
    	privilegRepository.save(mbEinsehen);
    	
    	
    	
    	
    	Set<Privileg> mitarbeiter = new HashSet<Privileg>();
    	mitarbeiter.add(bestaendeAendern);
    	privilegRepository.save(mitarbeiter);
    	
    	Set<Privileg> chefkoch = new HashSet<Privileg>();
    	chefkoch.add(mbSetzen);
    	chefkoch.add(bestaendeAendern);
    	privilegRepository.save(chefkoch);
    	
    	Set<Privileg> manager = new HashSet<Privileg>();
    	manager.add(mbSetzen);
    	manager.add(bestaendeAendern);
    	manager.add(mbEinsehen);
    	privilegRepository.save(manager);
    	
    	mitarbeiterRepository.save(new Mitarbeiter("Max","Mustermann","maxmu", new Date(),manager));
    	mitarbeiterRepository.save(new Mitarbeiter("Chef","Koch","chef", new Date(),chefkoch));
    	mitarbeiterRepository.save(new Mitarbeiter("Horst","Heinrich","hohe",new Date(),mitarbeiter));
    	
    	
    	
    	Saisonverfuegbarkeit ganzesjahr = new Saisonverfuegbarkeit(true,true,true,true,true,true,true,true,true,true,true,true);
    	
    	Zutat kartoffeln = new Zutat("Kartoffeln", "Nachtschattengewaechse");
    	Bestand kartoffelBestand = new BestandFactory().createBestand(kartoffeln, 1, 2, "kg");
    	kartoffeln.setBestand(kartoffelBestand);
    	
    	Zutat tomaten = new Zutat("Tomaten", "Nachtschattengewaechse",ganzesjahr);
    	Bestand tomatenBestand = new BestandFactory().createBestand(tomaten, 30, 25, "kg");
    	tomaten.setBestand(tomatenBestand);
    	
    	Zutat karotten = new Zutat("Karotten", "Gemuese",ganzesjahr);
    	Bestand karottenBestand = new BestandFactory().createBestand(karotten, 400, 100, "kg");
    	karotten.setBestand(karottenBestand);
    	
    	Zutat rindersteak = new Zutat("Rindersteak", "Nachtschattengewaechse",ganzesjahr);
    	Bestand rindersteakBestand = new BestandFactory().createBestand(rindersteak, 300, 80, "kg");
    	rindersteak.setBestand(rindersteakBestand);
    	
    	Zutat riesling = new Zutat("Riesling", "Weisswein",ganzesjahr);
    	Bestand rieslingBestand = new BestandFactory().createBestand(riesling, 70, 50, "flaschen");
    	riesling.setBestand(rieslingBestand);
    	
    	zutatRepository.save(kartoffeln);
    	zutatRepository.save(tomaten);
    	zutatRepository.save(karotten);
    	zutatRepository.save(rindersteak);
    	zutatRepository.save(riesling);
    	
       	System.out.println(mitarbeiterRepository.findByVorname("Max"));
    		
    }
    
    

}
