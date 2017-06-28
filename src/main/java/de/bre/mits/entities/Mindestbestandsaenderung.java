package de.bre.mits.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mindestbestandsaenderung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mindestbestandsaenderung_id;
    private Date datum;
    private double mindestbestand;
    @ManyToOne
    private Bestand	bestand;
    
    
    
    protected Mindestbestandsaenderung() {
    	
    }
    
    
	public Mindestbestandsaenderung(Date datum, double mindestbestand,Bestand bestand) {
		super();
		this.datum = datum;
		this.mindestbestand = mindestbestand;
		this.bestand = bestand;
	}


	public long getId() {
		return mindestbestandsaenderung_id;
	}
	public void setId(long id) {
		this.mindestbestandsaenderung_id = id;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getMindestbestand() {
		return mindestbestand;
	}
	public void setMindestbestand(double mindestbestand) {
		this.mindestbestand = mindestbestand;
	}
	public Bestand getBestand() {
		return bestand;
	}
	public void setBestand(Bestand bestand) {
		this.bestand = bestand;
	}
}
