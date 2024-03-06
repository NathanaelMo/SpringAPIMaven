package com.example.demo.dtos;

import lombok.Data;

import java.time.LocalDate;



@Data
public class MembreDto {

	private Long idMembre;
	private String email;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String mdp;
	private String adresse;

}
