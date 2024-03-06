package com.example.demo.controllers;

import ch.qos.logback.classic.Logger;
import com.example.demo.dtos.EvenementDto;
import com.example.demo.dtos.InscriptionDto;
import com.example.demo.dtos.MembreDto;
import com.example.demo.services.InscriptionService;
import com.example.demo.services.impl.EvenementServiceImpl;
import com.example.demo.services.impl.InscriptionServiceImpl;
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
	 * <p>Get all Inscriptions in the system</p>
	 * @return List<InscriptionDto>
	 */
	@GetMapping
	public List<InscriptionDto> getInscriptions() {
		return inscriptionService.getAllInscriptions();
	}

	/**
	 * Method to get the Inscription based on the ID of an evenement
	 */
	@GetMapping("/evenements/{id}")
	public List<InscriptionDto> getInscriptionByIdEvenement(@PathVariable Long id){
		return inscriptionService.getInscriptionByIdEvenement(id);
	}

	/**
	 * Method to get the Inscription based on the ID of a membre
	 */
	@GetMapping("/membres/{id}")
	public List<InscriptionDto> getInscriptionByIdMembre(@PathVariable Long id){
		return inscriptionService.getInscriptionByIdMembre(id);
	}

	/**
	 * Create a new Inscription in the system
	 */
	@PostMapping
	public InscriptionDto saveInscription(final @RequestBody InscriptionDto inscriptionDto){
		return inscriptionService.saveInscription(inscriptionDto);
	}


	/**
	 * Modify a new Inscription in the system
	 */
	@PutMapping
	public InscriptionDto modifyEvenement(final @RequestBody InscriptionDto inscriptionDto){
		return inscriptionService.modifyInscription(inscriptionDto);
	}

	/**
	 * Delete a Inscription by it's id
	 */
	@DeleteMapping("/membres/{id}/evenements/{id2}")
	public Boolean deleteInscription(@PathVariable Long id, @PathVariable Long id2){
		return inscriptionService.deleteInscription(id, id2);
	}
	
}
