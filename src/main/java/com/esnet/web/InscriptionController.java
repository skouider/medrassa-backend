package com.esnet.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.esnet.beans.Inscription;
import com.esnet.dto.InscriptionAdminRequestDTO;
import com.esnet.dto.InscriptionDTO;
import com.esnet.dto.InscriptionRequestDTO;
import com.esnet.mapper.InscriptionMapper;
import com.esnet.services.InscriptionService;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

	@Autowired
	private InscriptionService inscriptionService;
	@Autowired
	private InscriptionMapper inscriptionMapper;

	@GetMapping
	public List<InscriptionDTO> findAll() {
		return inscriptionMapper.toDTOList(inscriptionService.findAll());
	}

	@GetMapping("/{id}")
	public InscriptionDTO findById(@PathVariable Long id) {
		return inscriptionMapper.toDTO(inscriptionService.findById(id));
	}

	@GetMapping("/reference/{reference}")
	public InscriptionDTO findByReference(@PathVariable String reference) {
		return inscriptionMapper.toDTO(inscriptionService.findByReference(reference));
	}

	@GetMapping("/classe/{classeId}")
	public List<InscriptionDTO> findByClasse(@PathVariable Long classeId) {
		return inscriptionMapper.toDTOList(inscriptionService.findByClasse(classeId));
	}

	@GetMapping("/en-attente")
	public List<InscriptionDTO> findEnAttente() {
		return inscriptionMapper.toDTOList(inscriptionService.findEnAttente());
	}

	@GetMapping("/validees")
	public List<InscriptionDTO> findValidees() {
		return inscriptionMapper.toDTOList(inscriptionService.findValidees());
	}

	@PostMapping
	public InscriptionDTO save(@RequestBody InscriptionRequestDTO dto) {
		Inscription inscription = inscriptionMapper.requestToEntity(dto);
		return inscriptionMapper.toDTO(inscriptionService.save(inscription));
	}

	@PutMapping("/{id}/valider")
	public InscriptionDTO valider(@PathVariable Long id) {
		return inscriptionMapper.toDTO(inscriptionService.valider(id));
	}

	@PutMapping("/{id}/refuser")
	public InscriptionDTO refuser(@PathVariable Long id) {
		return inscriptionMapper.toDTO(inscriptionService.refuser(id));
	}

	@PutMapping("/{id}/annuler")
	public InscriptionDTO annuler(@PathVariable Long id) {
		return inscriptionMapper.toDTO(inscriptionService.annuler(id));
	}

	@DeleteMapping("/{id}")
	public int deleteById(@PathVariable Long id) {
		return inscriptionService.deleteById(id);
	}

	@PostMapping("/admin/{sessionId}")
	public InscriptionDTO saveByAdmin(@PathVariable Long sessionId, @RequestBody InscriptionAdminRequestDTO dto) {

		Inscription inscription = inscriptionMapper.adminRequestToEntity(dto);

		return inscriptionMapper.toDTO(inscriptionService.saveByAdmin(inscription, sessionId));
	}

	@GetMapping("/page")
	public Page<InscriptionDTO> findAll(

			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size

	) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Inscription> inscriptions = inscriptionService.findAll(pageable);

		List<InscriptionDTO> dtoList = new ArrayList<>();

		for (Inscription inscription : inscriptions.getContent()) {
			dtoList.add(inscriptionMapper.toDTO(inscription));
		}

		return new PageImpl<>(dtoList, pageable, inscriptions.getTotalElements());
	}

	@GetMapping("/search")
	public List<InscriptionDTO> rechercher(@RequestParam String keyword) {

		return inscriptionMapper.toDTOList(inscriptionService.rechercher(keyword));
	}
}