package tn.esprit.tpautoloc.service;

import tn.esprit.tpautoloc.web.dto.EmployeDTO;

import java.util.List;

public interface IEmployeService {

    EmployeDTO create(EmployeDTO employeDTO);

    EmployeDTO findById(Long id);

    List<EmployeDTO> findAll();

    EmployeDTO update(Long id, EmployeDTO employeDTO);

    void deleteById(Long id);

    void affecterEmployeAAgence(Long employeId, Long agenceId);

    void desaffecterEmployeDeAgence(Long employeId);
}