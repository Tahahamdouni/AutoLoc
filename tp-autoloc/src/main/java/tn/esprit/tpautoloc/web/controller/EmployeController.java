package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IEmployeService;
import tn.esprit.tpautoloc.web.dto.EmployeDTO;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping
    public List<EmployeDTO> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeDTO findById(@PathVariable Long id) {
        return employeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO create(@Valid @RequestBody EmployeDTO employeDTO) {
        return employeService.create(employeDTO);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @Valid @RequestBody EmployeDTO employeDTO) {
        return employeService.update(id, employeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeService.deleteById(id);
    }

    @PutMapping("/{employeId}/affecter/{agenceId}")
    public ResponseEntity<Void> affecterEmployeAAgence(
            @PathVariable Long employeId,
            @PathVariable Long agenceId) {
        employeService.affecterEmployeAAgence(employeId, agenceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{employeId}/desaffecter")
    public ResponseEntity<Void> desaffecterEmployeDeAgence(@PathVariable Long employeId) {
        employeService.desaffecterEmployeDeAgence(employeId);
        return ResponseEntity.ok().build();
    }
}