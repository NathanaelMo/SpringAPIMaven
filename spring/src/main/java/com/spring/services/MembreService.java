package com.spring.services;

import com.spring.dtos.InscriptionDto;
import com.spring.dtos.MembreDto;
import com.spring.dtos.EvenementDto;

import java.util.List;

public interface MembreService {


    MembreDto saveMembre(MembreDto membreDto);

    MembreDto modifyMembre(MembreDto membreDto);

    MembreDto getMembreById(Long membreId);

    List<InscriptionDto> getInscriptionByMembre(Long membreId);

    List<EvenementDto> getEvenementByMembre(Long membreId);

    boolean deleteMembre(Long membreId);

    List<MembreDto> getAllMembres();


}