package com.esnet.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ConfigurationInscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeClasse typeClasse;
    
    @ManyToOne
    private SessionOuverture sessionOuverture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeClasse getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(TypeClasse typeClasse) {
		this.typeClasse = typeClasse;
	}

	
	
	public SessionOuverture getSessionOuverture() {
		return sessionOuverture;
	}

	
	public void setSessionOuverture(SessionOuverture sessionOuverture) {
		this.sessionOuverture = sessionOuverture;
	}
    
    

}