package com.example.demo.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Evenement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvenement;

	private Long idLieu;

	private String nom;

	private LocalDate date;

	private int duree;

	private int nbMaxPers;

}
