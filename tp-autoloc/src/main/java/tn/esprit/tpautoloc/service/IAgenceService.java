package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.web.dto.AgenceDTO;

import java.util.List;

public interface IAgenceService {
    
    AgenceDTO create(AgenceDTO agenceDTO);
    
    AgenceDTO findById(Long id);
    
    List<AgenceDTO> findAll();
    
    AgenceDTO update(Long id, AgenceDTO agenceDTO);
    
    void deleteById(Long id);
}
