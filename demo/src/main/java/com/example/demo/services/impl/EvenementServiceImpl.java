package com.example.demo.services.impl;
import com.example.demo.dtos.InscriptionDto;
import com.example.demo.dtos.MembreDto;
import com.example.demo.entities.Evenement;
import com.example.demo.dtos.EvenementDto;
import com.example.demo.repositories.EvenementRepository;
import com.example.demo.services.EvenementService;
import com.example.demo.services.InscriptionService;
import com.example.demo.services.MembreService;
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
        // Converts the dto to the membre entity
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
        // Save the membre entity
        evenement = evenementRepository.save(evenement);
        // Return the new dto
        return evenementEntityToDto(evenement);
    }

    @Override
    public EvenementDto modifyEvenement(EvenementDto evenementDto) {
        // Converts the dto to the evenement entity
        Evenement evenement = evenementDtoToEntity(evenementDto);
        // Save the evenement entity
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
        // Return the new dto
        return evenementEntityToDto(evenement);
    }

    @Override
    public EvenementDto getEvenementById(Long evenementId) {
        Evenement evenement = evenementRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("membre not found"));
        return evenementEntityToDto(evenement);
    }

    @Override
    public List<EvenementDto> getEvenementByMembre(Long membreId) {
        List<InscriptionDto> inscriptions = inscriptionService.getInscriptionByIdMembre(membreId);
        List<EvenementDto> evenementDtos = new ArrayList<>();
        inscriptions.forEach(inscription -> {
            evenementDtos.add(this.getEvenementById(inscription.getIdEvenement()));
        });
        return evenementDtos;
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
        List<EvenementDto> evenements = this.getEvenementByMembre(id2);
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

    /**
     * Map evenement dto to evenement entity
     */
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

    /**
     * Map evenement entity to evenement dto
     */
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
