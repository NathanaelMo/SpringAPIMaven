package com.spring.services.impl;

import com.spring.dtos.InscriptionDto;
import com.spring.entities.Inscription;
import com.spring.repositories.InscriptionRepository;
import com.spring.services.InscriptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service("inscriptionService")
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository){
        this.inscriptionRepository = inscriptionRepository;
    }


    @Override
    public InscriptionDto saveInscription(InscriptionDto inscriptionDto) {
        Inscription inscription = inscriptionDtoToEntity(inscriptionDto);
        inscription = inscriptionRepository.save(inscription);
        return inscriptionEntityToDto(inscription);
    }

    @Override
    public InscriptionDto modifyInscription(InscriptionDto inscriptionDto) {
        Inscription inscription = inscriptionDtoToEntity(inscriptionDto);
        inscription = inscriptionRepository.save(inscription);
        return inscriptionEntityToDto(inscription);
    }

    @Override
    public List<InscriptionDto> getInscriptionByIdEvenement(Long idEvenement) {
        List<InscriptionDto> inscriptionDtos = new ArrayList<>();
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        inscriptions.forEach(inscription -> {
            if(inscription.getIdEvenement() == idEvenement){
                inscriptionDtos.add(inscriptionEntityToDto(inscription));
            }
        });
        return inscriptionDtos;
    }

    @Override
    public List<InscriptionDto> getInscriptionByIdMembre(Long idMembre) {
        List<InscriptionDto> inscriptionDtos = new ArrayList<>();
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        inscriptions.forEach(inscription -> {
            if(inscription.getIdMembre() == idMembre){
                inscriptionDtos.add(inscriptionEntityToDto(inscription));
            }
        });
        return inscriptionDtos;
    }

    @Override
    public boolean deleteInscription(Long membreId, Long evenementId) {
        AtomicBoolean delete = new AtomicBoolean(false);
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        inscriptions.forEach(inscription -> {
            if(inscription.getIdMembre() == membreId && inscription.getIdEvenement() == evenementId){
                inscriptionRepository.deleteById(inscription.getIdInscription());
                delete.set(true);
            }
        });
        if(delete.get()){
            return true;
        }
        return false;
    }

    @Override
    public List<InscriptionDto> getAllInscriptions() {
        List<InscriptionDto> inscriptionDtos = new ArrayList<>();
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        inscriptions.forEach(inscription -> {
            inscriptionDtos.add(inscriptionEntityToDto(inscription));
        });
        return inscriptionDtos;
    }

    private InscriptionDto inscriptionEntityToDto(Inscription inscription){
        InscriptionDto inscriptionDto = new InscriptionDto();
        inscriptionDto.setIdInscription(inscription.getIdInscription());
        inscriptionDto.setIdMembre(inscription.getIdMembre());
        inscriptionDto.setIdEvenement(inscription.getIdEvenement());
        return inscriptionDto;
    }

    private Inscription inscriptionDtoToEntity(InscriptionDto inscriptionDto){
        Inscription inscription = new Inscription();
        inscription.setIdInscription(inscriptionDto.getIdInscription());
        inscription.setIdMembre(inscriptionDto.getIdMembre());
        inscription.setIdEvenement(inscriptionDto.getIdEvenement());
        return inscription;
    }

}

