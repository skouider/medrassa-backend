package com.esnet.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esnet.beans.Classe;
import com.esnet.beans.SessionOuverture;
import com.esnet.beans.TypeClasse;
import com.esnet.dto.SessionOuvertureDTO;
import com.esnet.mapper.SessionOuvertureMapper;
import com.esnet.services.SessionService;

@RestController
@RequestMapping("/api/sessions")
public class SessionOuvertureController {

    @Autowired private SessionService         sessionService;
    @Autowired private SessionOuvertureMapper sessionMapper;

    @GetMapping
    public List<SessionOuvertureDTO> findAll() {
        return sessionMapper.toDTOList(sessionService.findAll());
    }

    @GetMapping("/{id}")
    public SessionOuvertureDTO findById(@PathVariable Long id) {
        return sessionMapper.toDTO(sessionService.findById(id));
    }

    @GetMapping("/active")
    public SessionOuvertureDTO getSessionActive() {
        return sessionMapper.toDTO(sessionService.getSessionActive());
    }

    @PostMapping
    public SessionOuvertureDTO save(@RequestBody SessionOuvertureDTO dto) {
        SessionOuverture session = sessionMapper.toEntity(dto);
        return sessionMapper.toDTO(sessionService.save(session));
    }

    @PutMapping("/{id}")
    public SessionOuvertureDTO update(
            @PathVariable Long id,
            @RequestBody SessionOuvertureDTO dto) {
        SessionOuverture session = sessionMapper.toEntity(dto);
        return sessionMapper.toDTO(sessionService.update(session, id));
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return sessionService.deleteById(id);
    }

    @PutMapping("/{id}/ouvrir")
    public SessionOuvertureDTO ouvrirSession(@PathVariable Long id) {
        return sessionMapper.toDTO(sessionService.ouvrirSession(id));
    }
    
    @PutMapping("/{id}/configuration")
    public void configurerSession(
            @PathVariable Long id,
            @RequestBody List<TypeClasse> types
    ) {
        sessionService.configurerSession(id, types);
    }


    @PutMapping("/{id}/fermer")
    public SessionOuvertureDTO fermerSession(@PathVariable Long id) {
        return sessionMapper.toDTO(sessionService.fermerSession(id));
    }
    
    @GetMapping("/active/configurations")
    public List<TypeClasse> getConfigurationsSessionActive(){
    	return sessionService.getConfigurationsSessionActive();
    }
    
    @GetMapping("/configurations/{id}")
    public List<TypeClasse> getConfigurationsBySessionId(@PathVariable Long id){
    	return sessionService.getConfigurationsSession(id);
    }
}