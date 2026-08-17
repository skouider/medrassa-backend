package com.esnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;

@Repository
public interface SessionOuvertureRepository extends JpaRepository<SessionOuverture, Long>{

	
	    SessionOuverture findByLibelle(String libelle);

	    List<SessionOuverture> findByActiveTrue();

	    SessionOuverture findByActive(Boolean active);
	    
	    boolean existsByLibelle(String libelle);
	    
	    
	    
	    
}
