package com.esnet.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Classe;
import com.esnet.beans.ConfigurationInscription;
import com.esnet.beans.Inscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.StatutInscription;
import com.esnet.repository.ConfigurationRepository;
import com.esnet.repository.InscriptionRepository;

@Service
public class InscriptionServiceImpl implements InscriptionService {

	@Autowired
	private InscriptionRepository inscriptionRepository;
	@Autowired
	private SessionService sessionService;
	@Autowired
	@Lazy
	private ClasseService classeService;
	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	@Transactional
	public Inscription save(Inscription inscription) {

		// 1. Vérifier qu'une session est ouverte
		SessionOuverture session = sessionService.getSessionActive();

		if (session == null) {
			throw new RuntimeException("Aucune session d'inscription n'est ouverte.");
		}

		// 2. Vérifier que le type est autorisé
		boolean autorise = configurationRepository.existsBySessionOuvertureAndTypeClasse(session,
				inscription.getTypeClasse());

		if (!autorise) {
			throw new RuntimeException("Les inscriptions " + inscription.getTypeClasse() + " ne sont pas ouvertes.");
		}

		// 3. Calcul de l'âge
		int age = Period.between(inscription.getDateNaissance(), LocalDate.now()).getYears();

		// 4. Chercher les classes de cette session
		List<Classe> classes = classeService.findByTypeClasseAndGenreAndSession(inscription.getTypeClasse(),
				inscription.getGenre(), session.getId());

		if (classes.isEmpty()) {
			throw new RuntimeException(
					"Aucune classe disponible pour " + inscription.getTypeClasse() + " / " + inscription.getGenre());
		}

		// verifier si le nom existe deja
		boolean existe = inscriptionRepository
				.existsByNomIgnoreCaseAndPrenomIgnoreCaseAndDateNaissanceAndClasseSessionId
				(inscription.getNom(),
						inscription.getPrenom(), inscription.getDateNaissance(), session.getId());

		if (existe) {
			throw new RuntimeException("Cet élève est déjà inscrit dans cette session.");
		}

		Classe classeChoisie = null;
		boolean ageValide = false;

		// 5. Recherche d'une classe compatible
		for (Classe classe : classes) {
		    System.out.println("================================");
		    System.out.println("ELEVE AGE = " + age);
		    System.out.println("CLASSE = " + classe.getNom());
		    System.out.println("AGE MIN = " + classe.getAgeMin());
		    System.out.println("AGE MAX = " + classe.getAgeMax());

			if (age >= classe.getAgeMin() && age <= classe.getAgeMax()) {

		        System.out.println(">>> AGE COMPATIBLE");

				ageValide = true;

				long places = classeService.nombrePlacesDisponibles(classe.getId());

				if (places > 0) {
					classeChoisie = classe;
					break;
				}
			}
		}

		// 6. Vérification de l'âge
		if (!ageValide) {
			throw new RuntimeException("Votre âge (" + age + " ans) ne correspond à aucune classe.");
		}

		// 7. Affectation
		if (classeChoisie != null) {

			inscription.setClasse(classeChoisie);
			inscription.setStatut(StatutInscription.VALIDEE);

		} else {

			// Toutes les classes sont pleines
			inscription.setStatut(StatutInscription.LISTE_ATTENTE);
		}

		// 8. Génération de la référence
		inscription.setReference(genererReference());

		return inscriptionRepository.save(inscription);
	}

	@Override
	@Transactional
	public Inscription update(Inscription inscription) {

		return null;
	}

	@Override
	@Transactional
	public int deleteById(Long id) {
		if (!inscriptionRepository.existsById(id)) {
			return -1;
		}
		inscriptionRepository.deleteById(id);
		return 1;
	}

	@Override
	public Inscription findByReference(String reference) {

		return inscriptionRepository.findByReference(reference);
	}

	@Override
	public List<Inscription> findAll() {

		return inscriptionRepository.findAll();
	}

	@Override
	public List<Inscription> findByClasse(Long classeId) {

		return inscriptionRepository.findByClasseId(classeId);
	}

	@Override
	public List<Inscription> findEnAttente() {

		return inscriptionRepository.findByStatut(StatutInscription.EN_ATTENTE);
	}

	@Override
	public List<Inscription> findValidees() {

		return inscriptionRepository.findByStatut(StatutInscription.VALIDEE);
	}

	@Override
	@Transactional
	public Inscription valider(Long id) {
//		1. rechercher inscription

		Inscription inscription = findById(id);
//		2. vérifier EN_ATTENTE
		if (inscription.getStatut() != StatutInscription.EN_ATTENTE) {
			throw new RuntimeException("le status n'est pas EN_ATTENTE");
		}
//		3. vérifier capacité restante
		long placesRestante = classeService.nombrePlacesDisponibles(inscription.getClasse().getId());
		if (placesRestante <= 0) {
			throw new RuntimeException("y'a plus de place dans cette classe");
		}
//		4. statut = VALIDEE
		inscription.setStatut(StatutInscription.VALIDEE);
//		5. sauvegarder
		return inscriptionRepository.save(inscription);
	}

	@Override
	@Transactional
	public Inscription refuser(Long id) {
//		1. rechercher inscription
		Inscription inscription = findById(id);
//		2. vérifier EN_ATTENTE
		if (inscription.getStatut() != StatutInscription.EN_ATTENTE) {
			throw new RuntimeException("le status n'est pas EN_ATTENTE");
		}
//		3. statut = REFUSEE
		inscription.setStatut(StatutInscription.REFUSEE);
//		4. sauvegarder
		return inscriptionRepository.save(inscription);
	}

	@Override
	@Transactional
	public Inscription annuler(Long id) {
//		1. rechercher inscription
		Inscription inscription = findById(id);

//		2. statut = ANNULEE
		inscription.setStatut(StatutInscription.ANNULEE);

//		3. sauvegarder
		return inscriptionRepository.save(inscription);
	}

	@Override
	public String genererReference() {
		String horodatage = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm"));

		String identifiantUnique = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
		String reference = "INS-" + horodatage + "-" + identifiantUnique;

		return reference;
	}

	@Override
	public Inscription findById(Long id) {

		return inscriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aucune Inscription trouvée avec l'ID : " + id));
	}

	@Override
	@Transactional
	public Inscription saveByAdmin(Inscription inscription, Long sessionId) {

		// 1. Vérifier la session
		SessionOuverture session = sessionService.findById(sessionId);

		if (session == null) {
			throw new RuntimeException("Cette session n'existe pas.");
		}

		// 2. Vérifier la classe
		System.out.println("get classe %%%%%%"+inscription.getClasse().getId());
		Classe classe = classeService.findById(inscription.getClasse().getId());

		// 3. Vérifier que la classe appartient à cette session
		if (!classe.getSession().getId().equals(sessionId)) {
			throw new RuntimeException("La classe sélectionnée n'appartient pas à cette session.");
		}

		inscription.setTypeClasse(classe.getTypeClasse());
		inscription.setGenre(classe.getGenre());
		inscription.setDateInscription(LocalDate.now());
		// verifier si le nom existe deja
				boolean existe = inscriptionRepository
						.existsByNomIgnoreCaseAndPrenomIgnoreCaseAndDateNaissanceAndClasseSessionId
						(inscription.getNom(),
								inscription.getPrenom(), inscription.getDateNaissance(), session.getId());

				if (existe) {
					throw new RuntimeException("Cet élève est déjà inscrit dans cette session.");
				}

		
		// 4. Vérifier que le type est autorisé
		boolean autorise = configurationRepository.existsBySessionOuvertureAndTypeClasse(session,
				classe.getTypeClasse());

		if (!autorise) {
			throw new RuntimeException("Le type " + classe.getTypeClasse() + " n'est pas autorisé dans cette session.");
		}

		// 5. Vérifier l'âge
		int age = Period.between(inscription.getDateNaissance(), LocalDate.now()).getYears();

		if (age < classe.getAgeMin() || age > classe.getAgeMax()) {
			throw new RuntimeException("L'âge ne correspond pas à cette classe.");
		}

		// 6. Vérifier les places disponibles
		long places = classeService.nombrePlacesDisponibles(classe.getId());

		if (places <= 0) {
			throw new RuntimeException("Cette classe est complète.");
		}

		// 7. Affectation
		inscription.setClasse(classe);
		inscription.setStatut(StatutInscription.VALIDEE);
		inscription.setReference(genererReference());

		return inscriptionRepository.save(inscription);
	}

	@Override
	public Page<Inscription> findAll(Pageable pageable) {
		
		return inscriptionRepository.findAll(pageable);
	}

	@Override
	public List<Inscription> rechercher(String keyword) {
		
		return inscriptionRepository.rechercher(keyword);
	}

	
}