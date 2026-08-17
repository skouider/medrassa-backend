package com.esnet.dto;

import com.esnet.beans.TypeClasse;

public class ConfigurationInscriptionDTO {

    private Long id;

    private Long sessionId;
   
    private TypeClasse typeClasse;
    
	public TypeClasse getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(TypeClasse typeClasse) {
		this.typeClasse = typeClasse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
    
    
    

}
