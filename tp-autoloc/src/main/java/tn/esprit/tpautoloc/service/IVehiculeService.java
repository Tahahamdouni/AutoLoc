package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.domain.Vehicule;
import tn.esprit.tpautoloc.web.dto.VehiculeDTO;

import java.util.List;

public interface IVehiculeService {
    
    VehiculeDTO create(VehiculeDTO vehiculeDTO);
    
    VehiculeDTO findById(Long id);
    
    List<VehiculeDTO> findAll();
    
    VehiculeDTO update(Long id, VehiculeDTO vehiculeDTO);
    
    void deleteById(Long id);

    void affecterVehiculeAAgence(Long vehiculeId, Long agenceId);
    void desaffecterVehiculeDeAgence(Long vehiculeId);
}
