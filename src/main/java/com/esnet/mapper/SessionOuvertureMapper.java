package com.esnet.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esnet.beans.ConfigurationInscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;
import com.esnet.dto.SessionOuvertureDTO;

@Component
public class SessionOuvertureMapper {
	
	@Autowired
	private ConfigurationInscriptionMapper configurationMapper;

    public SessionOuvertureDTO toDTO(SessionOuverture session) {
        if (session == null) return null;

        SessionOuvertureDTO dto = new SessionOuvertureDTO();
        dto.setId(session.getId());
        dto.setLibelle(session.getLibelle());
        dto.setDateOuverture(session.getDateOuverture());
        dto.setDateFermeture(session.getDateFermeture());
        dto.setActive(session.getActive());

        // Champ calculé — sans @JsonIgnore sur classes
        // on doit absolument utiliser le DTO pour éviter la boucle infinie
        if (session.getClasses() != null) {
            dto.setNombreClasses(session.getClasses().size());
        } else {
            dto.setNombreClasses(0);
        }
        
        List<TypeClasse> types = new ArrayList<>();

        if(session.getConfigurations() != null) {
        	
        	for(ConfigurationInscription c : session.getConfigurations()){
        		types.add(c.getTypeClasse());
        	}
        }

        dto.setConfigurations(types);
        return dto;
    }

    public SessionOuverture toEntity(SessionOuvertureDTO dto) {
        if (dto == null) return null;

        SessionOuverture session = new SessionOuverture();
        session.setId(dto.getId());
        session.setLibelle(dto.getLibelle());
        session.setDateOuverture(dto.getDateOuverture());
        session.setDateFermeture(dto.getDateFermeture());
        session.setActive(dto.getActive());        
        return session;
    }

    public List<SessionOuvertureDTO> toDTOList(List<SessionOuverture> sessions) {
        if (sessions == null || sessions.isEmpty()) return null;

        List<SessionOuvertureDTO> dtos = new ArrayList<>();
        for (SessionOuverture s : sessions) {
            dtos.add(toDTO(s));
        }
        return dtos;
    }
}