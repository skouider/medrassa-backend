-- Create Admin table
CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255)
);

-- Create Utilisateur table
CREATE TABLE utilisateur (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

-- Create SessionOuverture table
CREATE TABLE session_ouverture (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255),
    date_ouverture DATE,
    date_fermeture DATE,
    active BOOLEAN
);

-- Create Classe table
CREATE TABLE classe (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    age_min INT,
    age_max INT,
    capacite INT,
    genre VARCHAR(50),
    type_classe VARCHAR(50),
    session_id BIGINT,
    FOREIGN KEY (session_id) REFERENCES session_ouverture(id)
);

-- Create Inscription table
CREATE TABLE inscription (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reference VARCHAR(255),
    date_inscription DATE,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    date_naissance DATE,
    telephone VARCHAR(20),
    adresse VARCHAR(255),
    nom_tuteur VARCHAR(255),
    type_classe VARCHAR(50),
    genre VARCHAR(50),
    statut VARCHAR(50),
    classe_id BIGINT,
    FOREIGN KEY (classe_id) REFERENCES classe(id)
);

-- Create ConfigurationInscription table
CREATE TABLE configuration_inscription (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type_classe VARCHAR(50),
    session_ouverture_id BIGINT,
    FOREIGN KEY (session_ouverture_id) REFERENCES session_ouverture(id)
);

-- Create indexes for better performance
CREATE INDEX idx_utilisateur_email ON utilisateur(email);
CREATE INDEX idx_classe_session ON classe(session_id);
CREATE INDEX idx_inscription_classe ON inscription(classe_id);
CREATE INDEX idx_config_session ON configuration_inscription(session_ouverture_id);

