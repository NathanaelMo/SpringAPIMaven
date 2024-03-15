package com.spring.services;

import com.spring.dtos.InscriptionDto;

import java.util.List;

public interface InscriptionService {


    InscriptionDto saveInscription(InscriptionDto inscriptionDto);

    InscriptionDto modifyInscription(InscriptionDto inscriptionDto);

    List<InscriptionDto> getInscriptionByIdEvenement(Long idEvenement);

    List<InscriptionDto> getInscriptionByIdMembre(Long membreId);

    boolean deleteInscription(Long membreId, Long evenementId);

    List<InscriptionDto> getAllInscriptions();


}
