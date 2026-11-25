package tn.esprit.tpautoloc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.domain.Employe;
import tn.esprit.tpautoloc.exception.ResourceNotFoundException;
import tn.esprit.tpautoloc.repository.IAgenceRepository;
import tn.esprit.tpautoloc.repository.IEmployeRepository;
import tn.esprit.tpautoloc.service.IEmployeService;
import tn.esprit.tpautoloc.web.dto.EmployeDTO;
import tn.esprit.tpautoloc.web.mapper.EmployeMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private static final String ENTITY_NAME = "Employe"; // <-- Constante ajoutée pour SonarLint

    private final IEmployeRepository employeRepository;
    private final IAgenceRepository agenceRepository;
    private final EmployeMapper employeMapper;

    @Override
    public EmployeDTO create(EmployeDTO dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Un nouvel employé ne doit pas avoir d'identifiant");
        }
        Employe employe = employeMapper.toEntity(dto);
        Employe saved = employeRepository.save(employe);
        return employeMapper.toDto(saved);
    }

    @Override
    public EmployeDTO findById(Long id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, id)); // <-- Utilisation de la constante
        return employeMapper.toDto(employe);
    }

    @Override
    public List<EmployeDTO> findAll() {
        return employeRepository.findAll().stream()
                .map(employeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeDTO update(Long id, EmployeDTO dto) {
        Employe existant = employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, id)); // <-- Utilisation de la constante

        existant.setNom(dto.getNom());
        existant.setPrenom(dto.getPrenom());
        existant.setRole(dto.getRole());

        Employe updated = employeRepository.save(existant);
        return employeMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException(ENTITY_NAME, id); // <-- Utilisation de la constante
        }
        employeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void affecterEmployeAAgence(Long employeId, Long agenceId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, employeId)); // <-- Utilisation de la constante

        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", agenceId));

        employe.setAgence(agence);
        employeRepository.save(employe);
    }

    @Override
    @Transactional
    public void desaffecterEmployeDeAgence(Long employeId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, employeId)); // <-- Utilisation de la constante

        if (employe.getAgence() == null) {
            throw new IllegalArgumentException("Cet employé n'est rattaché à aucune agence.");
        }

        employe.setAgence(null);
        employeRepository.save(employe);
    }
}