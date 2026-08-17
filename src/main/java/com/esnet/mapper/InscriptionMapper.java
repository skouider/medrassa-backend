package com.esnet.mapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esnet.beans.Classe;
import com.esnet.beans.Inscription;
import com.esnet.dto.InscriptionAdminRequestDTO;
import com.esnet.dto.InscriptionDTO;
import com.esnet.dto.InscriptionRequestDTO;

@Component
public class InscriptionMapper {

    @Autowired
    private ClasseMapper classeMapper;

    public InscriptionDTO toDTO(Inscription inscription) {
        if (inscription == null) return null;

        InscriptionDTO dto = new InscriptionDTO();
        dto.setId(inscription.getId());
        dto.setReference(inscription.getReference());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setNom(inscription.getNom());
        dto.setPrenom(inscription.getPrenom());
        dto.setDateNaissance(inscription.getDateNaissance());
        dto.setTelephone(inscription.getTelephone());
        dto.setAdresse(inscription.getAdresse());
        dto.setNomTuteur(inscription.getNomTuteur());
        dto.setTypeClasse(inscription.getTypeClasse());
        dto.setGenre(inscription.getGenre());
        dto.setStatut(inscription.getStatut());

        // Age calculé
        if (inscription.getDateNaissance() != null) {
            dto.setAge(Period.between(
                inscription.getDateNaissance(),
                LocalDate.now()
            ).getYears());
        }

        // ClasseDTO injecté — null si LISTE_ATTENTE
        dto.setClasse(classeMapper.toDTO(inscription.getClasse()));

        return dto;
    }

    // InscriptionRequestDTO → Inscription
    public Inscription requestToEntity(InscriptionRequestDTO dto) {
        if (dto == null) return null;

        Inscription inscription = new Inscription();
        inscription.setNom(dto.getNom());
        inscription.setPrenom(dto.getPrenom());
        inscription.setDateNaissance(dto.getDateNaissance());
        inscription.setTelephone(dto.getTelephone());
        inscription.setAdresse(dto.getAdresse());
        inscription.setNomTuteur(dto.getNomTuteur());
        inscription.setTypeClasse(dto.getTypeClasse());
        inscription.setGenre(dto.getGenre());
        inscription.setDateInscription(LocalDate.now());
        // reference  → genererReference() dans le service
        // statut     → calculé dans save()
        // classe     → assignée dans save()
        return inscription;
    }

    public List<InscriptionDTO> toDTOList(List<Inscription> inscriptions) {
        if (inscriptions == null || inscriptions.isEmpty()) return null;

        List<InscriptionDTO> dtos = new ArrayList<>();
        for (Inscription i : inscriptions) {
            dtos.add(toDTO(i));
        }
        return dtos;
    }
    
    public Inscription adminRequestToEntity(InscriptionAdminRequestDTO dto){

        Inscription inscription = new Inscription();

        inscription.setNom(dto.getNom());
        inscription.setPrenom(dto.getPrenom());
        inscription.setDateNaissance(dto.getDateNaissance());
        inscription.setTelephone(dto.getTelephone());
        inscription.setAdresse(dto.getAdresse());
        inscription.setNomTuteur(dto.getNomTuteur());
        inscription.setGenre(dto.getGenre());

        Classe classe = new Classe();
        classe.setId(dto.getClasseId());

        inscription.setClasse(classe);

        return inscription;
    }
}