package com.esnet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esnet.beans.Utilisateur;
import com.esnet.dto.LoginRequestDTO;
import com.esnet.dto.LoginResponseDTO;
import com.esnet.repository.UtilisateurRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponseDTO login(
            LoginRequestDTO dto) throws Exception {

        try {

            // ==========================================
            // 1. AUTHENTIFICATION
            // ==========================================

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    dto.getEmail(),
                                    dto.getPassword()
                            )
                    );

            // ==========================================
            // 2. UTILISATEUR AUTHENTIFIE
            // ==========================================

            UserDetails user =
                    (UserDetails) authentication.getPrincipal();

            // ==========================================
            // 3. RECUPERER L'UTILISATEUR
            // ==========================================

            Utilisateur utilisateur =
                    utilisateurRepository
                            .findByEmail(user.getUsername())
                            .orElseThrow();

            // ==========================================
            // 4. GENERER JWT AVEC ROLE
            // ==========================================

            String token =
                    jwtService.generateToken(
                            utilisateur.getEmail(),
                            utilisateur.getRole().name()
                    );

            // ==========================================
            // 5. REPONSE
            // ==========================================

            LoginResponseDTO response =
                    new LoginResponseDTO();

            response.setEmail(
                    utilisateur.getEmail()
            );

            response.setNom(
                    utilisateur.getNom()
            );

            response.setPrenom(
                    utilisateur.getPrenom()
            );

            response.setToken(token);

            return response;

        } catch (BadCredentialsException e) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Email ou mot de passe incorrect."
            );
        }
    }
}