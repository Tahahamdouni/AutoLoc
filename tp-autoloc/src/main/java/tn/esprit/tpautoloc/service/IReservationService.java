package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.web.dto.ReservationRequestDTO;
import tn.esprit.tpautoloc.web.dto.ReservationResponseDTO;

import java.util.List;

public interface IReservationService {
    
    ReservationResponseDTO create(ReservationRequestDTO reservationRequestDTO);
    
    ReservationResponseDTO findById(Long id);
    
    List<ReservationResponseDTO> findAll();
    
    ReservationResponseDTO update(Long id, ReservationRequestDTO reservationRequestDTO);
    
    void deleteById(Long id);
}
