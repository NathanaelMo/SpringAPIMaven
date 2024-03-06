package com.example.demo.entities;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Membre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMembre;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String mdp;
	private LocalDate dateNaissance;
}
