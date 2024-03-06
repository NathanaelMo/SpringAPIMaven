package com.example.demo.services;

import com.example.demo.dtos.EvenementDto;
import com.example.demo.dtos.InscriptionDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EvenementService {

    /**
     * Sauve a Evenement
     */
    EvenementDto saveEvenement(EvenementDto evenementDto);

    /**
     * Moàify a Evenement
     */
    EvenementDto modifyEvenement(EvenementDto evenementDto);

    /**
     * Get a Evenement by it's id
     */
    EvenementDto getEvenementById(Long evenementId);


    List<EvenementDto> getEvenementByMembre(Long membreId);


    List<InscriptionDto> getInscriptionByEvenement(Long idEvenement);

    InscriptionDto saveInscription(Long id, Long id2);

    /**
     * Delete a Evenement by it's id
     */
    boolean deleteEvenement(Long evenementId);

    /**
     * Get all the Evenement
     */
    List<EvenementDto> getAllEvenements();


}
