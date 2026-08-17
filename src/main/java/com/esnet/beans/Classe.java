package com.esnet.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Integer ageMin;

    private Integer ageMax;

    private Integer capacite;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private TypeClasse typeClasse;

    @ManyToOne
    @JsonIgnore
    private SessionOuverture session;

    @OneToMany(mappedBy = "classe")
    private List<Inscription> inscriptions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public TypeClasse getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(TypeClasse typeClasse) {
		this.typeClasse = typeClasse;
	}

	public SessionOuverture getSession() {
		return session;
	}

	public void setSession(SessionOuverture session) {
		this.session = session;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
	
	
    
    
    @Override
	public String toString() {
		return "Classe [id=" + id + ", nom=" + nom + ", ageMin=" + ageMin + ", ageMax=" + ageMax + ", capacite="
				+ capacite + ", genre=" + genre + ", typeClasse=" + typeClasse + ", session=" + session
				+ ", inscriptions=" + inscriptions + "]";
	}

	public Classe() {
		// TODO Auto-generated constructor stub
	}
}