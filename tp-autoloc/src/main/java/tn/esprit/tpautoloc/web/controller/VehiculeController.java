package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IVehiculeService;
import tn.esprit.tpautoloc.web.dto.VehiculeDTO;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeDTO> findAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    public VehiculeDTO findById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@Valid @RequestBody VehiculeDTO dto) { // <-- @Valid ajouté
        return vehiculeService.create(dto);
    }

    @PutMapping("/{id}")
    public VehiculeDTO update(@PathVariable Long id, @Valid @RequestBody VehiculeDTO dto) { // <-- @Valid ajouté
        return vehiculeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("/{vehiculeId}/affecter/{agenceId}")
    public ResponseEntity<Void> affecterVehiculeAAgence(
            @PathVariable Long vehiculeId,
            @PathVariable Long agenceId) {
        vehiculeService.affecterVehiculeAAgence(vehiculeId, agenceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{vehiculeId}/desaffecter")
    public ResponseEntity<Void> desaffecterVehiculeDeAgence(@PathVariable Long vehiculeId) {
        vehiculeService.desaffecterVehiculeDeAgence(vehiculeId);
        return ResponseEntity.ok().build();
    }
}