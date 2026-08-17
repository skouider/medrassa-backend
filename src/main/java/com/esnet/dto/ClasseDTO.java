package com.esnet.dto;

import com.esnet.beans.Genre;
import com.esnet.beans.TypeClasse;

public class ClasseDTO {

    private Long       id;
    private String     nom;
    private Integer    ageMin;
    private Integer    ageMax;
    private Integer    capacite;
    private Genre      genre;
    private TypeClasse typeClasse;

    // SessionOuvertureDTO injecté — pas tout l'objet SessionOuverture
    // sinon on expose les classes de la session → boucle infinie
    private SessionOuvertureDTO session;

    // Champs calculés depuis inscriptions
    private int     nombreInscrits;     // inscriptions VALIDEE
    private long    placesDisponibles;  // capacite - nombreInscrits
    private boolean complet;            // placesDisponibles == 0

    // inscriptions → ABSENT — endpoint séparé GET /inscriptions/classe/{id}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getAgeMin() { return ageMin; }
    public void setAgeMin(Integer ageMin) { this.ageMin = ageMin; }

    public Integer getAgeMax() { return ageMax; }
    public void setAgeMax(Integer ageMax) { this.ageMax = ageMax; }

    public Integer getCapacite() { return capacite; }
    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public TypeClasse getTypeClasse() { return typeClasse; }
    public void setTypeClasse(TypeClasse t) { this.typeClasse = t; }

    public SessionOuvertureDTO getSession() { return session; }
    public void setSession(SessionOuvertureDTO session) { this.session = session; }

    public int getNombreInscrits() { return nombreInscrits; }
    public void setNombreInscrits(int n) { this.nombreInscrits = n; }

    public long getPlacesDisponibles() { return placesDisponibles; }
    public void setPlacesDisponibles(long p) { this.placesDisponibles = p; }

    public boolean isComplet() { return complet; }
    public void setComplet(boolean complet) { this.complet = complet; }
}