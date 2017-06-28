package de.bre.mits.factories;

import java.util.Date;

import de.bre.mits.entities.Bestand;
import de.bre.mits.entities.Bestandsaenderung;
import de.bre.mits.entities.Mindestbestandsaenderung;
import de.bre.mits.entities.Zutat;

public class BestandFactory {

	public BestandFactory(){
		
	}
	
	public Bestand createBestand(Zutat zutat, double menge, double mindestbestand, String mengeneinheit){
		
		Bestand bestand = new Bestand(menge, mindestbestand,mengeneinheit,zutat);
		Bestandsaenderung bestandsaenderung = new Bestandsaenderung(new Date(), menge,bestand);
		Mindestbestandsaenderung mindestbestandsaenderung = new Mindestbestandsaenderung(new Date(), mindestbestand,bestand);
		
		
		return bestand;
	}
	
}

