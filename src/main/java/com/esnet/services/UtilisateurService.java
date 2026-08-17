package com.esnet.services;

import com.esnet.beans.Utilisateur;

public interface UtilisateurService {

    Utilisateur save(Utilisateur utilisateur);

    Utilisateur findByEmail(String email);

}
