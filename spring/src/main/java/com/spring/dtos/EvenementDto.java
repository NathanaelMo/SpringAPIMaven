package com.spring.dtos;

import lombok.Data;

import java.time.LocalDate;


@Data
public class EvenementDto {

	private Long idEvenement;

	private Long idLieu;

	private String nom;

	private LocalDate date;

	private int duree;

	private int nbMaxPers;



}
