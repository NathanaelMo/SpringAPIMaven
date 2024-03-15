package com.spring.controllers;

import com.spring.dtos.InscriptionDto;
import com.spring.dtos.MembreDto;
import com.spring.dtos.EvenementDto;
import com.spring.services.MembreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membres")
public class MembreController {

	private final MembreService membreService;

	public MembreController(MembreService membreService) {
		this.membreService = membreService;
	}

	/**
	 * Obtenir tous les membres
	 */
	@GetMapping
	public List<MembreDto> getMembres() {
		return membreService.getAllMembres();
	}

	/**
	 * Obtenir un membre avec son id
	 */
	@GetMapping("/{id}")
	public MembreDto getMembre(@PathVariable Long id){
		return membreService.getMembreById(id);
	}

	/**
	 * Créer un membre
	 */
	@PostMapping
	public MembreDto saveMembre(final @RequestBody MembreDto membreDto){
		return membreService.saveMembre(membreDto);
	}

	/**
	 * Obtenir un evenement avec l'id d'un membre
	 */
	@GetMapping("/{id}/evenements")
	public List<EvenementDto> getEvenementByMembre(@PathVariable Long id){
		return membreService.getEvenementByMembre(id);
	}


	/**
	 * Obtenir une inscription avec l'id d'un membre
	 */
	@GetMapping("/{id}/inscriptions")
	public List<InscriptionDto> getInscriptionByMembre(@PathVariable Long id){
		return membreService.getInscriptionByMembre(id);
	}

	/**
	 * Modifier un membre
	 */
	@PutMapping
	public MembreDto modifyMembre(final @RequestBody MembreDto membreDto){
		return membreService.modifyMembre(membreDto);
	}

	/**
	 * Supprimer un membre avec son id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteMembre(@PathVariable Long id){
		return membreService.deleteMembre(id);
	}

}

