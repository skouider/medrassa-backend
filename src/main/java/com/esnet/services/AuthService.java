package com.esnet.services;

import com.esnet.dto.LoginRequestDTO;
import com.esnet.dto.LoginResponseDTO;

public interface AuthService {

	LoginResponseDTO login(LoginRequestDTO dto)throws Exception;
}
