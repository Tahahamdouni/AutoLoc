package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IContratService;
import tn.esprit.tpautoloc.web.dto.ContratDTO;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @GetMapping
    public List<ContratDTO> findAll() {
        return contratService.findAll();
    }

    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) {
        return contratService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDTO create(@Valid @RequestBody ContratDTO contratDTO) { // <-- @Valid ajouté
        return contratService.create(contratDTO);
    }

    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id, @Valid @RequestBody ContratDTO contratDTO) { // <-- @Valid ajouté
        return contratService.update(id, contratDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}