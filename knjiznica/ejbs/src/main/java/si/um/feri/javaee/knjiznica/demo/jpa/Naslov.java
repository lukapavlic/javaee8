package si.um.feri.javaee.knjiznica.demo.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Razred je uporabljen pri naprednejših JPA demonstracijah
 */
@Entity
public class Naslov implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
    private int id;
    private String ulica;
    private String mesto;
    private String drzava;
    private String zip;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }    
   
    public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String toString() {
        return "Address id: " + getId() + 
               ", street: " + getUlica() +
               ", city: " + getMesto() +
               ", state: " + getDrzava() +
               ", zip: " + getZip();
    }
}
