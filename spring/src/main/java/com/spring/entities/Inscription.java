package com.spring.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.*;
@Entity
@Data
public class Inscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;

	private Long idMembre;

	private Long idEvenement;


}
