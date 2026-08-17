package com.esnet.dto;

import java.time.LocalDate;
import com.esnet.beans.Genre;
import com.esnet.beans.StatutInscription;
import com.esnet.beans.TypeClasse;

public class InscriptionDTO {

    private Long              id;
    private String            reference;
    private LocalDate         dateInscription;
    private String            nom;
    private String            prenom;
    private LocalDate         dateNaissance;
    private int               age;           // calculé
    private String            telephone;
    private String            adresse;
    private String            nomTuteur;
    private TypeClasse        typeClasse;
    private Genre             genre;
    private StatutInscription statut;

    // ClasseDTO injecté — null si LISTE_ATTENTE
    private ClasseDTO classe;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate d) { this.dateInscription = d; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate d) { this.dateNaissance = d; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

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

    public StatutInscription getStatut() { return statut; }
    public void setStatut(StatutInscription statut) { this.statut = statut; }

    public ClasseDTO getClasse() { return classe; }
    public void setClasse(ClasseDTO classe) { this.classe = classe; }
}