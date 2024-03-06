package com.example.demo.services.impl;

import com.example.demo.dtos.MembreDto;
import com.example.demo.dtos.InscriptionDto;
import com.example.demo.entities.Membre;
import com.example.demo.repositories.MembreRepository;
import com.example.demo.services.MembreService;
import com.example.demo.services.InscriptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("membreService")
public class MembreServiceImpl implements MembreService {

	private final MembreRepository membreRepository;

    private final InscriptionService inscriptionService;

    public MembreServiceImpl(MembreRepository membreRepository, InscriptionService inscriptionService){
        this.membreRepository = membreRepository;
        this.inscriptionService = inscriptionService;
    }

    @Override
    public MembreDto saveMembre(MembreDto membreDto) {
        // Converts the dto to the membre entity
        Membre membre = membreDtoToEntity(membreDto);
        List<MembreDto> membres = this.getAllMembres();
        for(int i = 0;i<membres.size();i++){
            if(membres.get(i).getIdMembre() == membreDto.getIdMembre()){
                return null;
            }
        }
        // Save the membre entity
        membre = membreRepository.save(membre);
        // Return the new dto
        return membreEntityToDto(membre);
    }

    @Override
    public MembreDto modifyMembre(MembreDto membreDto) {
        // Converts the dto to the membre entity
        Membre membre = membreDtoToEntity(membreDto);
        this.getMembreById(membreDto.getIdMembre());
        // Save the membre entity
        membre = membreRepository.save(membre);
        // Return the new dto
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
    public List<MembreDto> getMembreByEvenement(Long evenementId) {
        List<InscriptionDto> inscriptions = inscriptionService.getInscriptionByIdEvenement(evenementId);
        List<MembreDto> membreDtos = new ArrayList<>();
        inscriptions.forEach(inscription -> {
            membreDtos.add(this.getMembreById(inscription.getIdMembre()));
        });
        return membreDtos;
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

    /**
     * Map membre dto to membre entity
     */
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

    /**
     * Map membre entity to membre dto
     */
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
}
