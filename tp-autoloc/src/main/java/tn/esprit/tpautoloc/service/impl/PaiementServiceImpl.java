package tn.esprit.tpautoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpautoloc.domain.Paiement;
import tn.esprit.tpautoloc.exception.ResourceNotFoundException;
import tn.esprit.tpautoloc.repository.IContratRepository;
import tn.esprit.tpautoloc.repository.IPaiementRepository;
import tn.esprit.tpautoloc.service.IPaiementService;
import tn.esprit.tpautoloc.web.dto.PaiementDTO;
import tn.esprit.tpautoloc.web.mapper.PaiementMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementDTO create(PaiementDTO paiementDTO) {
        if (paiementDTO.getId() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau paiement ne doit pas avoir d'identifiant");
        }
        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        Paiement saved = paiementRepository.save(paiement);
        return paiementMapper.toDto(saved);
    }

    @Override
    public PaiementDTO findById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
        return paiementMapper.toDto(paiement);
    }

    @Override
    public List<PaiementDTO> findAll() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public PaiementDTO update(Long id, PaiementDTO paiementDTO) {
        Paiement existant = paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));

        existant.setDatePaiement(paiementDTO.getDatePaiement());
        existant.setMontant(paiementDTO.getMontant());
        existant.setModePaiement(paiementDTO.getModePaiement());

        if (paiementDTO.getContratId() != null) {
            existant.setContrat(contratRepository.findById(paiementDTO.getContratId())
                    .orElseThrow(() -> new ResourceNotFoundException("Contrat", paiementDTO.getContratId())));
        }

        Paiement updated = paiementRepository.save(existant);
        return paiementMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paiement", id);
        }
        paiementRepository.deleteById(id);
    }
}