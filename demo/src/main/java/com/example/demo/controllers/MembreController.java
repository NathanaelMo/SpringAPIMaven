package com.example.demo.controllers;

import com.example.demo.dtos.InscriptionDto;
import com.example.demo.dtos.MembreDto;
import com.example.demo.services.MembreService;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.impl.MembreServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/membres")
public class MembreController {
	
	private final MembreService membreService;

	public MembreController(MembreService membreService) {
		this.membreService = membreService;
	}

	/**
	 * <p>Get all membres in the system</p>
	 * @return List<MembreDto>
	 */
	@GetMapping
	public List<MembreDto> getMembres() {
		return membreService.getAllMembres();
	}

	/**
	 * Method to get the membre based on the ID
	 */
	@GetMapping("/{id}")
	public MembreDto getMembre(@PathVariable Long id){
		return membreService.getMembreById(id);
	}

	/**
	 * Create a new Membre in the system
	 */
	@PostMapping
	public MembreDto saveMembre(final @RequestBody MembreDto membreDto){
		return membreService.saveMembre(membreDto);
	}

	/**
	 * Method to get the membre based on the evenement ID
	 */
	@GetMapping("/evenements/{id}/membres")
	public List<MembreDto> getMembreByEvenement(@PathVariable Long id){
		return membreService.getMembreByEvenement(id);
	}


	/**
	 * Method to get the inscription based on the membre ID
	 */
	@GetMapping("/{id}/inscriptions")
	public List<InscriptionDto> getInscriptionByMembre(@PathVariable Long id){
		return membreService.getInscriptionByMembre(id);
	}

	/**
	 * Modify a new Membre in the system
	 */
	@PutMapping
	public MembreDto modifyMembre(final @RequestBody MembreDto membreDto){
		return membreService.modifyMembre(membreDto);
	}

	/**
	 * Delete a membre by it's id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteMembre(@PathVariable Long id){
		return membreService.deleteMembre(id);
	}
	
}
