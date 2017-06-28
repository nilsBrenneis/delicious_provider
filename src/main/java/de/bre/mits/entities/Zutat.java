package de.bre.mits.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Zutat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zutat_id;
    private String bezeichnung;
    @OneToOne(cascade=CascadeType.ALL)
    private Bestand bestand;
    private String zutatenkategorie;
    @Embedded
    @AttributeOverrides({
    	@AttributeOverride(name="januar", column=@Column(nullable=true)),
    	@AttributeOverride(name="februar", column=@Column(nullable=true)),
    	@AttributeOverride(name="maerz", column=@Column(nullable=true)),
    	@AttributeOverride(name="april", column=@Column(nullable=true)),
    	@AttributeOverride(name="mai", column=@Column(nullable=true)),
    	@AttributeOverride(name="juni", column=@Column(nullable=true)),
    	@AttributeOverride(name="juli", column=@Column(nullable=true)),
    	@AttributeOverride(name="august", column=@Column(nullable=true)),
    	@AttributeOverride(name="september", column=@Column(nullable=true)),
    	@AttributeOverride(name="oktober", column=@Column(nullable=true)),
    	@AttributeOverride(name="november", column=@Column(nullable=true)),
    	@AttributeOverride(name="dezember", column=@Column(nullable=true)),
    })
    private Saisonverfuegbarkeit saisonverfuegbarkeit;
    
    protected Zutat() {
    	
    }
    
    
	public Zutat(String bezeichnung, Bestand bestand) {
		this.bezeichnung = bezeichnung;
		this.bestand = bestand;
	}

	public Zutat(String bezeichnung,String kategorie) {
		this.bezeichnung = bezeichnung;
		this.zutatenkategorie=kategorie;
	}
		
	public Zutat(String bezeichnung,String kategorie, Bestand bestand) {
		this.bezeichnung = bezeichnung;
		this.bestand = bestand;
		this.zutatenkategorie=kategorie;
	}
	
	public Zutat(String bezeichnung,String kategorie, Saisonverfuegbarkeit saisonverfuegbarkeit) {
		this.bezeichnung = bezeichnung;
		this.saisonverfuegbarkeit = saisonverfuegbarkeit;
		this.zutatenkategorie=kategorie;
	}
	
	public Zutat(String bezeichnung,String kategorie, Saisonverfuegbarkeit saisonverfuegbarkeit, Bestand bestand) {
		this.bezeichnung = bezeichnung;
		this.bestand = bestand;
		this.zutatenkategorie=kategorie;
		this.saisonverfuegbarkeit = saisonverfuegbarkeit;
	}
	
	
	public long getId() {
		return zutat_id;
	}
	public void setId(long id) {
		this.zutat_id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public Bestand getBestand() {
		return bestand;
	}
	public void setBestand(Bestand bestand) {
		this.bestand = bestand;
	}
	public String getZutatenkategorie() {
		return zutatenkategorie;
	}
	public void setZutatenkategorie(String zutatenkategorie) {
		this.zutatenkategorie = zutatenkategorie;
	}
	
	public void setSaisonverfuegbarkeit(Saisonverfuegbarkeit saisonverfuegbarkeit){
		this.saisonverfuegbarkeit = saisonverfuegbarkeit;
	}

	public Saisonverfuegbarkeit getSaisonverfuegbarkeit(){
		return saisonverfuegbarkeit;
	}
	
	
}
