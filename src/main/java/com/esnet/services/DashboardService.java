package com.esnet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esnet.beans.Genre;
import com.esnet.beans.StatutInscription;
import com.esnet.dto.StatistiqueDashboardDTO;
import com.esnet.repository.ClasseRepository;
import com.esnet.repository.InscriptionRepository;

@Service
public class DashboardService {

	@Autowired
	private InscriptionRepository inscriptionRepository;
	@Autowired
	private ClasseRepository classeRepository;

    public StatistiqueDashboardDTO statistiques() {

    StatistiqueDashboardDTO dto = new StatistiqueDashboardDTO();

   
    
    dto.setNombreClasses(classeRepository.count());

    dto.setNombreEleves(inscriptionRepository.count());

    dto.setNombreGarcons(
            inscriptionRepository.countByGenre(Genre.GARCON));

    dto.setNombreFilles(
            inscriptionRepository.countByGenre(Genre.FILLE));

    dto.setNombreValidees(
            inscriptionRepository.countByStatut(
                    StatutInscription.VALIDEE));

    dto.setNombreListeAttente(
            inscriptionRepository.countByStatut(
                    StatutInscription.LISTE_ATTENTE));

    dto.setNombreRefusees(
            inscriptionRepository.countByStatut(
                    StatutInscription.REFUSEE));

    dto.setNombreAnnulees(
            inscriptionRepository.countByStatut(
                    StatutInscription.ANNULEE));

    return dto;
}

}

