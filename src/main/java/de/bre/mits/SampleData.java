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
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
    	createData();
    }
    public SampleData() {
	
    }
    
    public void createData(){
    	mitarbeiterRepository.save(new Mitarbeiter("Max","Mustermann","cook", new Date(),"abc123"));
    	mitarbeiterRepository.save(new Mitarbeiter("Anna","Mueller","manager", new Date(),"abc123"));
    	mitarbeiterRepository.save(new Mitarbeiter("Horst","Löffel","chef",new Date(),"abc123"));
    	

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
    	
    	Zutat rind = new Zutat("Rind", "Nachtschattengewaechse",ganzesjahr);
    	Bestand rindersteakBestand = new BestandFactory().createBestand(rind, 300, 80, "kg");
    	rind.setBestand(rindersteakBestand);
    	
    	Zutat riesling = new Zutat("Riesling", "Weisswein",ganzesjahr);
    	Bestand rieslingBestand = new BestandFactory().createBestand(riesling, 70, 50, "flaschen");
    	riesling.setBestand(rieslingBestand);

		Zutat spargel = new Zutat("Spargel", "Nachtschattengewaechse");
		Bestand spargelBestand = new BestandFactory().createBestand(spargel, 70, 10, "kg");
		spargel.setBestand(spargelBestand);

		Zutat huhn = new Zutat("Huhn", "Nachtschattengewaechse");
		Bestand huhnBestand = new BestandFactory().createBestand(huhn, 404, 100, "stueck");
		huhn.setBestand(huhnBestand);

		Zutat hamster = new Zutat("Hamster", "Nachtschattengewaechse");
		Bestand hamsterBestand = new BestandFactory().createBestand(hamster, 300, 500, "stueck");
		hamster.setBestand(hamsterBestand);

		Zutat hund = new Zutat("Hund", "Nachtschattengewaechse");
		Bestand hundBestand = new BestandFactory().createBestand(hund, 100, 200, "flaschen");
		hund.setBestand(hundBestand);
    	
    	zutatRepository.save(kartoffeln);
    	zutatRepository.save(tomaten);
    	zutatRepository.save(karotten);
    	zutatRepository.save(rind);
    	zutatRepository.save(riesling);
    	zutatRepository.save(spargel);
		zutatRepository.save(huhn);
		zutatRepository.save(hamster);
		zutatRepository.save(hund);
    }
    
    

}
