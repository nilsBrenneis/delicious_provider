package de.bre.mits.entities;

import de.bre.mits.repositories.MitarbeiterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

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
    private String passwortHash;

	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private Set<Privileg> nutzerprivilegien = new HashSet<Privileg>();

    private int loginTries;
    private static final int MAX_LOGIN_TRIES = 3;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    protected Mitarbeiter() {
    	
    }
    
    
    public Mitarbeiter(String vorname, String name, String benutzername, Date geburtsDatum, String passwortHash) {
		super();
		this.vorname = vorname;
		this.name = name;
		this.benutzername = benutzername;
		this.geburtsDatum = geburtsDatum;
		this.passwortHash = passwordEncoder().encode(passwortHash);
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

    public String getPasswortHash() {
        return passwortHash;
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

    public boolean isPasswordOK(Mitarbeiter m, MitarbeiterRepository mitarbeiterRepository, String benutzername, String givenPassword) {
        if (loginTries < MAX_LOGIN_TRIES && BCrypt.checkpw(givenPassword, passwortHash)) {
            return true;
        } else {
            loginTries++;
            mitarbeiterRepository.save(m);
            return false;
        }
    }
    
    public String toString(){
    	return vorname + " "+ name;
    }
}
