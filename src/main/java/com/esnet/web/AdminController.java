package com.esnet.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esnet.beans.Admin;
import com.esnet.dto.*;
import com.esnet.mapper.*;
import com.esnet.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private AdminService           adminService;
    @Autowired private AdminMapper            adminMapper;
    @Autowired private SessionOuvertureMapper sessionMapper;
    @Autowired private ClasseMapper           classeMapper;
    @Autowired private InscriptionMapper      inscriptionMapper;

    // ── ADMIN CRUD ────────────────────────────────────────────

    @GetMapping
    public List<AdminDTO> findAll() {
        return adminMapper.toDTOList(adminService.findAll());
    }

    @GetMapping("/username/{username}")
    public AdminDTO findByUsername(@PathVariable String username) {
        return adminMapper.toDTO(adminService.findByUsername(username));
    }

    @PostMapping
    public AdminDTO save(@RequestBody Admin admin) {
        return adminMapper.toDTO(adminService.save(admin));
    }

    @PutMapping
    public AdminDTO update(@RequestBody Admin admin) {
        return adminMapper.toDTO(adminService.update(admin));
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return adminService.deleteById(id);
    }

    // ── SESSIONS ──────────────────────────────────────────────

    @GetMapping("/sessions")
    public List<SessionOuvertureDTO> findAllSessions() {
        return sessionMapper.toDTOList(adminService.findAllSessions());
    }

    @PutMapping("/sessions/{id}/ouvrir")
    public SessionOuvertureDTO ouvrirSession(@PathVariable Long id) {
        return sessionMapper.toDTO(adminService.ouvrirSession(id));
    }

    @PutMapping("/sessions/{id}/fermer")
    public SessionOuvertureDTO fermerSession(@PathVariable Long id) {
        return sessionMapper.toDTO(adminService.fermerSession(id));
    }

    // ── CLASSES ───────────────────────────────────────────────

    @GetMapping("/classes")
    public List<ClasseDTO> findAllClasses() {
        return classeMapper.toDTOList(adminService.findAllClasses());
    }

    @GetMapping("/classes/coran")
    public List<ClasseDTO> findClassesCoran() {
        return classeMapper.toDTOList(adminService.findClassesCoran());
    }

    @GetMapping("/classes/preparatoire")
    public List<ClasseDTO> findClassesPreparatoire() {
        return classeMapper.toDTOList(adminService.findClassesPreparatoire());
    }

    @GetMapping("/classes/{id}/places")
    public long nombrePlacesDisponibles(@PathVariable Long id) {
        return adminService.nombrePlacesDisponibles(id);
    }

    @PostMapping("/classes")
    public ClasseDTO creerClasse(@RequestBody ClasseDTO dto) {
        return classeMapper.toDTO(
            adminService.creerClasse(classeMapper.toEntity(dto))
        );
    }

    @PutMapping("/classes/{id}")
    public ClasseDTO modifierClasse(
            @PathVariable Long id,
            @RequestBody ClasseDTO dto) {
        return classeMapper.toDTO(
            adminService.modifierClasse(classeMapper.toEntity(dto), id)
        );
    }

    @DeleteMapping("/classes/{id}")
    public int supprimerClasse(@PathVariable Long id) {
        return adminService.supprimerClasse(id);
    }

    // ── INSCRIPTIONS ──────────────────────────────────────────

    @GetMapping("/inscriptions")
    public List<InscriptionDTO> findAllInscriptions() {
        return inscriptionMapper.toDTOList(adminService.findAllInscriptions());
    }

    @GetMapping("/inscriptions/validees")
    public List<InscriptionDTO> findValidees() {
        return inscriptionMapper.toDTOList(adminService.findValidees());
    }

    @GetMapping("/inscriptions/en-attente")
    public List<InscriptionDTO> findEnAttente() {
        return inscriptionMapper.toDTOList(adminService.findEnAttente());
    }

    @GetMapping("/inscriptions/liste-attente")
    public List<InscriptionDTO> findListeAttente() {
        return inscriptionMapper.toDTOList(adminService.findListeAttente());
    }

    @GetMapping("/inscriptions/{reference}")
    public InscriptionDTO findByReference(@PathVariable String reference) {
        return inscriptionMapper.toDTO(
            adminService.findByReference(reference)
        );
    }

    @GetMapping("/inscriptions/classe/{classeId}")
    public List<InscriptionDTO> findByClasse(@PathVariable Long classeId) {
        return inscriptionMapper.toDTOList(
            adminService.findByClasse(classeId)
        );
    }

    @PutMapping("/inscriptions/{id}/valider")
    public InscriptionDTO valider(@PathVariable Long id) {
        return inscriptionMapper.toDTO(adminService.valider(id));
    }

    @PutMapping("/inscriptions/{id}/refuser")
    public InscriptionDTO refuser(@PathVariable Long id) {
        return inscriptionMapper.toDTO(adminService.refuser(id));
    }

    @PutMapping("/inscriptions/{id}/annuler")
    public InscriptionDTO annuler(@PathVariable Long id) {
        return inscriptionMapper.toDTO(adminService.annuler(id));
    }

    // ── LISTE D'ATTENTE ───────────────────────────────────────

    @PutMapping("/inscriptions/liste-attente/{classeId}/valider-premier")
    public InscriptionDTO validerPremierDeLaListe(
            @PathVariable Long classeId) {
        return inscriptionMapper.toDTO(
            adminService.validerPremierDeLaListe(classeId)
        );
    }

    @GetMapping("/inscriptions/liste-attente/classe/{classeId}")
    public List<InscriptionDTO> getListeAttenteParClasse(
            @PathVariable Long classeId) {
        return inscriptionMapper.toDTOList(
            adminService.getListeAttenteParClasse(classeId)
        );
    }

    // ── EXPORT ────────────────────────────────────────────────

    @GetMapping("/export/classe/{classeId}")
    public List<InscriptionDTO> exporterClasse(@PathVariable Long classeId) {
        return inscriptionMapper.toDTOList(
            adminService.exporterClasse(classeId)
        );
    }

    @GetMapping("/export/validees")
    public List<InscriptionDTO> exporterValidees() {
        return inscriptionMapper.toDTOList(adminService.exporterValidees());
    }

    // ── STATISTIQUES ──────────────────────────────────────────

    @GetMapping("/stats")
    public AdminStatDTO getStats() {
        AdminStatDTO dto = new AdminStatDTO();
        dto.setNombreTotalInscriptions(adminService.nombreTotalInscriptions());
        dto.setNombreValidees(adminService.nombreValidees());
        dto.setNombreListeAttente(adminService.nombreListeAttente());
        dto.setNombreRefusees(adminService.nombreRefusees());
        dto.setNombreAnnulees(adminService.nombreAnnulees());
        dto.setNombreGarcons(adminService.nombreGarcons());
        dto.setNombreFilles(adminService.nombreFilles());
        dto.setNombreCoran(adminService.nombreCoran());
        dto.setNombrePreparatoire(adminService.nombrePreparatoire());
        return dto;
    }

    @GetMapping("/stats/classe/{classeId}")
    public long nombreInscriptionsParClasse(@PathVariable Long classeId) {
        return adminService.nombreInscriptionsParClasse(classeId);
    }
}