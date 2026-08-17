package com.esnet.services;

import java.util.List;

import com.esnet.beans.Admin;
import com.esnet.beans.Classe;
import com.esnet.beans.Inscription;
import com.esnet.beans.SessionOuverture;

public interface AdminService {
	
	
	// ===== ADMIN =====

	    Admin save(Admin admin);

	    Admin update(Admin admin);

	    int deleteById(Long id);

	    Admin findByUsername(String username);

	    List<Admin> findAll();
	
	

    // ===== SESSION =====
    SessionOuverture ouvrirSession(Long sessionId);

    SessionOuverture fermerSession(Long sessionId);

    List<SessionOuverture> findAllSessions();


    // ===== CLASSES =====
    Classe creerClasse(Classe classe);

    Classe modifierClasse(Classe classe, Long id);

    int supprimerClasse(Long id);

    List<Classe> findAllClasses();

    List<Classe> findClassesCoran();

    List<Classe> findClassesPreparatoire();

    long nombrePlacesDisponibles(Long classeId);


    // ===== INSCRIPTIONS =====
    List<Inscription> findAllInscriptions();

    List<Inscription> findValidees();

    List<Inscription> findEnAttente();

    List<Inscription> findListeAttente();

    Inscription valider(Long inscriptionId);

    Inscription refuser(Long inscriptionId);

    Inscription annuler(Long inscriptionId);


    // ===== RECHERCHE =====
    Inscription findByReference(String reference);

    List<Inscription> findByClasse(Long classeId);


    // ===== STATISTIQUES =====
    long nombreTotalInscriptions();

    long nombreValidees();

    long nombreListeAttente();

    long nombreRefusees();

    long nombreAnnulees();

    long nombreInscriptionsParClasse(Long classeId);

    long nombreGarcons();

    long nombreFilles();

    long nombreCoran();

    long nombrePreparatoire();


    // ===== LISTE D'ATTENTE =====
    List<Inscription> getListeAttente();

    Inscription validerPremierDeLaListe(Long classeId);

    List<Inscription> getListeAttenteParClasse(Long classeId);


    // ===== EXPORT =====
    List<Inscription> exporterClasse(Long classeId);

    List<Inscription> exporterValidees();

}
