package com.esnet.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.esnet.beans.Classe;
import com.esnet.beans.Inscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.StatutInscription;
import com.esnet.dto.ClasseDTO;

@Component
public class ClasseMapper {

    @Autowired
    private SessionOuvertureMapper sessionMapper;

    public ClasseDTO toDTO(Classe classe) {
        if (classe == null) return null;

        ClasseDTO dto = new ClasseDTO();
        dto.setId(classe.getId());
        dto.setNom(classe.getNom());
        dto.setAgeMin(classe.getAgeMin());
        dto.setAgeMax(classe.getAgeMax());
        dto.setCapacite(classe.getCapacite());
        dto.setGenre(classe.getGenre());
        dto.setTypeClasse(classe.getTypeClasse());

        // SessionOuvertureDTO injecté
        dto.setSession(sessionMapper.toDTO(classe.getSession()));

        // Champs calculés depuis inscriptions
        // Classe n'a pas @JsonIgnore sur inscriptions
        // → le DTO protège contre la boucle infinie
        if (classe.getInscriptions() != null) {
            int nombreInscrits = 0;
            for (Inscription i : classe.getInscriptions()) {
                if (i.getStatut() == StatutInscription.VALIDEE) {
                    nombreInscrits++;
                }
            }
            dto.setNombreInscrits(nombreInscrits);
            long places = classe.getCapacite() - nombreInscrits;
            dto.setPlacesDisponibles(places);
            dto.setComplet(places == 0);
        } else {
            dto.setNombreInscrits(0);
            dto.setPlacesDisponibles(classe.getCapacite() != null
                ? classe.getCapacite() : 0);
            dto.setComplet(false);
        }

        return dto;
    }

    public Classe toEntity(ClasseDTO dto) {
        if (dto == null) return null;

        Classe classe = new Classe();
        classe.setId(dto.getId());
        classe.setNom(dto.getNom());
        classe.setAgeMin(dto.getAgeMin());
        classe.setAgeMax(dto.getAgeMax());
        classe.setCapacite(dto.getCapacite());
        classe.setGenre(dto.getGenre());
        classe.setTypeClasse(dto.getTypeClasse());
        // session → résolue dans le service via session.id
        if (dto.getSession() != null) {
            SessionOuverture s = new SessionOuverture();
            s.setId(dto.getSession().getId());
            classe.setSession(s);
        }
        return classe;
    }

    public List<ClasseDTO> toDTOList(List<Classe> classes) {
        if (classes == null || classes.isEmpty()) return null;

        List<ClasseDTO> dtos = new ArrayList<>();
        for (Classe c : classes) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }
}