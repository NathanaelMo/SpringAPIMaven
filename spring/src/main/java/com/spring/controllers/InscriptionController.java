package com.spring.controllers;

import com.spring.dtos.InscriptionDto;
import com.spring.services.InscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

	private final InscriptionService inscriptionService;

	public InscriptionController(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}

	/**
	 * Obtenir toutes les inscriptions
	 */
	@GetMapping
	public List<InscriptionDto> getInscriptions() {
		return inscriptionService.getAllInscriptions();
	}

	/**
	 * Obtenir les inscriptions avec l'id d'un evenement
	 */
	@GetMapping("/evenements/{id}")
	public List<InscriptionDto> getInscriptionByIdEvenement(@PathVariable Long id){
		return inscriptionService.getInscriptionByIdEvenement(id);
	}

	/**
	 * Obtenir les inscriptions avec l'id d'un membre
	 */
	@GetMapping("/membres/{id}")
	public List<InscriptionDto> getInscriptionByIdMembre(@PathVariable Long id){
		return inscriptionService.getInscriptionByIdMembre(id);
	}

	/**
	 * Créer une inscription
	 */
	@PostMapping
	public InscriptionDto saveInscription(final @RequestBody InscriptionDto inscriptionDto){
		return inscriptionService.saveInscription(inscriptionDto);
	}


	/**
	 * Modifier une inscription
	 */
	@PutMapping
	public InscriptionDto modifyEvenement(final @RequestBody InscriptionDto inscriptionDto){
		return inscriptionService.modifyInscription(inscriptionDto);
	}

	/**
	 * Supprimer une inscription avec l'id d'un membre et d'un evenement
	 */
	@DeleteMapping("/membres/{id}/evenements/{id2}")
	public Boolean deleteInscription(@PathVariable Long id, @PathVariable Long id2){
		return inscriptionService.deleteInscription(id, id2);
	}

}
