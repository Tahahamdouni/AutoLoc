package tn.esprit.tpautoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.exception.ResourceNotFoundException;
import tn.esprit.tpautoloc.repository.IAgenceRepository;
import tn.esprit.tpautoloc.service.IAgenceService;
import tn.esprit.tpautoloc.web.dto.AgenceDTO;
import tn.esprit.tpautoloc.web.mapper.AgenceMapper; // <-- Import du Mapper

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper; // <-- Injection du Mapper

    @Override
    public AgenceDTO create(AgenceDTO agenceDTO) {
        if (agenceDTO.getId() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle agence ne doit pas avoir d'identifiant");
        }
        // 1. Convertir le DTO en Entité
        Agence agence = agenceMapper.toEntity(agenceDTO);

        // 2. Sauvegarder l'entité
        Agence saved = agenceRepository.save(agence);

        // 3. Renvoyer le DTO
        return agenceMapper.toDto(saved);
    }

    @Override
    public AgenceDTO findById(Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
        return agenceMapper.toDto(agence);
    }

    @Override
    public List<AgenceDTO> findAll() {
        return agenceRepository.findAll().stream()
                .map(agenceMapper::toDto) // Convertir chaque entité en DTO
                .toList();
    }

    @Override
    public AgenceDTO update(Long id, AgenceDTO agenceDTO) {
        // 1. Chercher l'existant (qui est une entité)
        Agence existant = agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));

        // 2. Mettre à jour les champs
        existant.setNom(agenceDTO.getNom());
        existant.setVille(agenceDTO.getVille());
        existant.setAdresse(agenceDTO.getAdresse());
        existant.setTelephone(agenceDTO.getTelephone());

        // 3. Sauvegarder et renvoyer le DTO
        Agence updated = agenceRepository.save(existant);
        return agenceMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepository.deleteById(id);
    }
}