package com.esnet.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.esnet.beans.Admin;
import com.esnet.dto.AdminDTO;

@Component
public class AdminMapper {

    public AdminDTO toDTO(Admin admin) {
        if (admin == null) return null;

        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setNom(admin.getNom());
        dto.setPrenom(admin.getPrenom());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());
        // password → JAMAIS dans le DTO
        return dto;
    }

    public Admin toEntity(AdminDTO dto) {
        if (dto == null) return null;

        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        return admin;
    }

    public List<AdminDTO> toDTOList(List<Admin> admins) {
        if (admins == null || admins.isEmpty()) return null;

        List<AdminDTO> dtos = new ArrayList<>();
        for (Admin a : admins) {
            dtos.add(toDTO(a));
        }
        return dtos;
    }
}