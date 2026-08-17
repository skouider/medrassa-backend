package com.esnet.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.esnet.beans.Inscription;

public interface InscriptionService {

	Inscription saveByAdmin(Inscription inscription, Long sessionId);
	
    Inscription save(Inscription inscription);

    Inscription update(Inscription inscription);

    int deleteById(Long id);

    Inscription findByReference(String reference);
    
    Inscription findById(Long id);

    List<Inscription> findAll();

    List<Inscription> findByClasse(Long classeId);

    List<Inscription> findEnAttente();

    List<Inscription> findValidees();

    Inscription valider(Long id);

    Inscription refuser(Long id);

    Inscription annuler(Long id);

    String genererReference();
    
    Page<Inscription> findAll(Pageable pageable);
    
    List<Inscription> rechercher(String keyword);
}
