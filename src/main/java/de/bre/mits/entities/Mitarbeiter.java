package de.bre.mits.entities;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Mitarbeiter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mitarbeiter_id;
    private String vorname;
    private String name;
    private String benutzername;
    private Date geburtsDatum;
    
    @ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    private Set<Privileg> nutzerprivilegien = new HashSet<Privileg>();



    
    protected Mitarbeiter() {
    	
    }
    
    
    public Mitarbeiter(String vorname, String name, String benutzername, Date geburtsDatum) {
		super();
		this.vorname = vorname;
		this.name = name;
		this.benutzername = benutzername;
		this.geburtsDatum = geburtsDatum;
    }
    
    public Mitarbeiter(String vorname, String name, String benutzername, Date geburtsDatum,
			Set<Privileg> nutzerprivilegien) {
		super();
		this.vorname = vorname;
		this.name = name;
		this.benutzername = benutzername;
		this.geburtsDatum = geburtsDatum;
		this.nutzerprivilegien = nutzerprivilegien;
	}


	public long getId() {
		return mitarbeiter_id;
	}

	public void setId(long id) {
		this.mitarbeiter_id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public Date getGeburtsDatum() {
		return geburtsDatum;
	}

	public void setGeburtsDatum(Date geburtsDatum) {
		this.geburtsDatum = geburtsDatum;
	}

	public Set<Privileg> getNutzerprivilegien() {
        return Collections.unmodifiableSet(nutzerprivilegien);
    }

    public void addNutzerprivilegien(Privileg p) {
        if (!nutzerprivilegien.contains(p)) {
            nutzerprivilegien.add(p);
            p.addPrivilegienInhaber(this);
        }
    }

    public void removeNutzerPrivilegien (Privileg p) {
        if (nutzerprivilegien.contains(p)) {
            nutzerprivilegien.remove(p);
            p.removePrivilegienInhaber(this);
        }
    }
    
    
    public String toString(){
    	return vorname + " "+ name;
    }
}
