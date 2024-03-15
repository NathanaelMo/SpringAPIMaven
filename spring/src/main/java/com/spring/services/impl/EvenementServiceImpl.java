package com.spring.services.impl;
import com.spring.dtos.InscriptionDto;
import com.spring.dtos.MembreDto;
import com.spring.entities.Evenement;
import com.spring.dtos.EvenementDto;
import com.spring.repositories.EvenementRepository;
import com.spring.services.EvenementService;
import com.spring.services.InscriptionService;
import com.spring.services.MembreService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service("evenementService")
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;

    private final InscriptionService inscriptionService;

    private final MembreService membreService;

    public EvenementServiceImpl(EvenementRepository evenementRepository, InscriptionService inscriptionService, MembreService membreService){
        this.evenementRepository = evenementRepository;
        this.inscriptionService = inscriptionService;
        this.membreService = membreService;
    }

    @Override
    public EvenementDto saveEvenement(EvenementDto evenementDto) {
        Evenement evenement = evenementDtoToEntity(evenementDto);
        List<EvenementDto> evenements = this.getAllEvenements();
        for(int i = 0;i<evenements.size();i++){
            if(evenements.get(i).getIdEvenement() == evenementDto.getIdEvenement()){
                return null;
            }
            if(evenements.get(i).getDate().equals(evenementDto.getDate()) && evenements.get(i).getIdLieu() == evenementDto.getIdLieu()){
                return null;
            }
        }
        evenement = evenementRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    @Override
    public EvenementDto modifyEvenement(EvenementDto evenementDto) {
        Evenement evenement = evenementDtoToEntity(evenementDto);
        this.getEvenementById(evenementDto.getIdEvenement());
        List<EvenementDto> evenements = this.getAllEvenements();
        for(int i = 0;i<evenements.size();i++){
            if(evenements.get(i).getIdEvenement() != evenementDto.getIdEvenement()){
                if(evenements.get(i).getDate().equals(evenementDto.getDate()) && evenements.get(i).getIdLieu() == evenementDto.getIdLieu()){
                    return null;
                }
            }
        }
        evenement = evenementRepository.save(evenement);
        return evenementEntityToDto(evenement);
    }

    @Override
    public EvenementDto getEvenementById(Long evenementId) {
        Evenement evenement = evenementRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("membre not found"));
        return evenementEntityToDto(evenement);
    }

    @Override
    public List<MembreDto> getMembreByEvenement(Long evenementId) {
        List<InscriptionDto> inscriptions = inscriptionService.getInscriptionByIdEvenement(evenementId);
        List<MembreDto> membreDtos = new ArrayList<>();
        inscriptions.forEach(inscription -> {
            membreDtos.add(this.membreService.getMembreById(inscription.getIdMembre()));
        });
        return membreDtos;
    }

    @Override
    public List<InscriptionDto> getInscriptionByEvenement(Long idEvenement) {
        return inscriptionService.getInscriptionByIdEvenement(idEvenement);
    }

    @Override
    public InscriptionDto saveInscription(Long id, Long id2) {
        InscriptionDto inscription = new InscriptionDto();
        inscription.setIdEvenement(id);
        inscription.setIdMembre(id2);
        this.getEvenementById(id);
        this.membreService.getMembreById(id2);
        List<EvenementDto> evenements = this.membreService.getEvenementByMembre(id2);
        List<InscriptionDto> inscriptions = this.inscriptionService.getInscriptionByIdEvenement(id);
        for(int i = 0;i<inscriptions.size();i++){
            if(inscriptions.get(i).getIdMembre() == id2){
                return null;
            }
        }
        for(int i = 0;i<evenements.size();i++){
            if(evenements.get(i).getDate() == this.getEvenementById(id).getDate()){
                return null;
            }
        }
        inscriptionService.saveInscription(inscription);
        return inscription;
    }

    @Override
    public boolean deleteEvenement(Long evenementId) {
        evenementRepository.deleteById(evenementId);
        return true;
    }

    @Override
    public List<EvenementDto> getAllEvenements() {
        List<EvenementDto> evenementDtos = new ArrayList<>();
        List<Evenement> evenements = evenementRepository.findAll();
        evenements.forEach(membre -> {
            evenementDtos.add(evenementEntityToDto(membre));
        });
        return evenementDtos;
    }

    private EvenementDto evenementEntityToDto(Evenement evenement){
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.setIdEvenement(evenement.getIdEvenement());
        evenementDto.setIdLieu(evenement.getIdLieu());
        evenementDto.setNom(evenement.getNom());
        evenementDto.setDate(evenement.getDate());
        evenementDto.setDuree(evenement.getDuree());
        evenementDto.setNbMaxPers(evenement.getNbMaxPers());
        return evenementDto;
    }

    private Evenement evenementDtoToEntity(EvenementDto evenementDto){
        Evenement evenement = new Evenement();
        evenement.setIdEvenement(evenementDto.getIdEvenement());
        evenement.setIdLieu(evenementDto.getIdLieu());
        evenement.setNom(evenementDto.getNom());
        evenement.setDate(evenementDto.getDate());
        evenement.setDuree(evenementDto.getDuree());
        evenement.setNbMaxPers(evenementDto.getNbMaxPers());
        return evenement;
    }
}
