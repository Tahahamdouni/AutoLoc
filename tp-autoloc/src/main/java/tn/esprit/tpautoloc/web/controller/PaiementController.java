package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IPaiementService;
import tn.esprit.tpautoloc.web.dto.PaiementDTO;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    public List<PaiementDTO> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    public PaiementDTO findById(@PathVariable Long id) {
        return paiementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaiementDTO create(@Valid @RequestBody PaiementDTO paiementDTO) { // <-- @Valid ajouté
        return paiementService.create(paiementDTO);
    }

    @PutMapping("/{id}")
    public PaiementDTO update(@PathVariable Long id, @Valid @RequestBody PaiementDTO paiementDTO) { // <-- @Valid ajouté
        return paiementService.update(id, paiementDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paiementService.deleteById(id);
    }
}