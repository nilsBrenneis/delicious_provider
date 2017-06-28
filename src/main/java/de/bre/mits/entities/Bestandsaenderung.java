package de.bre.mits.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bestandsaenderung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestandsaenderung_id;
    private Date datum;
    private double mengenaenderung;
    @ManyToOne
    Bestand bestand;
    
    protected Bestandsaenderung() {
    	
    }
    
    
	public Bestandsaenderung( Date datum, double mengenaenderung, Bestand bestand) {
		this.datum = datum;
		this.mengenaenderung = mengenaenderung;
		this.bestand=bestand;
		
	}


	public long getId() {
		return bestandsaenderung_id;
	}
	public void setId(long id) {
		this.bestandsaenderung_id = id;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getMengenaenderung() {
		return mengenaenderung;
	}
	public void setMengenaenderung(double mengenaenderung) {
		this.mengenaenderung = mengenaenderung;
	}
	
	public Bestand getBestand(){
		return bestand;
	}
	
	public void setBestand(Bestand bestand){
		this.bestand= bestand;
	}
	
}
