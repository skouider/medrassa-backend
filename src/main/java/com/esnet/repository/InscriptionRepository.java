package com.esnet.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Genre;
import com.esnet.beans.Inscription;
import com.esnet.beans.StatutInscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

	Inscription findByReference(String reference);
	
	 List<Inscription> findByNom(String nom);
	 
	 List<Inscription> findByPrenom(String prenom);
	 
	 List<Inscription> findByDateNaissance(LocalDate dateNaissance);
	 
	 List<Inscription> findByNomAndPrenom(String nom, String prenom);

	List<Inscription> findByStatut(StatutInscription statut);

	List<Inscription> findByClasseId(Long classeId);

	List<Inscription> findByTelephone(String telephone);

	long countByClasseId(Long classeId);

	long countByStatut(StatutInscription statut);
	
	long countByGenre(Genre genre);
	
	boolean existsByNomIgnoreCaseAndPrenomIgnoreCaseAndDateNaissanceAndClasseSessionId(
	        String nom,
	        String prenom,
	        LocalDate dateNaissance,
	        Long sessionId);
	@Query("""
			SELECT i
			FROM Inscription i
			WHERE
			LOWER(i.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(i.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(i.reference) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
			List<Inscription> rechercher(@Param("keyword") String keyword);
}
