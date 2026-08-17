package com.esnet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esnet.beans.Admin;
import com.esnet.beans.Classe;
import com.esnet.beans.Inscription;
import com.esnet.beans.SessionOuverture;
import com.esnet.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin save(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin update(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionOuverture ouvrirSession(Long sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionOuverture fermerSession(Long sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionOuverture> findAllSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classe creerClasse(Classe classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classe modifierClasse(Classe classe, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int supprimerClasse(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Classe> findAllClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Classe> findClassesCoran() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Classe> findClassesPreparatoire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long nombrePlacesDisponibles(Long classeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Inscription> findAllInscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> findValidees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> findEnAttente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> findListeAttente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription valider(Long inscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription refuser(Long inscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription annuler(Long inscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription findByReference(String reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> findByClasse(Long classeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long nombreTotalInscriptions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreValidees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreListeAttente() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreRefusees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreAnnulees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreInscriptionsParClasse(Long classeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreGarcons() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreFilles() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombreCoran() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long nombrePreparatoire() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Inscription> getListeAttente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription validerPremierDeLaListe(Long classeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> getListeAttenteParClasse(Long classeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> exporterClasse(Long classeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscription> exporterValidees() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
