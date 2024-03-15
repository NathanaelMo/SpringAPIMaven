package com.spring.services.impl;

import com.spring.dtos.EvenementDto;
import com.spring.dtos.MembreDto;
import com.spring.dtos.InscriptionDto;
import com.spring.entities.Evenement;
import com.spring.entities.Membre;
import com.spring.repositories.MembreRepository;
import com.spring.repositories.EvenementRepository;
import com.spring.services.MembreService;
import com.spring.services.InscriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("membreService")
public class MembreServiceImpl implements MembreService {

    private final MembreRepository membreRepository;

    private final EvenementRepository evenementRepository;

    private final InscriptionService inscriptionService;

    public MembreServiceImpl(MembreRepository membreRepository, EvenementRepository evenementRepository, InscriptionService inscriptionService){
        this.membreRepository = membreRepository;
        this.evenementRepository = evenementRepository;
        this.inscriptionService = inscriptionService;
    }

    @Override
    public MembreDto saveMembre(MembreDto membreDto) {
        Membre membre = membreDtoToEntity(membreDto);
        List<MembreDto> membres = this.getAllMembres();
        for(int i = 0;i<membres.size();i++){
            if(membres.get(i).getIdMembre() == membreDto.getIdMembre()){
                return null;
            }
            if(membres.get(i).getEmail().equals(membreDto.getEmail())){
                return null;
            }
        }
        membre = membreRepository.save(membre);
        return membreEntityToDto(membre);
    }

    @Override
    public MembreDto modifyMembre(MembreDto membreDto) {
        Membre membre = membreDtoToEntity(membreDto);
        this.getMembreById(membreDto.getIdMembre());
        membre = membreRepository.save(membre);
        return membreEntityToDto(membre);
    }

    @Override
    public MembreDto getMembreById(Long membreId) {
        Membre membre = membreRepository.findById(membreId).orElseThrow(() -> new EntityNotFoundException("membre not found"));
        return membreEntityToDto(membre);
    }

    @Override
    public List<InscriptionDto> getInscriptionByMembre(Long membreId) {
        return inscriptionService.getInscriptionByIdMembre(membreId);
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

    private EvenementDto getEvenementById(Long evenementId) {
        Evenement evenement = evenementRepository.findById(evenementId).orElseThrow(() -> new EntityNotFoundException("membre not found"));
        return evenementEntityToDto(evenement);
    }

    @Override
    public boolean deleteMembre(Long membreId) {
        this.getMembreById(membreId);
        membreRepository.deleteById(membreId);
        return true;
    }

    @Override
    public List<MembreDto> getAllMembres() {
        List<MembreDto> membreDtos = new ArrayList<>();
        List<Membre> membres = membreRepository.findAll();
        membres.forEach(membre -> {
            membreDtos.add(membreEntityToDto(membre));
        });
        return membreDtos;
    }

    private MembreDto membreEntityToDto(Membre membre){
        MembreDto membreDto = new MembreDto();
        membreDto.setIdMembre(membre.getIdMembre());
        membreDto.setEmail(membre.getEmail());
        membreDto.setNom(membre.getNom());
        membreDto.setPrenom(membre.getPrenom());
        membreDto.setAdresse(membre.getAdresse());
        membreDto.setDateNaissance(membre.getDateNaissance());
        membreDto.setMdp(membre.getMdp());
        return membreDto;
    }

    private Membre membreDtoToEntity(MembreDto membreDto){
        Membre membre = new Membre();
        membre.setIdMembre(membreDto.getIdMembre());
        membre.setEmail(membreDto.getEmail());
        membre.setNom(membreDto.getNom());
        membre.setPrenom(membreDto.getPrenom());
        membre.setAdresse(membreDto.getAdresse());
        membre.setDateNaissance(membreDto.getDateNaissance());
        membre.setMdp(membreDto.getMdp());
        return membre;
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

}
