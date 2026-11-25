package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.domain.Client;
import tn.esprit.tpautoloc.web.dto.ClientDTO;

import java.util.List;

public interface IClientService {
    
    ClientDTO create(ClientDTO clientDTO);
    
    ClientDTO findById(Long id);
    
    List<ClientDTO> findAll();
    
    ClientDTO update(Long id, ClientDTO clientDTO);
    
    void deleteById(Long id);
}
