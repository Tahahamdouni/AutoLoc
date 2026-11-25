package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.domain.Contrat;
import tn.esprit.tpautoloc.web.dto.ContratDTO;

import java.util.List;

public interface IContratService {
    
ContratDTO create(ContratDTO contratDTO);
    
    ContratDTO findById(Long id);
    
    List<ContratDTO> findAll();
    
    ContratDTO update(Long id, ContratDTO contratDTO);
    
    void deleteById(Long id);
}
