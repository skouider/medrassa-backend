package com.esnet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esnet.dto.LoginRequestDTO;
import com.esnet.dto.LoginResponseDTO;
import com.esnet.services.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO dto) throws Exception{
        System.out.println("===== LOGIN CONTROLLER =====");

    	return authService.login(dto);

    }

}
