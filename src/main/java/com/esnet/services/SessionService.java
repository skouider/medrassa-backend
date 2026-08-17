package com.esnet.services;

import java.util.List;

import com.esnet.beans.Classe;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;

public interface SessionService {

    SessionOuverture save(SessionOuverture session);

    SessionOuverture update(SessionOuverture session, Long id);

    int deleteById(Long id);

    SessionOuverture findById(Long id);

    List<SessionOuverture> findAll();
    
    void configurerSession(Long sessionId, List<TypeClasse> typeClasses);

    SessionOuverture ouvrirSession(Long id);

    SessionOuverture fermerSession(Long id);

    SessionOuverture getSessionActive();
    
    List<TypeClasse> getConfigurationsSessionActive();
    
    List<TypeClasse> getConfigurationsSession(Long id);
    
    
}