package com.esnet.dto;

import java.time.LocalDate;

import com.esnet.beans.Genre;

public class InscriptionAdminRequestDTO {

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String telephone;
    private String adresse;
    private String nomTuteur;
    private Genre genre;

    private Long classeId;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNomTuteur() {
		return nomTuteur;
	}

	public void setNomTuteur(String nomTuteur) {
		this.nomTuteur = nomTuteur;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Long getClasseId() {
		return classeId;
	}

	public void setClasseId(Long classeId) {
		this.classeId = classeId;
	}

    // getters setters
    
    
}
