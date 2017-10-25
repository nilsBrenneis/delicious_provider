package de.bre.mits.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bestand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestand_id;
    private double menge;
    private double mindestbestand;
    private Mengeneinheit mengeneinheit;
   	@OneToOne(mappedBy="bestand",fetch = FetchType.LAZY)
   	@JsonIgnore
   	private Zutat zutat;
    
    protected Bestand() {
    	
    }
    
	public Bestand(double menge, double mindestbestand) {
		this.menge = menge;
		this.mindestbestand = mindestbestand;
	}
	
	
	public Bestand(double menge, double mindestbestand, String mengeneinheit,Zutat zutat) {
		this.menge = menge;
		this.mindestbestand = mindestbestand;
		this.mengeneinheit=Mengeneinheit.valueOf(mengeneinheit.toLowerCase());
	}
	
	public enum Mengeneinheit {
		l, kg, ml, g, flaschen, stueck, tonne
	}


	public long getId() {
		return bestand_id;
	}
	public void setId(long id) {
		this.bestand_id = id;
	}
	public double getMenge() {
		return menge;
	}
	public void setMenge(double menge) {
		this.menge = menge;
	}
	public double getMindestbestand() {
		return mindestbestand;
	}
	public void setMindestbestand(double mindestbestand) {
		this.mindestbestand = mindestbestand;
	}
	public Mengeneinheit getMengeneinheit() {
		return mengeneinheit;
	}
	public void setMengeneinheit(Mengeneinheit mengeneinheit) {
		this.mengeneinheit = mengeneinheit;
	}
	
	public Zutat getZutat(){
		return zutat;
	}
	
	public void setZutat(Zutat zutat){
		this.zutat=zutat;
	}
}

