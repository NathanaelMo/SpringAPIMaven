package com.spring.services;

import com.spring.dtos.EvenementDto;
import com.spring.dtos.InscriptionDto;
import com.spring.dtos.MembreDto;

import java.util.List;


public interface EvenementService {


    EvenementDto saveEvenement(EvenementDto evenementDto);

    EvenementDto modifyEvenement(EvenementDto evenementDto);

    EvenementDto getEvenementById(Long evenementId);

    List<MembreDto> getMembreByEvenement(Long evenementId);

    List<InscriptionDto> getInscriptionByEvenement(Long idEvenement);

    InscriptionDto saveInscription(Long id, Long id2);

    boolean deleteEvenement(Long evenementId);

    List<EvenementDto> getAllEvenements();


}

