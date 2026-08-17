package com.esnet.dto;

import java.time.LocalDate;
import java.util.List;

import com.esnet.beans.TypeClasse;

public class SessionOuvertureDTO {

    private Long      id;
    private String    libelle;
    private LocalDate dateOuverture;
    private LocalDate dateFermeture;
    private Boolean   active;

    // Champ calculé — depuis classes.size()
    private int nombreClasses;
    // classes → ABSENT — sans DTO les entités Classe
    // seraient sérialisées avec leurs inscriptions → boucle infinie
    private List<TypeClasse> configurations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public LocalDate getDateOuverture() { return dateOuverture; }
    public void setDateOuverture(LocalDate d) { this.dateOuverture = d; }

    public LocalDate getDateFermeture() { return dateFermeture; }
    public void setDateFermeture(LocalDate d) { this.dateFermeture = d; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public int getNombreClasses() { return nombreClasses; }
    public void setNombreClasses(int n) { this.nombreClasses = n; }
	public List<TypeClasse> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<TypeClasse> configurations) {
		this.configurations = configurations;
	}
	
	
}