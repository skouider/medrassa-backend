package com.esnet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esnet.beans.Utilisateur;
import com.esnet.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur save(Utilisateur utilisateur) {

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {

        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable"));
    }

}
