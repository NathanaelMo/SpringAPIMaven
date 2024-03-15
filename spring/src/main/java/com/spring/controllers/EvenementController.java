package com.spring.controllers;

import com.spring.dtos.EvenementDto;
import com.spring.dtos.MembreDto;
import com.spring.dtos.InscriptionDto;
import com.spring.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

	private final EvenementService evenementService;

	@Autowired
	public EvenementController(EvenementService evenementService) {
		this.evenementService = evenementService;
	}

	/**
	 * Obtenir tous les Evenements
	 */
	@GetMapping
	public List<EvenementDto> getEvenements() {
		return evenementService.getAllEvenements();
	}

	/**
	 * Obtenir un Evenement avec un id
	 */
	@GetMapping("/{id}")
	public EvenementDto getEvenement(@PathVariable Long id){
		return evenementService.getEvenementById(id);
	}

	/**
	 * Obtenir un membre avec l'id d'un evenement
	 */
	@GetMapping("/{id}/membres")
	public List<MembreDto> getMembreByEvenement(@PathVariable Long id){
		return evenementService.getMembreByEvenement(id);
	}

	/**
	 * Obtenir les inscriptions avec l'id d'un evenement
	 */
	@GetMapping("/{id}/inscriptions")
	public List<InscriptionDto> getInscriptionByEvenement(@PathVariable Long id){
		return evenementService.getInscriptionByEvenement(id);
	}

	/**
	 * Créer une inscription
	 */
	@PostMapping("/{id}/membres/{id2}/inscriptions")
	public InscriptionDto saveInscription(@PathVariable Long id, @PathVariable Long id2){
		return evenementService.saveInscription(id, id2);
	}


	/**
	 * Créer un evenement
	 */
	@PostMapping
	public EvenementDto saveEvenement(final @RequestBody EvenementDto evenementDto){
		return evenementService.saveEvenement(evenementDto);
	}


	/**
	 * Modifier un evenement
	 */
	@PutMapping
	public EvenementDto modifyEvenement(final @RequestBody EvenementDto evenementDto){
		return evenementService.modifyEvenement(evenementDto);
	}

	/**
	 * Supprimer un evenement avec son id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteEvenement(@PathVariable Long id){
		return evenementService.deleteEvenement(id);
	}

}

