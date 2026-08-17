package com.esnet.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.esnet.beans.ConfigurationInscription;
import com.esnet.dto.ConfigurationInscriptionDTO;

@Component
public class ConfigurationInscriptionMapper {

    public ConfigurationInscriptionDTO toDTO(ConfigurationInscription config) {

        if (config == null) {
            return null;
        }

        ConfigurationInscriptionDTO dto = new ConfigurationInscriptionDTO();

        dto.setId(config.getId());

        if (config.getSessionOuverture() != null) {
            dto.setSessionId(config.getSessionOuverture().getId());
        }

        dto.setTypeClasse(config.getTypeClasse());

        return dto;
    }

    public List<ConfigurationInscriptionDTO> toDTOList(List<ConfigurationInscription> configs) {

        List<ConfigurationInscriptionDTO> dtos = new ArrayList<>();

        if (configs == null) {
            return dtos;
        }

        for (ConfigurationInscription c : configs) {
            dtos.add(toDTO(c));
        }

        return dtos;
    }
}