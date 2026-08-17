package com.esnet.dto;

import java.time.LocalDate;
import com.esnet.beans.Genre;
import com.esnet.beans.TypeClasse;

// POST /inscriptions — ce que le parent envoie
public class InscriptionRequestDTO {

    private String     nom;
    private String     prenom;
    private LocalDate  dateNaissance;
    private String     telephone;
    private String     adresse;
    private String     nomTuteur;
    private TypeClasse typeClasse;
    private Genre      genre;
    // reference       → généré dans genererReference()
    // dateInscription → LocalDate.now() dans le service
    // statut          → calculé dans save()
    // classe          → assignée automatiquement dans save()

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate d) { this.dateNaissance = d; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getNomTuteur() { return nomTuteur; }
    public void setNomTuteur(String nomTuteur) { this.nomTuteur = nomTuteur; }

    public TypeClasse getTypeClasse() { return typeClasse; }
    public void setTypeClasse(TypeClasse t) { this.typeClasse = t; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }
}