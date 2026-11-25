package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IAgenceService;
import tn.esprit.tpautoloc.web.dto.AgenceDTO;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @GetMapping
    public List<AgenceDTO> findAll() {
        return agenceService.findAll();
    }

    @GetMapping("/{id}")
    public AgenceDTO findById(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO create(@Valid @RequestBody AgenceDTO agenceDTO) { // <-- @Valid ajouté
        return agenceService.create(agenceDTO);
    }

    @PutMapping("/{id}")
    public AgenceDTO update(@PathVariable Long id, @Valid @RequestBody AgenceDTO agenceDTO) { // <-- @Valid ajouté
        return agenceService.update(id, agenceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}