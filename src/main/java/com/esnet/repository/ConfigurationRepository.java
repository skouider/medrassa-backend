package com.esnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esnet.beans.Classe;
import com.esnet.beans.ConfigurationInscription;
import com.esnet.beans.Genre;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;

public interface ConfigurationRepository extends JpaRepository<ConfigurationInscription, Long>{

	boolean existsBySessionOuvertureAndTypeClasse
	( SessionOuverture session, TypeClasse typeClasse);
	
	void deleteBySessionOuverture(SessionOuverture session);
	
	List<ConfigurationInscription> findBySessionOuverture(SessionOuverture session);
	
	List<ConfigurationInscription> findBySessionOuvertureId(Long id);
	
	
}
