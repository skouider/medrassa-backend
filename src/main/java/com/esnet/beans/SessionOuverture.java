package com.esnet.beans;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SessionOuverture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private LocalDate dateOuverture;

    private LocalDate dateFermeture;

    private Boolean active;

    @OneToMany(mappedBy = "session")
    private List<Classe> classes;
    
    @OneToMany(mappedBy = "sessionOuverture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConfigurationInscription> configurations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public LocalDate getDateFermeture() {
		return dateFermeture;
	}

	public void setDateFermeture(LocalDate dateFermeture) {
		this.dateFermeture = dateFermeture;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public List<ConfigurationInscription> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<ConfigurationInscription> configurations) {
		this.configurations = configurations;
	}
    
    
    
}
