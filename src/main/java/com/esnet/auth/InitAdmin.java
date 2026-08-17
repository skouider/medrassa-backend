package com.esnet.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.esnet.beans.Role;
import com.esnet.beans.Utilisateur;
import com.esnet.repository.UtilisateurRepository;

//@Component
public class InitAdmin implements CommandLineRunner{

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public void run(String... args) throws Exception {

		Utilisateur admin = new Utilisateur();
		admin.setNom("ali");
		admin.setPrenom("behar");
		admin.setEmail("admin@gmail.com");
		admin.setPassword(passwordEncoder.encode("12345"));
		admin.setRole(Role.ROLE_ADMIN);
		utilisateurRepository.save(admin);
		
		
	}

	
}
