package com.esnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Classe;
import com.esnet.beans.Genre;
import com.esnet.beans.TypeClasse;

@Repository
public interface ClasseRepository
        extends JpaRepository<Classe, Long> {

    Classe findByNom(String nom);

    List<Classe> findByTypeClasse(TypeClasse typeClasse);

    List<Classe> findByGenre(Genre genre);

    List<Classe> findBySessionId(Long sessionId);

    List<Classe> findByTypeClasseAndGenre(TypeClasse typeClasse, Genre genre);

    List<Classe> findByTypeClasseAndSessionId(TypeClasse typeClasse, Long sessionId);

    List<Classe> findByGenreAndSessionId(Genre genre, Long sessionId);

    @Query("SELECT c FROM Classe c WHERE c.typeClasse = :typeClasse " +
            "AND (c.genre = :genre OR c.genre = com.esnet.beans.Genre.MIXTE) " +
            "AND c.session.id = :sessionId")
     List<Classe> findByTypeClasseAndGenreAndSessionId(
         @Param("typeClasse") TypeClasse typeClasse,
         @Param("genre") Genre genre,
         @Param("sessionId") Long sessionId
     );
//    List<Classe> findByTypeClasseAndGenreAndSessionId(TypeClasse typeClasse,Genre genre,Long sessionId);

    List<Classe> findBySessionActiveTrue();
    
    int deleteBySessionId(Long id);
}
