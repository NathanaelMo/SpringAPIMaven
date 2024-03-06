package com.example.demo.services.impl;

import com.example.demo.dtos.InscriptionDto;
import com.example.demo.entities.Inscription;
import com.example.demo.repositories.InscriptionRepository;
import com.example.demo.services.InscriptionService;
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
        // Converts the dto to the inscription entity
        Inscription inscription = inscriptionDtoToEntity(inscriptionDto);
        // Save the inscription entity
        inscription = inscriptionRepository.save(inscription);
        // Return the new dto
        return inscriptionEntityToDto(inscription);
    }

    @Override
    public InscriptionDto modifyInscription(InscriptionDto inscriptionDto) {
        // Converts the dto to the inscription entity
        Inscription inscription = inscriptionDtoToEntity(inscriptionDto);
        // Save the inscription entity
        inscription = inscriptionRepository.save(inscription);
        // Return the new dto
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
        List<InscriptionDto> inscriptionDtos = new ArrayList<>();
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

    /**
     * Map membre dto to membre entity
     */
    private InscriptionDto inscriptionEntityToDto(Inscription inscription){
        InscriptionDto inscriptionDto = new InscriptionDto();
        inscriptionDto.setIdInscription(inscription.getIdInscription());
        inscriptionDto.setIdMembre(inscription.getIdMembre());
        inscriptionDto.setIdEvenement(inscription.getIdEvenement());
        return inscriptionDto;
    }

    /**
     * Map membre entity to membre dto
     */
    private Inscription inscriptionDtoToEntity(InscriptionDto inscriptionDto){
        Inscription inscription = new Inscription();
        inscription.setIdInscription(inscriptionDto.getIdInscription());
        inscription.setIdMembre(inscriptionDto.getIdMembre());
        inscription.setIdEvenement(inscriptionDto.getIdEvenement());
        return inscription;
    }

}
