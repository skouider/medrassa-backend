package com.esnet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Classe;
import com.esnet.beans.ConfigurationInscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;
import com.esnet.repository.ConfigurationRepository;
import com.esnet.repository.SessionOuvertureRepository;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionOuvertureRepository sessionRepository;
	@Autowired
	private ConfigurationRepository configurationRepository;
	@Autowired
	@Lazy
	private ClasseService classeService;

	@Override
//	public SessionOuverture save(SessionOuverture session) {
//
//		if (sessionRepository.existsByLibelle(session.getLibelle())) {
//			throw new RuntimeException("Une session avec ce libellé existe déjà");
//		}
//		if (session.getDateOuverture() == null || session.getDateFermeture() == null) {
//
//			throw new RuntimeException("Les dates sont obligatoires");
//		}
//
//		if (session.getDateOuverture().isAfter(session.getDateFermeture())) {
//
//			throw new RuntimeException("Date ouverture invalide");
//		}
//		
//		session.setActive(false);
//		configurerSession(session.getId(), getConfigurationsSession(session.getId()));
//		return sessionRepository.save(session);
//	}

	@Transactional
	public SessionOuverture save(SessionOuverture session) {

	    if (sessionRepository.existsByLibelle(session.getLibelle())) {
	        throw new RuntimeException("Une session avec ce libellé existe déjà");
	    }

	    if (session.getDateOuverture() == null
	            || session.getDateFermeture() == null) {
	        throw new RuntimeException("Les dates sont obligatoires");
	    }

	    if (session.getDateOuverture().isAfter(session.getDateFermeture())) {
	        throw new RuntimeException("Date ouverture invalide");
	    }

	    session.setActive(false);

	    SessionOuverture sessionSaved = sessionRepository.save(session);

	    if (session.getConfigurations() != null) {

	        for (ConfigurationInscription config : session.getConfigurations()) {

	            ConfigurationInscription nouvelleConfig =
	                    new ConfigurationInscription();

	            nouvelleConfig.setSessionOuverture(sessionSaved);
	            nouvelleConfig.setTypeClasse(config.getTypeClasse());

	            configurationRepository.save(nouvelleConfig);
	        }
	    }

	    return sessionSaved;
	}

	@Override
	public SessionOuverture update(SessionOuverture session, Long id) {

		SessionOuverture existingSession = findById(id);

		existingSession.setLibelle(session.getLibelle());
		existingSession.setActive(session.getActive());
		existingSession.setDateFermeture(session.getDateFermeture());
		existingSession.setDateOuverture(session.getDateOuverture());
		return sessionRepository.save(existingSession);
	}

	@Override
	@Transactional
	public int deleteById(Long id) {

		if (!sessionRepository.existsById(id)) {
			return -1;
		}
		SessionOuverture session = findById(id);

		if (Boolean.TRUE.equals(session.getActive())) {
			throw new RuntimeException("Impossible de supprimer une session active");
		}
		classeService.deleteBySessionId(id);
		sessionRepository.deleteById(id);
		return 1;
	}

	@Override
	public SessionOuverture findById(Long id) {

		return sessionRepository.findById(id).orElseThrow(
		        () -> new IllegalArgumentException("Aucune session d'ouverture trouvée avec l'ID : " + id)
);
	}

	@Override
	public List<SessionOuverture> findAll() {

		return sessionRepository.findAll();
	}
	
	@Override
	@Transactional
	public void configurerSession(Long sessionId, List<TypeClasse> types) {

		System.out.println("Session "+sessionId);
		System.out.println("type de classe "+types);
		
	    SessionOuverture session = findById(sessionId);
	    if (session == null) {
	        throw new RuntimeException("session introuvable");
	    }

	    // supprimer anciennes configs
	    configurationRepository.deleteBySessionOuverture(session);

	    
	    for (TypeClasse type : types) {
	    	
	    	ConfigurationInscription config = new ConfigurationInscription();
	    	config.setSessionOuverture(session);
	    	config.setTypeClasse(type);;
	    	configurationRepository.save(config);
	    }
	}

	@Override
	@Transactional
	public SessionOuverture ouvrirSession(Long id) {
		if (findById(id) == null) {
			throw new RuntimeException("session introuvable");
		}

		List<SessionOuverture> sessionActives = new ArrayList<>();
		List<SessionOuverture> sessions = findAll();
		for (SessionOuverture sessionOuverture : sessions) {
			if (sessionOuverture.getActive() == true) {
				sessionActives.add(sessionOuverture);
			}
		}
		for (SessionOuverture session : sessionActives) {
			session.setActive(false);
			sessionRepository.save(session);
		}

		SessionOuverture session = findById(id);
		session.setActive(true);
		sessionRepository.save(session);
		return session;
	}

	@Override
	@Transactional
	public SessionOuverture fermerSession(Long id) {
		SessionOuverture sessionOuverture = findById(id);
		sessionOuverture.setActive(false);
		return sessionRepository.save(sessionOuverture);
	}

	@Override
	public SessionOuverture getSessionActive() {

		return sessionRepository.findByActive(true);
	}
	
	@Override
	public List<TypeClasse> getConfigurationsSessionActive() {

	    SessionOuverture session = getSessionActive();

	    if(session == null) {
	        return new ArrayList<>();
	    }

	    List<ConfigurationInscription> configurations =
	            configurationRepository.findBySessionOuverture(session);

	    List<TypeClasse> types = new ArrayList<>();

	    for (ConfigurationInscription config : configurations) {
	        types.add(config.getTypeClasse());
	    }

	    return types;
	   
	}

	@Override
	public List<TypeClasse> getConfigurationsSession(Long id) {
		
	    List<ConfigurationInscription> configs = configurationRepository
	    		.findBySessionOuvertureId(id);
	    List<TypeClasse> types = new ArrayList<>();
	    for (ConfigurationInscription configurationInscription : configs) {
	    	types.add(configurationInscription.getTypeClasse());
	    }
	    return types;
	}
}
