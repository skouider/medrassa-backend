package com.esnet.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.esnet.beans.Classe;
import com.esnet.beans.Genre;
import com.esnet.beans.TypeClasse;
import com.esnet.dto.ClasseDTO;
import com.esnet.mapper.ClasseMapper;
import com.esnet.services.ClasseService;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    @Autowired private ClasseService classeService;
    @Autowired private ClasseMapper  classeMapper;

    @GetMapping
    public List<ClasseDTO> findAll() {
        return classeMapper.toDTOList(classeService.findAll());
        

    }

    @GetMapping("/{id}")
    public ClasseDTO findById(@PathVariable Long id) {
        return classeMapper.toDTO(classeService.findById(id));
    }

    @GetMapping("/session/{sessionId}")
    public List<ClasseDTO> findBySession(@PathVariable Long sessionId) {
        return classeMapper.toDTOList(classeService.findBySession(sessionId));
    }

    @GetMapping("/coran")
    public List<ClasseDTO> findClassesCoran() {
        return classeMapper.toDTOList(classeService.findClassesCoran());
    }

    @GetMapping("/preparatoire")
    public List<ClasseDTO> findClassesPreparatoire() {
        return classeMapper.toDTOList(classeService.findClassesPreparatoire());
    }

    @GetMapping("/search")
    public List<ClasseDTO> findByTypeClasseAndGenre(
            @RequestParam TypeClasse typeClasse,
            @RequestParam Genre genre) {
        return classeMapper.toDTOList(
            classeService.findByTypeClasseAndGenre(typeClasse, genre)
        );
    }

    @GetMapping("/{id}/places")
    public long nombrePlacesDisponibles(@PathVariable Long id) {
        return classeService.nombrePlacesDisponibles(id);
    }

    @PostMapping
    public ClasseDTO save(@RequestBody ClasseDTO dto) {
        Classe classe = classeMapper.toEntity(dto);
        return classeMapper.toDTO(classeService.save(classe));
    }

    @PutMapping("/{id}")
    public ClasseDTO update(
            @PathVariable Long id,
            @RequestBody ClasseDTO dto) {
        Classe classe = classeMapper.toEntity(dto);
        return classeMapper.toDTO(classeService.update(classe, id));
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return classeService.deleteById(id);
    }
    
    @GetMapping("/type/{typeClasse}/session/{sessionId}")
    List<ClasseDTO> findByTypeClasseAndSessionOuverture(@PathVariable TypeClasse typeClasse,@PathVariable Long sessionId){
    	return classeMapper.toDTOList(classeService.findByTypeClasseAndSession(typeClasse, sessionId));
    			
    }
    
    @GetMapping("/genre/{genre}/session/{sessionId}")
    List<ClasseDTO> findByGenreAndSession(@PathVariable Genre genre,@PathVariable Long sessionId){
    	return classeMapper.toDTOList(classeService.findByGenreAndSessionId(genre, sessionId));
    			
    }
    
    @GetMapping("/session-active")
    public List<ClasseDTO> findBySessionActive() {

        return classeMapper.toDTOList(
                classeService.findBySessionActive()
        );
    }
    	
    
}
