package com.esnet.services;

import java.util.List;

import com.esnet.beans.Classe;
import com.esnet.beans.Genre;
import com.esnet.beans.TypeClasse;

public interface ClasseService {

    Classe save(Classe classe);

    Classe update(Classe classe, Long id);

    int deleteById(Long id);

    Classe findById(Long id);

    List<Classe> findAll();

    List<Classe> findBySession(Long sessionId);

    List<Classe> findClassesCoran();

    List<Classe> findClassesPreparatoire();
    
    List<Classe> findByTypeClasseAndGenre(TypeClasse typeClasse, Genre genre);

    long nombrePlacesDisponibles(Long classeId);
    
    List<Classe> findByTypeClasseAndSession(TypeClasse typeClasse, Long sessionId);
    
    List<Classe> findByGenreAndSessionId(Genre genre, Long sessionId);
    
    List<Classe> findByTypeClasseAndGenreAndSession(TypeClasse typeClasse,Genre genre,
            Long sessionId);
    
    List<Classe> findBySessionActive();

    int deleteBySessionId(Long id);
}
