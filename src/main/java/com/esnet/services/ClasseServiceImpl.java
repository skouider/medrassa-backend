package com.esnet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Classe;
import com.esnet.beans.ConfigurationInscription;
import com.esnet.beans.Genre;
import com.esnet.beans.Inscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.StatutInscription;
import com.esnet.beans.TypeClasse;
import com.esnet.repository.ClasseRepository;

@Service
public class ClasseServiceImpl implements ClasseService{

	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private InscriptionService inscriptionService;

	@Override
	@Transactional
	public Classe save(Classe classe) {
//		1. vérifier que la session existe
		
		SessionOuverture sessionOuverture = sessionService.
				findById(classe.getSession().getId());
//		2. vérifier que le nom n'existe pas dans la même session
		
		List<Classe> classes = findBySession(sessionOuverture.getId());
		
		for (Classe classe2 : classes) {

		    if(classe.getNom().equalsIgnoreCase(classe2.getNom())) {

		        throw new RuntimeException(
		            "Cette classe existe déjà dans cette session");
		    }
		}
//		3. vérifier :

//		   ageMin < ageMax
		if(classe.getAgeMin() >= classe.getAgeMax()) {
			throw new RuntimeException("ageMin est superieur ageMax");
		}
//		   capacité > 0
		else if(classe.getCapacite() <= 0) {
			throw new RuntimeException("capacité < 0");
		}
		
//        List<ConfigurationInscription> configs = sessionOuverture.getConfigurations();
//        
//        for (ConfigurationInscription configurationInscription : configs) {
//			if(classe.getTypeClasse() != configurationInscription.getTypeClasse()) {
//				throw new RuntimeException
//				("le type de classe "+classe.getTypeClasse()+ " est incorrecte");
//			}
//		}
		List<ConfigurationInscription> configs = sessionOuverture.getConfigurations();
	    boolean typeAutorise = false;

	    for (ConfigurationInscription config : configs) {
	        if (config.getTypeClasse() == classe.getTypeClasse()) {
	            typeAutorise = true;
	            break;
	        }
	    }

	    if (!typeAutorise) {
	        throw new RuntimeException("Le type " + classe.getTypeClasse() + " n'est pas autorisé pour cette session");
	    }
//		4. enregistrer
		return classeRepository.save(classe);
	}

	@Override
	@Transactional
	public Classe update(Classe classe, Long id) {
		Classe existingClasse = findById(id);
		existingClasse.setAgeMax(classe.getAgeMax());
		existingClasse.setAgeMin(classe.getAgeMin());
		existingClasse.setCapacite(classe.getCapacite());
		existingClasse.setGenre(classe.getGenre());
		existingClasse.setNom(classe.getNom());
		existingClasse.setSession(classe.getSession());
		existingClasse.setTypeClasse(classe.getTypeClasse());		
		return classeRepository.save(existingClasse);
	}

	@Override
	@Transactional
	public int deleteById(Long id) {
		if(!classeRepository.existsById(id)) {
		    return -1;
		}
		if(!inscriptionService.findByClasse(id).isEmpty()) {

		    throw new RuntimeException(
		        "Impossible de supprimer une classe contenant des inscriptions");
		}
		
		classeRepository.deleteById(id);

		return 1;
	}

	@Override
	public Classe findById(Long id) {
		
		return classeRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Classe> findAll() {
		
		return classeRepository.findAll();
	}

	@Override
	public List<Classe> findBySession(Long sessionId) {
		if(sessionService.findById(sessionId) == null) {
			throw new RuntimeException("cette session n'existe pas");
		}
		return classeRepository.findBySessionId(sessionId);
	}

	@Override
	public List<Classe> findClassesCoran() {
		
		return classeRepository.findByTypeClasse(TypeClasse.CORAN);
	}

	@Override
	public List<Classe> findClassesPreparatoire() {
		
		return classeRepository.findByTypeClasse(TypeClasse.PREPARATOIRE);
	}

	@Override
	public long nombrePlacesDisponibles(Long classeId) {
//		1. récupérer la classe
		
		Classe classe = findById(classeId);
//		2. compter les inscriptions VALIDEE
		List<Inscription> inscriptions = inscriptionService.findByClasse(classeId);
		
		int nbValidee = 0;
		for (Inscription inscription : inscriptions) {
			if(inscription.getStatut() == StatutInscription.VALIDEE) {
				nbValidee ++;
			}
		}
//		3. retourner
        
//		capacite - nombreValide
           		
		return classe.getCapacite() - nbValidee;
	}

	@Override
	public List<Classe> findByTypeClasseAndGenre(TypeClasse typeClasse, Genre genre) {
		
		return classeRepository.findByTypeClasseAndGenre(typeClasse, genre);
	}

	@Override
	public List<Classe> findByTypeClasseAndSession(TypeClasse typeClasse, Long sessionId) {

	    findBySession(sessionId); // Vérifie que la session existe

	    List<Classe> classes =
	            classeRepository.findByTypeClasseAndSessionId(typeClasse, sessionId);

	    if (classes.isEmpty()) {
	        return classes;
	    }

	    return classes;
	}

	@Override
	public List<Classe> findByGenreAndSessionId(Genre genre, Long sessionId) {
		
		findBySession(sessionId); // Vérifie que la session existe
		
		List<Classe> classes = classeRepository.findByGenreAndSessionId(genre, sessionId);
		
		if (classes.isEmpty()) {
	        return classes;
	    }

	    return classes;

	}
// recuperer la session avec id , type et genre
	@Override
	public List<Classe> findByTypeClasseAndGenreAndSession(
	        TypeClasse typeClasse,
	        Genre genre,
	        Long sessionId) {

	    return classeRepository.findByTypeClasseAndGenreAndSessionId(
	            typeClasse,
	            genre,
	            sessionId);
	}

	
	@Override
	public List<Classe> findBySessionActive() {

	    List<Classe> classes = classeRepository.findBySessionActiveTrue();

	    if (classes.isEmpty()) {
	        throw new RuntimeException("Aucune classe disponible pour la session active.");
	    }

	    return classes;
	}

	@Override
	@Transactional
	public int deleteBySessionId(Long id) {
	     classeRepository.deleteBySessionId(id);
		return 1;
	}

		
	
}
