package com.example.demo.controllers;

import com.example.demo.dtos.EvenementDto;
import com.example.demo.dtos.InscriptionDto;
import com.example.demo.services.EvenementService;
import com.example.demo.services.impl.EvenementServiceImpl;
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
	 * <p>Get all Evenements in the system</p>
	 * @return List<EvenementDto>
	 */
	@GetMapping
	public List<EvenementDto> getEvenements() {
		return evenementService.getAllEvenements();
	}

	/**
	 * Method to get the Evenement based on the ID
	 */
	@GetMapping("/{id}")
	public EvenementDto getEvenement(@PathVariable Long id){
		return evenementService.getEvenementById(id);
	}

	/**
	 * Method to get the evenement based on the membre ID
	 */
	@GetMapping("/membres/{id}/evenements")
	public List<EvenementDto> getEvenementByMembre(@PathVariable Long id){
		return evenementService.getEvenementByMembre(id);
	}

	/**
	 * Method to get the inscription based on the evenement ID
	 */
	@GetMapping("/{id}/inscriptions")
	public List<InscriptionDto> getInscriptionByEvenement(@PathVariable Long id){
		return evenementService.getInscriptionByEvenement(id);
	}

	/**
	 * Create a new inscription in the system
	 */
	@PostMapping("/{id}/membres/{id2}/inscriptions")
	public InscriptionDto saveInscription(@PathVariable Long id, @PathVariable Long id2){
		return evenementService.saveInscription(id, id2);
	}


	/**
	 * Create a new Evenement in the system
	 */
	@PostMapping
	public EvenementDto saveEvenement(final @RequestBody EvenementDto evenementDto){
		return evenementService.saveEvenement(evenementDto);
	}


	/**
	 * Modify a new Evenement in the system
	 */
	@PutMapping
	public EvenementDto modifyEvenement(final @RequestBody EvenementDto evenementDto){
		return evenementService.modifyEvenement(evenementDto);
	}

	/**
	 * Delete a Evenement by it's id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteEvenement(@PathVariable Long id){
		return evenementService.deleteEvenement(id);
	}
	
}

